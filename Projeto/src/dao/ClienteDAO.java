package dao;

import java.sql.*;
import modelo.Cliente;

public class ClienteDAO {
    
    Connection connection;
    
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void create(Cliente cliente) {
        try {
            String sql = "INSERT INTO Cliente (matricula, cpf, telefone, nome, endereco) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, cliente.getMatricula());
                pstm.setString(2, cliente.getCpf());
                pstm.setString(3, cliente.getTelefone());
                pstm.setString(4, cliente.getNome());
                pstm.setString(5, cliente.getEndereco());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
