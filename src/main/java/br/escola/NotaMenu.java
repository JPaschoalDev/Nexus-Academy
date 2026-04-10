package br.escola;

// Importa todas as classes de banco de dados: Connection, CallableStatement, ResultSet, etc.
import java.sql.*;
// Importa o Scanner, que usamos para ler o que o usuário digita no console
import java.util.Scanner;

// =========================================================================
//                           Classe "NotaMenu.java"
// =========================================================================

// Agrupa todas as ações que o usuário pode fazer relacionadas a notas.
// Cada metodo público aqui corresponde a uma opção do menu lá no Main.java.

// Todos os métodos seguem o mesmo fluxo:
// 1. Montar o SQL ou nome da procedure
// 2. Abrir conexão com o banco
// 3. Executar o comando
// 4. Ler e exibir o resultado (quando houver)
// 5. Fechar tudo automaticamente (try-with-resources)

public class NotaMenu {

    // =========================================================================
    //                              Lançar nota
    // =========================================================================
    // Pede os dados de uma nota pelo console e a registra no banco.
    // Chama a Stored Procedure "sp_lancar_nota" que já existe no banco,
    // e que faz todas as validações automaticamente:
    //  - verifica se a matrícula está ATIVA (trigger bloqueia se não estiver)
    //  - verifica se a nota está entre 0 e 10
    //  - verifica se o bimestre é válido (1 a 4)
    // Toda alteração também é registrada automaticamente na tabela log_nota
    // pelos triggers do banco (trg_nota_insert).

    public static void lancarNota(Scanner sc) {
        // "sc Scanner" recebido do Main para ler os dados que o usuário digitar

        // Esse sc.nextLine() inicial é um "erro" clássico do Scanner!
        // Quando o usuário digita "1" e pressiona Enter no menu (Main.java),
        // o sc.nextInt() lê o "1" mas DEIXA o "\n" (Enter) no buffer.
        // Se não limparmos isso, o próximo sc.nextLine() vai ler esse "\n" vazio
        // em vez de esperar o usuário digitar. Esse nextLine() "engole" o lixo.
        sc.nextLine();

        // Coletamos os dados da nota pelo console
        System.out.print("Código da matrícula do aluno: ");
        String codigoMatricula = sc.nextLine();

        System.out.print("ID da turma_disciplina: ");
        int idTurmaDisc = sc.nextInt();

        System.out.print("Bimestre (1 a 4): ");
        int bimestre = sc.nextInt();

        System.out.print("Nota (0 a 10): ");
        double nota = sc.nextDouble();

        System.out.print("Faltas no bimestre: ");
        int faltas = sc.nextInt();

        // A procedure tem 5 parâmetros de entrada (IN):
        // 1-código da matrícula, 2-id da turma_disciplina,
        // 3-bimestre, 4-nota, 5-faltas
        String sql = "{CALL sp_lancar_nota(?,?,?,?,?)}";

        // CallableStatement é o tipo certo para chamar Stored Procedures.
        // Diferente do Statement simples, ele suporta parâmetros de entrada e saída.
        try (Connection con = Conexao.obter();
             CallableStatement cs = con.prepareCall(sql)) { // prepara a chamada da procedure

            // Preenchendo os parâmetros de ENTRADA (IN) - posições 1 a 5.
            // Usar parâmetros assim (em vez de concatenar strings) protege
            // contra ataques de SQL Injection.
            cs.setString(1, codigoMatricula);
            cs.setInt(2, idTurmaDisc);
            cs.setInt(3, bimestre);
            cs.setDouble(4, nota);
            cs.setInt(5, faltas);

            // Executa a procedure - nesse caso usamos execute() e não executeQuery()
            // porque a procedure só faz INSERT/UPDATE no banco, não retorna linhas.
            cs.execute();

            System.out.println("Nota lançada com sucesso!");

        } catch (Exception e) {
            // Captura qualquer erro que tenha ocorrido dentro do try:
            // matrícula inativa (trigger bloqueia), nota forado intervalo,
            // bimestre inválido, banco offline, etc.
            // getMessage() retonra uma descrição legível do problema.
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // =========================================================================
    //                     Ver boletim de um aluno específico
    // =========================================================================
    // Pede um código de matrícula ao usuário e exibe o boletim completo do aluno,
    // com todas as notas dos 4 bimestres, média final e situação em cada disciplina.
    // Chama a Stored Procedure "sp_boletim_aluno" que já existe no banco.
    // Toda a lógica de cálculo fica no banco, não no Java.
    public static void verBoletim(Scanner sc) {
        // "sc Scanner" recebido do Main para ler os dados que o usuário digitar

        System.out.print("Digite o código da matrícula: ");
        // sc.next() = Usado para ler o código da matrícula
        String codigo = sc.next();

        // Sintaxe de chamada de Stored Procedure no Java JDBC.
        // As chaves {} são OBRIGATÓRIAS nesse formato.
        // O primeiro "?" será o código da matrícula (preenchemos abaixo).
        // o 2026 é o ano letivo (fixo por enquanto)
        String sql = "{CALL sp_boletim_aluno(?, 2026)}";

        // CallableStatement é o tipo certo para chamar Stored Procedures.
        // Diferente do Statement simples, ele suporta parâmetros de entrada e saída.
        try (Connection con = Conexao.obter();
             CallableStatement cs = con.prepareCall(sql)) { // prepara a chamada da procedure

            // Substitui o primeiro "?" pelo código digitado pelo usuário.
            // Usar parâmetros assim (em vez concatenar strings) protege
            // contra ataques de SQL Injection.
            cs.setString(1, codigo);

            // executeQuery() executa a procedure e retorna as linhas do resultado
            ResultSet rs = cs.executeQuery();

            System.out.println("\n--- Boletim ---");

            // rs.next() funciona como um cursor/ponteiro:
            // começa ANTES da primeira linha, e cada chamada avança uma posição.
            // Retorna true enquanto houver linha, false quando acabar.
            while (rs.next()) {
                // Exibe cada disciplina em qual linha formatada com:
                // %-20s = nome da disciplina em 20 caracteres alinado à esquerda
                // %.1f  = número decimal com 1 casa após a vírgula (ex: 8.5)
                // %n    = quebra de linha (equivalente ao \n, mas compatível com qualquer SO)
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
}
