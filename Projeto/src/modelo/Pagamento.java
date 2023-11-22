package modelo;

import java.time.LocalDateTime;

public class Pagamento {
    float valor;
    LocalDateTime data;
    String modalidade;
    String gerente;
    String cliente;
    int plano;
    public Pagamento(float valor, LocalDateTime data, String modalidade, String gerente, String cliente, int plano) {
        this.valor = valor;
        this.data = data;
        this.modalidade = modalidade;
        this.gerente = gerente;
        this.cliente = cliente;
        this.plano = plano;
    }
    
    public float getValor() {
        return this.valor;
    }
    
    public void setValor(float valor) {
        this.valor = valor;
    }
    public LocalDateTime getData() {
        return this.data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public String getModalidade() {
        return this.modalidade;
    }
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
    public String getGerente() {
        return this.gerente;
    }
    public void setGerente(String gerente) {
        this.gerente = gerente;
    }
    public String getCliente() {
        return this.cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public int getPlano() {
        return this.plano;
    }
    public void setPlano(int plano) {
        this.plano = plano;
    }
    
}
