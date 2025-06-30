import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ListaEvento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Evento> eventos = carregarEventosDoArquivo();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Cadastrar novo evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            if (opcao.equals("1")) {
                Evento novo = cadastrarEvento(sc);
                eventos.add(novo);
                salvarEventosEmArquivo(eventos);
            } else if (opcao.equals("2")) {
                listarEventos(eventos);
            } else if (opcao.equals("3")) {
                System.out.println("Saindo... 👋");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }

    public static Evento cadastrarEvento(Scanner sc) {
        String nome, endereco, categoria, descricao, dataStr, horaStr;

        // Nome
        while (true) {
            System.out.print("Nome do evento: ");
            nome = sc.nextLine();
            System.out.println("Você digitou: \"" + nome + "\". Está certo? (s/n)");
            if (sc.nextLine().trim().equalsIgnoreCase("s")) break;
        }

        // Endereço
        while (true) {
            System.out.print("Endereço do evento: ");
            endereco = sc.nextLine();
            System.out.println("Você digitou: \"" + endereco + "\". Está certo? (s/n)");
            if (sc.nextLine().trim().equalsIgnoreCase("s")) break;
        }

        // Categoria
        while (true) {
            System.out.print("Categoria do evento: ");
            categoria = sc.nextLine();
            System.out.println("Você digitou: \"" + categoria + "\". Está certo? (s/n)");
            if (sc.nextLine().trim().equalsIgnoreCase("s")) break;
        }

        // Descrição
        while (true) {
            System.out.print("Descrição do evento: ");
            descricao = sc.nextLine();
            System.out.println("Você digitou: \"" + descricao + "\". Está certo? (s/n)");
            if (sc.nextLine().trim().equalsIgnoreCase("s")) break;
        }

        // Data
        while (true) {
            System.out.print("Data do evento (ex: 2025-07-26): ");
            dataStr = sc.nextLine();
            System.out.println("Você digitou: \"" + dataStr + "\". Está certo? (s/n)");
            if (sc.nextLine().trim().equalsIgnoreCase("s")) break;
        }

        // Hora
        while (true) {
            System.out.print("Hora do evento (ex: 20h ou 14h30): ");
            horaStr = sc.nextLine();
            System.out.println("Você digitou: \"" + horaStr + "\". Está certo? (s/n)");
            if (sc.nextLine().trim().equalsIgnoreCase("s")) break;
        }

        horaStr = horaStr.replace("h", ":");
        if (horaStr.endsWith(":")) horaStr += "00";

        LocalDate data = LocalDate.parse(dataStr);
        LocalTime hora = LocalTime.parse(horaStr);
        LocalDateTime dataHora = LocalDateTime.of(data, hora);

        return new Evento(nome, endereco, categoria, descricao, dataHora);
    }

    public static void listarEventos(List<Evento> eventos) {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n===== Lista de Eventos =====");
        for (Evento e : eventos) {
            System.out.println("Nome: " + e.getNome());
            System.out.println("Endereço: " + e.getEndereco());
            System.out.println("Categoria: " + e.getCategoria());
            System.out.println("Descrição: " + e.getDescricao());
            System.out.println("Data e Hora: " + e.getDataHora().format(formatter));
            System.out.println("---------------------------");
        }
    }

    public static void salvarEventosEmArquivo(List<Evento> eventos) {
        try (FileWriter writer = new FileWriter("eventos.txt")) {
            for (Evento e : eventos) {
                writer.write(e.toDataString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar os eventos: " + e.getMessage());
        }
    }

    public static List<Evento> carregarEventosDoArquivo() {
        List<Evento> eventos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("eventos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                eventos.add(Evento.fromDataString(linha));
            }
        } catch (IOException e) {
            System.out.println("⚠️ Nenhum arquivo encontrado ou erro ao carregar eventos.");
        }
        return eventos;
    }
}
