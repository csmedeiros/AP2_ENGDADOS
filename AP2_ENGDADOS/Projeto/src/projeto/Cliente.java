package projeto;

public class Cliente {

    private int id;
    private String nome;
    private int idade;
    private String email;

    // Construtor
    public Cliente(int id, String nome, int idade, String email) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    // Métodos de acesso (getters)
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }
}
