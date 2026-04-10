package br.escola;

import java.util.Scanner;

/**
 * Classe Main
 * -----------
 * Ponto de entrada do programa.
 * Controla o fluxo de navegação entre os módulos do sistema.
 * A exibição dos menus foi separada para a classe MenuPrincipal,
 * deixando o Main responsável apenas por ler a opção e chamar o método certo.
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int modulo = -1;

        // Loop principal — roda até o usuário escolher 0 (Sair)
        while (modulo != 0) {

            MenuPrincipal.exibir();  // exibe o menu principal
            modulo = sc.nextInt();   // lê qual módulo o usuário quer acessar

            switch (modulo) {
                case 1 -> navegarAlunos(sc);
                //case 2 -> navegarProfessores(sc);
                //case 3 -> navegarTurmas(sc);
                //case 4 -> navegarOcorrencias(sc);
                //case 5 -> navegarAtividades(sc);
                //case 6 -> navegarGestao(sc);
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Módulo inválido.");
            }
        }
    }

    // ── Métodos de navegação de cada módulo ─────────────────────────────────
    // Cada metodo exibe o submenu do módulo e fica em loop
    // até o usuário digitar 0 para voltar ao menu principal.

    private static void navegarAlunos(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            MenuPrincipal.exibirAlunos();
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

    /**private static void navegarProfessores(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            MenuPrincipal.exibirProfessores();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> ProfessorMenu.listarProfessores();
                case 2 -> ProfessorMenu.verCargaProfessor(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }**/

    /**private static void navegarTurmas(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            MenuPrincipal.exibirTurmas();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> TurmaMenu.listarTurmas();
                case 2 -> TurmaMenu.verRelatorioTurma(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }**/

    /**private static void navegarOcorrencias(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            MenuPrincipal.exibirOcorrencias();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> OcorrenciaMenu.listarOcorrencias();
                case 2 -> OcorrenciaMenu.registrarOcorrencia(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }**/

    /**private static void navegarAtividades(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            MenuPrincipal.exibirAtividades();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> AtividadeMenu.listarAtividades();
                case 2 -> AtividadeMenu.cadastrarAtividade(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }**/

    /**private static void navegarGestao(Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            MenuPrincipal.exibirGestao();
            opcao = sc.nextInt();
            switch (opcao) {
                case 1 -> GestaoMenu.transferirAluno(sc);
                case 2 -> GestaoMenu.concluirAluno(sc);
                case 3 -> GestaoMenu.fecharAnoLetivo(sc);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }**/
}