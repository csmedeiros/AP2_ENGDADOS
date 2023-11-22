
package modelo;

public class Instrutor {
    private String nome;
    private float salario;
    private String registro;
    private String cpf;
    
    public Instrutor(String nome, String cpf, float salario, String registro){
        this.nome = nome;
        this.salario = salario;
        this.registro = registro;
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getCpf() {
        return cpf;
    }
}