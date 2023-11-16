
package modelo;

public class Cliente {
    private String matricula;
    private String cpf;
    private String telefone;
    private String nome;
    private String endereco;
    
    public Cliente(String matricula, String cpf, String telefone, String nome, String endereco) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.telefone = telefone;
        this.nome = nome;
        this.endereco = endereco;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}