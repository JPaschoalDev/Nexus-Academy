package br.escola;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TurmaMenu {
    public void listarTurmas() {
        String sql = "SELECT turma, FROM escola.turma";

        try (Connection con = Conexao.obter();
             Statement st  = con.createStatement();
             ResultSet ab = st.executeQuery(sql)) {

            System.out.println("\n--- TURMA ---");

            while (ab.next()) {
                System.out.printf("id: %d | Nome: %s | Ano: %s | Turno: %s | Capacidade: %d%n",
                        ab.getInt("id"),
                        ab.getString("nome"),
                        ab.getInt("ano"),
                        ab.getString("turno"),
                        ab.getInt("capacidade"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }



}
