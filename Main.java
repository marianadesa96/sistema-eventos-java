import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaEventos sistema = new SistemaEventos();

        // Carrega eventos do arquivo
        sistema.carregarEventos();

        // Login ou cadastro do usuário
        sistema.loginOuCadastro(scanner);

        int opcao;
        do {
            System.out.println("\n--- MENU DE EVENTOS DE BARUERI ---");
            System.out.println("1. Cadastrar novo evento");
            System.out.println("2. Listar todos os eventos");
            System.out.println("3. Participar de um evento");
            System.out.println("4. Cancelar participação");
            System.out.println("5. Ver eventos que estou participando");
            System.out.println("6. Ver eventos que já ocorreram");
            System.out.println("7. Ver eventos por data/hora");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número válido: ");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    sistema.cadastrarEvento(scanner);
                    break;
                case 2:
                    sistema.listarEventos();
                    break;
                case 3:
                    sistema.participarDeEvento(scanner);
                    break;
                case 4:
                    sistema.cancelarParticipacao(scanner);
                    break;
                case 5:
                    sistema.mostrarEventosDoUsuario();
                    break;
                case 6:
                    sistema.mostrarEventosPassados();
                    break;
                case 7:
                    sistema.ordenarEventosPorDataHora();
                    break;
                case 0:
                    sistema.salvarEventos(); // aqui salva antes de sair
                    System.out.println("Saindo... Dados salvos.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
