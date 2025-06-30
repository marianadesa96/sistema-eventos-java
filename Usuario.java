import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private String senha;
    private List<Evento> eventosInscritos;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.eventosInscritos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void adicionarEvento(Evento evento) {
        eventosInscritos.add(evento);
    }

    public void removerEvento(Evento evento) {
        eventosInscritos.remove(evento);
    }

    public List<Evento> getEventosInscritos() {
        return eventosInscritos;
    }
}
