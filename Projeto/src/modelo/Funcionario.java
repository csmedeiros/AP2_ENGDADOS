package modelo; 

public class Funcionario {
    
    private String nome;
    private float salario;
    private String registro;
    private String cargo;
    
    public Funcionario(String nome, String cpf, float salario, String registro, String cargo) {
        this.nome = nome;
        this.salario = salario;
        this.registro = registro;
        this.cargo = cargo;
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
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return this.cpf;
    }
}