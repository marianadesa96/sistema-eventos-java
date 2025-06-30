import java.time.LocalDateTime;

public class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private String descricao;
    private LocalDateTime dataHora;

    public Evento(String nome, String endereco, String categoria, String descricao, LocalDateTime dataHora) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Usado para salvar o evento no arquivo
    public String toDataString() {
        return nome + ";" + endereco + ";" + categoria + ";" + descricao + ";" + dataHora.toString();
    }

    // Usado para ler o evento do arquivo
    public static Evento fromDataString(String linha) {
        try {
            String[] partes = linha.split(";");
            String nome = partes[0];
            String endereco = partes[1];
            String categoria = partes[2];
            String descricao = partes[3];
            LocalDateTime dataHora = LocalDateTime.parse(partes[4]);

            return new Evento(nome, endereco, categoria, descricao, dataHora);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
               "\nEndereço: " + endereco +
               "\nCategoria: " + categoria +
               "\nDescrição: " + descricao +
               "\nData e Hora: " + dataHora;
    }
}
