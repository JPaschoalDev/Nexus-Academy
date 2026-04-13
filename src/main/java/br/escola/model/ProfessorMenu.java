package br.escola.model;

// Importa todas as classes de banco de dados: Connection, Statement, ResultSet, etc.
import br.escola.service.Conexao;

import java.sql.*;
// Importa o Scanner, que usamos para ler o que o usuário digita no console
import java.util.Scanner;

// =========================================================================
//                         Classe "ProfessorMenu.java"
// =========================================================================

// Agrupa todas as ações que o usuário pode fazer relacionadas a professores.
// Cada metodo publico aqui corresponde a uma opção do menu la no Main.Java.

// Todos os metodos seguem o mesmo fluxo:
// 1. Montar o SQL ou nome da procedure
// 2. Abrir conexao com o banco
// 3. Executar o comando
// 4. Ler e exibir o resultado
// 5. Fechar tudo automaticamente (try-witch-resources)

public class ProfessorMenu {

    // =========================================================================
    //                         Listar todos os professores
    // =========================================================================
    // Busca e exibe todos os professores cadastrados no banco,
    // mostrando id, nome, telefone e e-mail.

    public static void listarProfessores() {
        // Montamos a query SQL que vamos enviar ao banco.
        // Consulta direta na tabela professor, sem JOINs nem filtros.
        String sql = "SELECT id, nome, telefone, email From professor";

        // try-with-resources: declara os recursos dentro do try().
        // O Java garante que Connection, Statement e ResultSet serão FECHADOS
        // autmoaticamente ao final - mesmo que ocorra um erro no meio do caminho.
        // Isso evita conexoes "presas" no banco de dados.
        try (Connection con = Conexao.obter();     // Abre conexao com o SQL.
             Statement st = con.createStatement();   // Cria executor de SQL simples
             ResultSet rs = st.executeQuery(sql))   {// Executa a query e guarda as linhas retornadas

            System.out.println("\n--- PROFESSORES CADASTRADOS ---");

            // rs.next() funciona como um cursor/ponteiro:
            // comeca ANTES da primeira linha, e cada chamada avanca uma posicao.
            // Retorna true enquanto houver linha, false quando acabar.
            while (rs.next()) {
                // printf formata a saida em colunas alinhadas:
                // %-4d = numero inteiro (d) alinhado a esquerda (-) em 4 carcteres
                // %-30s = texto (s) alinhado a esquerda em 30 caracteres de largura
                // %-15s = texto em 15 caracteres de largura
                // $s = texto simples sem formatacao especial
                // %n = quebra de linha (equivalente ao \n, mas compativel com qualquer SO)
                System.out.printf("%-4d | %-30s | %-15s | %s%n",
                        rs.getInt("id"),          // Lê o valor da coluna "id" da linha atual
                        rs.getString("nome"),     // Lê o valor da coluna "nome"
                        rs.getString("telefone"), // Lê o valor da coluna "telefone"
                        rs.getString("email"));   // Lê o valor da coluna "email"
            }
        }  catch (Exception e) {
            // Captura qualquer erro que tenha ocorrido dentro do try:
            // banco offline, senha errada, query com erro de sintaxe, etc.
            // getMessage() retorna uma dscricao legivel do problema.
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // =========================================================================
    //                     Ver carga horária dos professores
    // =========================================================================
    // Exibe a carga horária de cada professor, mostrando quantas turmas
    // e disciplinas ele leciona em determinado ano letivo.
    // Aqui usamos uma VIEW do banco (vw_carga_professor) em vez de escrever
    // toda a lógica no Java. A view já faz todos os JOINs e agrupamentos.

    public static void verCargaProfessor(Scanner sc) {
        // "sc Scanner" recebido do Main para ler os dados que o usuario digitar

        System.out.print("Digite o ano letivo (ex: 2026): ");
        // sc.nextInt() = Usado para ler o ano como numero inteiro
        int ano = sc.nextInt();

        // Consulta na view filtrando pelo ano informado.
        // O "?" será substituído pelo valor do ano antes da execução.
        // Todo o trabalho pesado (JOINs, COUNT, GROUP BY) já está encapsulado na view do banco.
        String sql = "SELECT professor, total_turmas, total_disciplinas " +
                "From vw_carga_professor WHERE ano = ?";

        // PreparedStatement e melhor que Statement quando a query tem parametros.
        // Ele separa o SQL dos valores, evitando SQL Injection.
        try (Connection con = Conexao.obter();
             PreparedStatement ps = con.prepareStatement (sql)) {

            // Substitui o primeiro "?" pelo ano digitado.
            // Usar parâmetros assim (em vez de concatenar strings) protege
            // contra ataques de SQL Injection.
            ps.setInt(1, ano);

            // executeQuery() executa a consulta e retorna as linhas do resultado
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- CARGA DOS PROFESSORES (" + ano + ") ---");

            while (rs.next()) {
                // %-30s = nome do professor em 30 caracteres alinhado à esquerda
                // %d    = número inteiro (total de turmas e disciplinas)
                System.out.printf("%-30s | Turmas: %d | Disciplinas: %d%n",
                        rs.getString("professor"),  // nome do professor
                        rs.getInt("total_turmas"),  // quantas turmas ele da aula
                        rs.getInt("total_disciplinas"));    // quantas disciplinas ele leciona
            }
        }  catch (Exception e) {
             System.out.println("Erro: " + e.getMessage());
        }
    }
}