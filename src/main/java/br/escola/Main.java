package br.escola;

import br.escola.model.AlunoMenu;
import br.escola.model.GestaoMenu;
import br.escola.util.PaginasMenu;

import java.util.Scanner;

import static br.escola.model.AtividadeMenu.listarAtividades;
import static br.escola.model.TurmaMenu.listarTurmas;
import static br.escola.model.TurmaMenu.verRelatorioTurma;

public class Main {
    // =========================================================================
    //                               Classe Main
    // =========================================================================
    // Controla o fluxo de navegação entre os módulos do sistema.
    // A exibição dos menus foi separada para a classe MenuPrincipal,
    // deixando o Main responsável apenas por ler a opção e chamar o metodo certo.

    static void main() throws Exception {

        Scanner sc = new Scanner(System.in);
        int modulo = -1;

        // Loop principal — roda até o usuário escolher 0 (Sair)
        while (modulo != 0) {

            PaginasMenu.exibir();  // Exibe o menu principal
            modulo = sc.nextInt();   // Lê o módulo que o usuário escolher

            switch (modulo) {
                case 1 -> navegarAlunos(sc);
                case 2 -> navegarProfessores(sc);
                case 3 -> navegarTurmas(sc);
                case 4 -> navegarOcorrencias(sc);
                case 5 -> navegarAtividades(sc);
                case 6 -> navegarGestao(sc);
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Módulo inválido.");
            }
        }
    }

    // Métodos de navegação de cada módulo
    // Cada metodo exibe o submenu do módulo e fica em loop
    // Quando o usuário digitar 0, volta ao menu principal.

    private static void navegarAlunos(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            PaginasMenu.exibirAlunos();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> AlunoMenu.listarAlunos();
                case 2 -> AlunoMenu.verBoletim(sc);
                case 3 -> AlunoMenu.alunosEmRisco();
                case 4 -> AlunoMenu.cadastrarAluno(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void navegarProfessores(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            PaginasMenu.exibirProfessores();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> System.out.println("FUNÇÃO EM CONSTRUÇÃO");
                case 2 -> System.out.println("FUNÇÃO EM CONSTRUÇÃO");
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void navegarTurmas(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            PaginasMenu.exibirTurmas();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> listarTurmas();
                case 2 -> verRelatorioTurma(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void navegarOcorrencias(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            PaginasMenu.exibirOcorrencias();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> System.out.println("FUNÇÃO EM CONSTRUÇÃO");
                case 2 -> System.out.println("FUNÇÃO EM CONSTRUÇÃO");
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void navegarAtividades(Scanner sc) throws Exception {
        int opcao = -1;
        while (opcao != 0) {
            PaginasMenu.exibirAtividades();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> listarAtividades();
                case 2 -> System.out.println("FUNÇÃO EM CONSTRUÇÃO");
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void navegarGestao(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            PaginasMenu.exibirGestao();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> GestaoMenu.transferirAluno(sc);
                case 2 -> GestaoMenu.fecharAnoLetivo();
                case 3 -> System.out.println("FUNÇÃO EM CONSTRUÇÃO");
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}