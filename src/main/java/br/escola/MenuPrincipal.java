package br.escola;

// =========================================================================
//                           Classe "MenuPrincipal.java"
// =========================================================================

// Responsável APENAS por exibir os menus na tela.
// Exibição separada do menu da lógica de execução.
// Assim o Main fica limpo e cada seção do menu é fácil de encontrar e editar.

public class MenuPrincipal {

    // Exibe o menu principal com todas as seções disponíveis.
    // Chamado no início de cada volta do loop no Main.

    public static void exibir() {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║     SISTEMA ESCOLAR      ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  [1] Alunos              ║");
        System.out.println("║  [2] Professores         ║");
        System.out.println("║  [3] Turmas              ║");
        System.out.println("║  [4] Ocorrências         ║");
        System.out.println("║  [5] Atividades          ║");
        System.out.println("║  [6] Gestão              ║");
        System.out.println("║  [0] Sair                ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("Escolha o módulo: ");
    }

    public static void exibirAlunos() {
        System.out.println("\n--- ALUNOS ---");
        System.out.println("1. Listar alunos");
        System.out.println("2. Ver boletim");
        System.out.println("3. Alunos em risco");
        System.out.println("4. Cadastrar aluno");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }

    public static void exibirProfessores() {
        System.out.println("\n--- PROFESSORES ---");
        System.out.println("1. Listar professores");
        System.out.println("2. Ver carga de um professor");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }

    public static void exibirTurmas() {
        System.out.println("\n--- TURMAS ---");
        System.out.println("1. Listar turmas");
        System.out.println("2. Ver relatório de uma turma");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }

    public static void exibirOcorrencias() {
        System.out.println("\n--- OCORRÊNCIAS ---");
        System.out.println("1. Listar ocorrências");
        System.out.println("2. Registrar ocorrência");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }

    public static void exibirAtividades() {
        System.out.println("\n--- ATIVIDADES EXTRACURRICULARES ---");
        System.out.println("1. Listar atividades");
        System.out.println("2. Cadastrar atividade");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }

    public static void exibirGestao() {
        System.out.println("\n--- GESTÃO ---");
        System.out.println("1. Transferir aluno");
        System.out.println("2. Concluir aluno");
        System.out.println("3. Fechar ano letivo");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }
}
