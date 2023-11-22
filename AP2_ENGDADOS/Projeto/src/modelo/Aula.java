
package modelo;

import java.time.LocalDateTime;

public class Aula {
    
    private LocalDateTime data;
    private String modalidade;
    private String instrutor;
    private String cliente;
    
    public Aula(LocalDateTime data, String modalidade, String instrutor, String cliente) {
        this.data = data;
        this.modalidade = modalidade;
        this.instrutor = instrutor;
        this.cliente = cliente;
    }
    
    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getInstrutor() {
        return instrutor;
    }
 
    public void setInstrutor(String instrutor) { 
        this.instrutor = instrutor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
