package dao;

import java.sql.*;
import modelo.Instrutor;

public class InstrutorDAO {
    
    private Connection connection;
    
    public InstrutorDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void create(Instrutor instrutor) {
        try {
            String sql = "INSERT INTO Instrutor (nome, salario, registro, cpf) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, instrutor.getNome());
                pstm.setFloat(2, instrutor.getSalario());
                pstm.setString(4, instrutor.getRegistro());
                pstm.setString(3, instrutor.getCpf());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
        public void remove(Instrutor instrutor) {
        try {
            String sql = "DELETE FROM Instrutor WHERE registro = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(4, instrutor.getRegistro());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
            public void atualizaSalario(Instrutor instrutor) {
        try {
            String sql = "UPDATE Instrutor SET salario = ? WHERE registro = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setFloat(1, funcionario.getSalario());
                pstm.setString(2, funcionario.getRegistro());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
