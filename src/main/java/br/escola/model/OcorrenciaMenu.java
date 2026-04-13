package br.escola.model;

import br.escola.service.Conexao;

import java.util.Scanner;
import java.sql.*;

public class OcorrenciaMenu {
    public static void listaOcorrencia()
    {
        String sql = "SELECT tipo, descricao, data_ocorrencia FROM ocorrencia";

        try (
                Connection con = Conexao.obter();      // Abre conexão com o MySQL
                Statement st = con.createStatement(); // Cria executor de SQL simples
                ResultSet rs = st.executeQuery(sql)) {// Executa a query e guarda as linhas retornadas

            System.out.println("\n--- OCORRENCIAS ---");

            // rs.next() funciona como um cursor/ponteiro:
            // começa ANTES da primeira linha, e cada chamada avança uma posição.
            // Retorna true enquanto houver linha, false quando acabar.
            while (rs.next()) {

                // printf formata a saída em colunas alinhadas:
                // %-30s = texto (s) alinhado à esquerda (-) em 30 caracteres de largura
                // %s    = texto simples sem formatação especial
                // %n    = quebra de linha (equivalente ao \n, mas compatível com qualquer SO)
                System.out.printf("%-30s | %s | %s%n",
                        rs.getString("tipo"),     // Lê o valor da coluna "tipo" da linha atual
                        rs.getString("descricao"),   // Lê o valor da coluna "codigo"
                        rs.getString("data_ocorrencia"));  // Lê o valor da coluna "status"
            }

        } catch (Exception e) {
            // Captura qualquer erro que tenha ocorrido dentro do try:
            // banco offline, senha errada, query com erro de sintaxe, etc.
            // getMessage() retorna uma descrição legível do problema.
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void registrarOcorrencia(Scanner sc) {
        // "sc Scanner" recebido do Main para ler os dados que o usuário digitar

        // Esse sc.nextLine() inicial é um "erro" clássica do Scanner!
        // Quando o usuário digita "4" e pressiona Enter no menu (Main.java),
        // o sc.nextInt() lê o "4" mas DEIXA o "\n" (Enter) no buffer.
        // Se não limparmos isso, o próximo sc.nextLine() vai ler esse "\n" vazio
        // em vez de esperar o usuário digitar o nome. Esse nextLine() "engole" o lixo.
        sc.nextLine();

        try {

            // Coletamos os dados da nova ocorrência pelo console
            System.out.print("Tipo: ");
            String tipo = sc.nextLine();

            System.out.print("Data ocorrência (AAAA-MM-DD): ");
            String data = sc.next();

            System.out.print("Descrição: ");
            String desc = sc.nextLine();

            System.out.println("Ocorrencia registrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

