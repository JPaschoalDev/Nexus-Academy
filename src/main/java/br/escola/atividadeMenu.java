package br.escola;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class AtividadeMenu {
    // =========================================================================
    //                           Listar todas as atividades
    // =========================================================================
    // Método responsável por buscar e exibir todas as atividades cadastradas
    // no banco de dados.
    //
    // As informações apresentadas incluem:
    // - ID da atividade
    // - ID do aluno
    // - Código de matrícula do aluno
    // - ID do professor responsável
    // - Tipo de esporte praticado
    // - Descrição da atividade
    // - Data de ocorrência
    //
    // Os dados são recuperados da tabela "atividades" e exibidos no console.

    public static void listarAtividades() {
        String sql = "SELECT id_atividade, id_aluno, matricula_aluno, id_professor, tipo_esporte, descricao, data_ocorrencia " +
                "FROM atividades a " +
                "JOIN aluno al ON a.id_aluno = al.id " +
                "JOIN matricula m ON m.id_aluno = al.id";

        try (Connection conn = Conexao.obter();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idAtividade = rs.getInt("id_atividade");
                int idAluno = rs.getInt("id_aluno");
                String matricula = rs.getString("matricula_aluno");
                int idProfessor = rs.getInt("id_professor");
                String tipoEsporte = rs.getString("tipo_esporte");
                String descricao = rs.getString("descricao");
                Date dataOcorrencia = rs.getDate("data_ocorrencia");

                System.out.println("ID Atividade: " + idAtividade);
                System.out.println("ID Aluno: " + idAluno);
                System.out.println("Matrícula: " + matricula);
                System.out.println("ID Professor: " + idProfessor);
                System.out.println("Tipo de Esporte: " + tipoEsporte);
                System.out.println("Descrição: " + descricao);
                System.out.println("Data: " + dataOcorrencia);
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar atividades: " + e.getMessage());
        }
    }



    public static class cadastrarAtividade {
        // =========================================================================
        //                           Listar todas as atividades
        // =========================================================================
        // Método responsável por buscar e exibir todas as atividades cadastradas
        // no banco de dados.
        //
        // As informações apresentadas incluem:
        // - ID da atividade
        // - ID do aluno
        // - Código de matrícula do aluno
        // - ID do professor responsável
        // - Tipo de esporte praticado
        // - Descrição da atividade
        // - Data de ocorrência
        //
        // Os dados são recuperados da tabela "atividades" e exibidos no console.

        public static void listarAtividades() {
            String sql = "SELECT id_atividade, id_aluno, matricula_aluno, id_professor, tipo_esporte, descricao, data_ocorrencia " +
                    "FROM atividades a " +
                    "JOIN aluno al ON a.id_aluno = al.id " +
                    "JOIN matricula m ON m.id_aluno = al.id";

            try (Connection conn = Conexao.obter();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int idAtividade = rs.getInt("id_atividade");
                    int idAluno = rs.getInt("id_aluno");
                    String matricula = rs.getString("matricula_aluno");
                    int idProfessor = rs.getInt("id_professor");
                    String tipoEsporte = rs.getString("tipo_esporte");
                    String descricao = rs.getString("descricao");
                    Date dataOcorrencia = rs.getDate("data_ocorrencia");

                    System.out.println("ID Atividade: " + idAtividade);
                    System.out.println("ID Aluno: " + idAluno);
                    System.out.println("Matrícula: " + matricula);
                    System.out.println("ID Professor: " + idProfessor);
                    System.out.println("Tipo de Esporte: " + tipoEsporte);
                    System.out.println("Descrição: " + descricao);
                    System.out.println("Data: " + dataOcorrencia);
                    System.out.println("-----------------------------");
                }

            } catch (SQLException e) {
                System.out.println("Erro ao listar atividades: " + e.getMessage());
            }
        }
    }
}