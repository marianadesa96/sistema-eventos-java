import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaEventos {
    private List<Evento> eventos;
    private Usuario usuarioLogado;

    public SistemaEventos() {
        this.eventos = new ArrayList<>();
        carregarEventos();
    }

    // LOGIN OU CADASTRO
    public void loginOuCadastro(Scanner scanner) {
        System.out.println("--- Login ou Cadastro ---");
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();

        File arquivoUsuario = new File(email + ".usuario");
        if (arquivoUsuario.exists()) {
            usuarioLogado = carregarUsuario(email);
            if (usuarioLogado != null) {
                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();
                if (senha.equals(usuarioLogado.getSenha())) {
                    System.out.println("Login realizado com sucesso! Olá, " + usuarioLogado.getNome());
                } else {
                    System.out.println("Senha incorreta. Encerrando.");
                    System.exit(0);
                }
            }
        } else {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Crie uma senha: ");
            String senha = scanner.nextLine();

            usuarioLogado = new Usuario(nome, email, senha);
            salvarUsuario();
            System.out.println("Cadastro realizado com sucesso! Bem-vindo(a), " + nome);
        }
    }

    // CADASTRAR EVENTO
    public void cadastrarEvento(Scanner scanner) {
        System.out.println("--- Cadastro de Evento ---");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Data e hora (formato: yyyy-MM-ddTHH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr);

        Evento evento = new Evento(nome, endereco, categoria, descricao, dataHora);
        eventos.add(evento);
        salvarEventos();

        System.out.println("Evento cadastrado com sucesso!");
    }

    // LISTAR EVENTOS
    public void listarEventos() {
        System.out.println("--- Lista de Eventos ---");
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        for (int i = 0; i < eventos.size(); i++) {
            System.out.println("Evento #" + (i + 1));
            System.out.println(eventos.get(i));
            System.out.println("-------------------");
        }
    }

    // PARTICIPAR
    public void participarDeEvento(Scanner scanner) {
        listarEventos();
        System.out.print("Digite o número do evento que deseja participar: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (indice >= 0 && indice < eventos.size()) {
            Evento evento = eventos.get(indice);
            usuarioLogado.adicionarEvento(evento);
            salvarUsuario();
            System.out.println("Você se inscreveu no evento!");
        } else {
            System.out.println("Número inválido.");
        }
    }

    // CANCELAR PARTICIPAÇÃO
    public void cancelarParticipacao(Scanner scanner) {
        List<Evento> inscritos = usuarioLogado.getEventosInscritos();
        if (inscritos.isEmpty()) {
            System.out.println("Você não está inscrito em nenhum evento.");
            return;
        }

        for (int i = 0; i < inscritos.size(); i++) {
            System.out.println((i + 1) + ". " + inscritos.get(i).getNome());
        }

        System.out.print("Digite o número do evento para cancelar: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (indice >= 0 && indice < inscritos.size()) {
            usuarioLogado.removerEvento(inscritos.get(indice));
            salvarUsuario();
            System.out.println("Participação cancelada.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    // MOSTRAR EVENTOS QUE O USUÁRIO ESTÁ INSCRITO
    public void mostrarEventosDoUsuario() {
        System.out.println("--- Seus Eventos ---");
        List<Evento> inscritos = usuarioLogado.getEventosInscritos();
        if (inscritos.isEmpty()) {
            System.out.println("Você não está participando de nenhum evento.");
        } else {
            for (Evento e : inscritos) {
                System.out.println(e);
                System.out.println("-----------");
            }
        }
    }

    // MOSTRAR EVENTOS QUE JÁ PASSARAM
    public void mostrarEventosPassados() {
        System.out.println("--- Eventos Já Ocorridos ---");
        LocalDateTime agora = LocalDateTime.now();
        for (Evento evento : eventos) {
            if (evento.getDataHora().isBefore(agora)) {
                System.out.println(evento);
                System.out.println("-----------");
            }
        }
    }

    // ORDENAR POR DATA E HORA
    public void ordenarEventosPorDataHora() {
        eventos.sort(Comparator.comparing(Evento::getDataHora));
        System.out.println("Eventos ordenados por data e hora:");
        listarEventos();
    }

    // SALVAR E CARREGAR EVENTOS
    public void salvarEventos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.data"))) {
            for (Evento evento : eventos) {
                writer.write(evento.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public void carregarEventos() {
        File arquivo = new File("events.data");
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Evento evento = Evento.fromDataString(linha);
                if (evento != null) {
                    eventos.add(evento);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar eventos: " + e.getMessage());
        }
    }

    // SALVAR E CARREGAR USUÁRIO
    private void salvarUsuario() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(usuarioLogado.getEmail() + ".usuario"))) {
            out.writeObject(usuarioLogado);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    private Usuario carregarUsuario(String email) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(email + ".usuario"))) {
            return (Usuario) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar usuário: " + e.getMessage());
            return null;
        }
    }
}
