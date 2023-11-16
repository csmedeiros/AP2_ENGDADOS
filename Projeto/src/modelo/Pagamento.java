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
    
}
