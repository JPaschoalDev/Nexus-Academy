package br.escola;

// Importa todas as classes de banco de dados: Connection, Statement, ResultSet, etc.
import java.sql.*;
// Importa o Scanner, que usamos para ler o que o usuário digita no console
import java.util.Scanner;

// =========================================================================
//                           Classe "AlunoMenu.java"
// =========================================================================

// Agrupa todas as ações que o usuário pode fazer relacionadas a alunos.
// Cada metodo público aqui corresponde a uma opção do menu lá no Main.java.

// Todos os métodos seguem o mesmo fluxo:
// 1. Montar o SQL ou nome da procedure
// 2. Abrir conexão com o banco
// 3. Executar o comando
// 4. Ler e exibir o resultado
// 5. Fechar tudo automaticamente (try-with-resources)

public class AlunoMenu {

    // =========================================================================
    //                           Listar todos os alunos
    // =========================================================================
    // Busca e exibe todos os alunos cadastrados no banco, mostrando nome, código da matrícula e situação atual.

    public static void listarAlunos() {
        // Montamos a query SQL que vamos enviar ao banco.
        // "a" e "m" são apelidos para as tabelas aluno e matricula usados para deixar o código mais limpo
        // O JOIN une as duas tabelas: para cada aluno, busca sua matrícula correspondente.

        String sql = "SELECT a.nome, m.codigo, m.status " +
                "FROM aluno a JOIN matricula m ON m.id_aluno = a.id";

        // try-with-resources: declara os recursos dentro do try().
        // O Java garante que Connection, Statement e ResultSet serão FECHADOS
        // automaticamente ao final — mesmo que ocorra um erro no meio do caminho.
        // Isso evita conexões "presas" no banco de dados.
        try (Connection con = Conexao.obter();      // Abre conexão com o MySQL
             Statement st  = con.createStatement(); // Cria executor de SQL simples
             ResultSet rs  = st.executeQuery(sql)) {// Executa a query e guarda as linhas retornadas

            System.out.println("\n--- ALUNOS MATRICULADOS ---");

            // rs.next() funciona como um cursor/ponteiro:
            // começa ANTES da primeira linha, e cada chamada avança uma posição.
            // Retorna true enquanto houver linha, false quando acabar.
            while (rs.next()) {

                // printf formata a saída em colunas alinhadas:
                // %-30s = texto (s) alinhado à esquerda (-) em 30 caracteres de largura
                // %s    = texto simples sem formatação especial
                // %n    = quebra de linha (equivalente ao \n, mas compatível com qualquer SO)
                System.out.printf("%-30s | %s | %s%n",
                        rs.getString("nome"),     // Lê o valor da coluna "nome" da linha atual
                        rs.getString("codigo"),   // Lê o valor da coluna "codigo"
                        rs.getString("status"));  // Lê o valor da coluna "status"
            }

        } catch (Exception e) {
            // Captura qualquer erro que tenha ocorrido dentro do try:
            // banco offline, senha errada, query com erro de sintaxe, etc.
            // getMessage() retorna uma descrição legível do problema.
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // =========================================================================
    //                     Ver boletim de um aluno específico
    // =========================================================================
    // Busca e exibe todos os alunos cadastrados no banco, mostrando nome, código da matrícula e situação atual.
    // Pede um código de matrícula ao usuário e exibe o boletim completo do aluno,
    // com as notas dos 4 bimestres, média final e situação em cada disciplina.
    // Chama a Stored Procedure "sp_boletim_aluno" que já existe no banco.
    // Toda a lógica de cálculo fica no banco, não no Java.
    public static void verBoletim(Scanner sc) {
        // "sc Scanner" recebido do Main para ler os dados que o usuário digitar

        System.out.print("Digite o código da matrícula: ");
        // sc.next() = Usado para ler o código da mátricula
        String codigo = sc.next();

        // Sintaxe de chamada de Stored Procedure no Java JDBC.
        // As chaves {} são OBRIGATÓRIAS nesse formato.
        // O primeiro "?" será o código da matrícula (preenchemos abaixo).
        // O 2026 é o ano letivo (fixo por enquanto)
        String sql = "{CALL sp_boletim_aluno(?, 2026)}";

        // CallableStatement é o tipo certo para chamar Stored Procedures.
        // Diferente do Statement simples, ele suporta parâmetros de entrada e saída.
        try (Connection con = Conexao.obter();
             CallableStatement cs = con.prepareCall(sql)) { // prepara a chamada da procedure

            // Substitui o primeiro "?" pelo código digitado pelo usuário.
            // Usar parâmetros assim (em vez de concatenar strings) protege
            // contra ataques de SQL Injection.
            cs.setString(1, codigo);

            // executeQuery() executa a procedure e retorna as linhas do resultado
            ResultSet rs = cs.executeQuery();

            System.out.println("\n--- BOLETIM ---");

            while (rs.next()) {
                // Exibe cada disciplina em uma linha formatada com:
                // %-20s = nome da disciplina em 20 caracteres alinhado à esquerda
                // %.1f  = número decimal com 1 casa após a vírgula (ex: 8.5)
                System.out.printf("%-20s | B1:%.1f B2:%.1f B3:%.1f B4:%.1f | Média:%.1f | %s%n",
                        rs.getString("disciplina"),  // nome da matéria
                        rs.getDouble("bim1"),         // nota do 1º bimestre
                        rs.getDouble("bim2"),         // nota do 2º bimestre
                        rs.getDouble("bim3"),         // nota do 3º bimestre
                        rs.getDouble("bim4"),         // nota do 4º bimestre
                        rs.getDouble("media"),        // média anual calculada pela procedure
                        rs.getString("situacao"));    // APROVADO, RECUPERACAO ou REPROVADO
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // =========================================================================
    //                      Listar alunos em situação de risco
    // =========================================================================

    // Exibe todos os alunos que possuem média abaixo de 6,0
    // ou mais de 20 faltas em alguma disciplina.
    // Aqui usamos uma VIEW do banco (vw_alunos_em_risco) em vez de escrever
    // toda a lógica no Java. A view já faz todos os cálculos e filtros.
    public static void alunosEmRisco() {

        // Consulta direta na view — simples assim!
        // Todo o trabalho pesado (JOINs, AVG, HAVING) já está encapsulado na view do banco.
        String sql = "SELECT aluno, disciplina, media, faltas FROM vw_alunos_em_risco";

        try (Connection con = Conexao.obter();
             Statement st  = con.createStatement();
             ResultSet rs  = st.executeQuery(sql)) {

            System.out.println("\n--- ALUNOS EM RISCO ---");

            while (rs.next()) {
                // %-20s = aluno em 20 caracteres | %-15s = disciplina em 15 caracteres
                // %.1f  = média com 1 decimal    | %d = número inteiro (faltas)
                System.out.printf("%-20s | %-15s | Média:%.1f | Faltas:%d%n",
                        rs.getString("aluno"),
                        rs.getString("disciplina"),
                        rs.getDouble("media"),
                        rs.getInt("faltas"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // =========================================================================
    //                           Cadastrar novo aluno
    // =========================================================================
    // Pede os dados de um novo aluno pelo console e o cadastra no banco,
    // criando automaticamente o aluno e a matrícula em uma única operação.
    // Usa a Stored Procedure "sp_matricular_aluno", que retorna o código
    // da matrícula gerado pelo banco (parâmetro OUT).

    public static void cadastrarAluno(Scanner sc) {
        // "sc Scanner" recebido do Main para ler os dados que o usuário digitar

        // Esse sc.nextLine() inicial é um "erro" clássica do Scanner!
        // Quando o usuário digita "4" e pressiona Enter no menu (Main.java),
        // o sc.nextInt() lê o "4" mas DEIXA o "\n" (Enter) no buffer.
        // Se não limparmos isso, o próximo sc.nextLine() vai ler esse "\n" vazio
        // em vez de esperar o usuário digitar o nome. Esse nextLine() "engole" o lixo.
        sc.nextLine();

        // Coletamos os dados do novo aluno pelo console
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Data nascimento (AAAA-MM-DD): ");
        String nasc = sc.next();

        System.out.print("Responsável: ");
        sc.nextLine();
        String resp = sc.nextLine();

        System.out.print("Telefone: ");
        String tel = sc.next();

        // A procedure tem 6 parâmetros no total:
        // Entradas (IN):  1-nome, 2-nascimento, 3-responsável, 4-telefone, 5-endereço
        // Saída    (OUT): 6-codigo (o banco gera e devolve o código da matrícula)
        String sql = "{CALL sp_matricular_aluno(?,?,?,?,?,?)}";

        try (Connection con = Conexao.obter();
             CallableStatement cs = con.prepareCall(sql)) {

            // Preenchendo os parâmetros de ENTRADA (IN) — posições 1 a 5
            cs.setString(1, nome);
            cs.setString(2, nasc);
            cs.setString(3, resp);
            cs.setString(4, tel);
            cs.setString(5, "");

            // Registrando o parâmetro de SAÍDA (OUT) — posição 6
            // Precisamos avisar o Java ANTES de executar que esse parâmetro
            // vai receber um valor de volta do banco, do tipo VARCHAR (texto).
            cs.registerOutParameter(6, Types.VARCHAR);

            // Executa a procedure — nesse caso usamos execute() e não executeQuery()
            // porque a procedure não retorna um ResultSet, só o parâmetro OUT
            cs.execute();

            // Após a execução, buscamos o código que o banco gerou e devolveu
            System.out.println("Aluno cadastrado! Código: " + cs.getString(6));

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
