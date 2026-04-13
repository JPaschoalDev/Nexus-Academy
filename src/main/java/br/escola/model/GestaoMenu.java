package br.escola.model;

import br.escola.service.Conexao;

import java.sql.*;
import java.util.Scanner;

public class GestaoMenu {


    public static void transferirAluno(Scanner sc) {

        sc.nextLine();

        System.out.print("Código de matrícula: ");
        String codigo = sc.nextLine();

        System.out.print("Motivo da transferência: ");
        String motivo = sc.nextLine();


        String sql = "{CALL SP_transferir_aluno(?, ?)}";

        try (Connection con = Conexao.obter();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, codigo);
            cs.setString(2, motivo);

            cs.execute();

            System.out.println("Aluno transferido com suceso!");
            System.out.println("Uma ocorrência de COMUNICADO foi registrada automaticamente.");

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }
    public static void fecharAnoLetivo(){

        Scanner sc = null;

        System.out.print("Ano letivo a fechar (ex: 2026): ");
        int ano = sc.nextInt();

        // Pedimos confirmação porque essa operação muda o status de várias matrículas
        // de uma vez e NÃO pode ser desfeita facilmente
        System.out.print("Tem certeza? Isso marcará reprovados do ano " + ano + ". (S/N): ");
        String confirmacao = sc.next();

        // Só prossegue se o usuário digitar "S" ou "s"
        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação cancelada.");
            return; // Sai do metodo sem fazer nada
        }

        // A procedure percorre todas as matrículas ATIVAS do ano informado,
        // calcula a média por disciplina de cada aluno, e marca como REPROVADA
        // quem tiver média abaixo de 6 em qualquer matéria.
        // Ao final ela retorna um resumo com aprovados, reprovados e total.
        String sql = "{CALL sp_fechar_ano_letivo(?)}";

        try (Connection con = Conexao.obter();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, ano); // parâmetro 1: o ano letivo a ser fechado

            // executeQuery() aqui pois a procedure retorna um SELECT de resumo
            ResultSet rs = cs.executeQuery();

            System.out.println("\n--- RESUMO DO FECHAMENTO ---");

            if (rs.next()) {
                // Exibe o resumo retornado pela procedure
                System.out.println("Ano letivo : " + rs.getInt("ano_letivo"));
                System.out.println("Aprovados  : " + rs.getInt("aprovados"));
                System.out.println("Reprovados : " + rs.getInt("reprovados"));
                System.out.println("Total      : " + rs.getInt("total"));
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}