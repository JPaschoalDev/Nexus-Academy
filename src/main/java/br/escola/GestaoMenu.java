package br.escola;

import java.sql.*;
import java.util.Scanner;

public static class GestaoMenu {

}

public static void transferirAluno(Scanner sc) {

    sc.nextLine();

    System.out.print("Código de matrícula: ");
    String codigo = sc.nextLine();

    System.out.print("Motivo da transferência: ");
    String motivo = sc.nextLine();


    String sql = "{ CALL SP_transferir_aluno(?, ?)}";

    try (Connection con = Conexao.obter ();
         CallableSTatement cs = con.prepareCall(sql)) {

        cs.setString(1, codigo);
        cs.setString(2, motivo);

        cs.execute();

        System.out.println("Aluno transferido com suceso!");
        System.out.println("Uma ocorrência de COMUNICADO foi registrada automaticamente.");

    } catch (Exception e) {

        System.out.println("Erro: " + e.getMessage());
      }
    }

