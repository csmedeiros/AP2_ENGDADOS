package dao;

import java.sql.*;
import modelo.Aula;
import modelo.Instrutor;
import modelo.Cliente;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class AulaDAO {
    
    private Connection connection;
    
    public AulaDAO(Connection connection) {
        this.connection = connection;
    }
 public void create(Aula aula) {
        try {
            String sql = "INSERT INTO Aula (data, modalidade) VALUES (?, ?);";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, aula.getData());
                pstm.setString(2, aula.getModalidade());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
 
 // A aula e o instrutor já devem estar cadastrados no banco de dados
 
  public void atualizaInstrutor(Aula aula, Instrutor instrutor) {
        try {
            String sql = "UPDATE Aula SET instrutor = ? WHERE data = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, aula.getData());
                pstm.setString(2, instrutor.getRegistro());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
   // A aula e o cliente já devem estar cadastrados no banco de dados
  
    public void atualizaCliente(Aula aula, Cliente cliente) {
        try {
            String sql = "UPDATE Aula SET fk_cliente = ? WHERE data = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setObject(1, aula.getData());
                pstm.setString(2, cliente.getMatricula());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      public void remove(Aula aula) {
        try {
            String sql = "DELETE FROM Aula WHERE data = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setObject(1, aula.getData());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      
      public ArrayList<Aula> retrieveAll() {

        ArrayList<Aula> aulas = new ArrayList<>();
        Aula ultimaAula = null;
        try {

            String sql = "SELECT a.data, a.modalidade, a.fk_instrutor, a.fk_cliente, i.registro, i.nome, i.salario, i.cpf, c.matricula, c.cpf, c.telefone, c.nome, c.endereco FROM Aula AS a LEFT JOIN Cliente AS c ON a.fk_cliente = c.matricula LEFT JOIN Instrutor AS i ON a.fk_instrutor = i.registro";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimaAula == null || ultimaAula.getData() != rst.getObject(1)) {
                            LocalDateTime a_data = rst.getObject(1, LocalDateTime.class);
                            String a_modalidade = rst.getString(2);
                            String a_instrutor = rst.getString(3);
                            String a_cliente = rst.getString(4);
                           Aula a = new Aula(a_data, a_modalidade, a_instrutor, a_cliente);
                            aulas.add(a);
                            ultimaAula = a;
                        }
                    }
                }
                return aulas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      
      public ArrayList<String> retrieveClientes() {
          
        ArrayList<String> clientes = new ArrayList<>();
          
        try {
            String sql = "SELECT DISTINCT fk_cliente FROM Aula";
            try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                try (ResultSet rst = pstm.getResultSet()) {
                    while(rst.next()) {
                    if(rst.getString(1)!=null) {
                    clientes.add(rst.getString(1));
                        }
                    }
                    return clientes;
                }
            }
        }   catch(SQLException e) {
                throw new RuntimeException(e);
          }
      }
      
}