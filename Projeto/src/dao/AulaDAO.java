package dao;

import java.sql.*;
import modelo.Aula;
import modelo.Instrutor;
import modelo.Cliente;

public class AulaDAO {
    
    private Connection connection;
    
    public AulaDAO(Connection connection) {
        this.connection = connection;
    }
 public void create(Aula aula) {
        try {
            String sql = "INSERT INTO Cliente (data, modalidade) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, aula.getData());
                pstm.setString(2, aula.getModalidade());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
 
 // A aula já deve estar cadastrada no banco de dados
 
  public void atualizaInstrutor(Aula aula, Instrutor instrutor) {
        try {
            String sql = "INSERT INTO Aula (data, modalidade, instrutor) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, aula.getData());
                pstm.setString(2, aula.getModalidade());
                pstm.setString(3, aula.getInstrutor());
                pstm.setString(5, aula.getCliente());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void atualizaCliente(Aula aula, Cliente cliente) {
        try {
            String sql = "INSERT INTO Aula (data, modalidade, instrutor) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, aula.getData());
                pstm.setString(2, aula.getModalidade());
                pstm.setString(3, aula.getInstrutor());
                pstm.setString(5, aula.getCliente());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}