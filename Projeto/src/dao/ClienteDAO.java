package dao;

import java.sql.*;
import modelo.Cliente;
import java.util.ArrayList;

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
    
        public void remove(Cliente cliente) {
        try {
            String sql = "DELETE FROM Cliente WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, cliente.getMatricula());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
        // Cliente já deve estar cadastrado no banco
        
            public void atualizaTelefone(Cliente cliente) {
        try {
            String sql = "UPDATE Cliente telefone = ? WHERE registro = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, cliente.getTelefone());
                pstm.setString(2, cliente.getMatricula());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
            
            public ArrayList<Cliente> retrieveAll() {
                ArrayList<Cliente> clientes = new ArrayList<>();
                Cliente ultimoCliente = null;
                try {
                    String sql = "SELECT * FROM Cliente";
                    try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                        pstm.execute();
                        try(ResultSet rst = pstm.getResultSet()) {
                            while(rst.next()) {
                                if(ultimoCliente == null || !ultimoCliente.getMatricula().equals(rst.getString(1))) {
                                    String c_matricula = rst.getString(1);
                                    String c_cpf = rst.getString(2);
                                    String c_telefone = rst.getString(3);
                                    String c_nome = rst.getString(4);
                                    String c_endereco = rst.getString(5);
                                    Cliente c = new Cliente(c_matricula, c_cpf, c_telefone, c_nome, c_endereco);
                                    ultimoCliente = c;
                                    clientes.add(c);
                                }
                            }
                            return clientes;
                        }
                    }
                } catch(SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            
            public ArrayList<String> retrieveTelefones() {
                ArrayList<String> telefones = new ArrayList<>();
                try {
                    String sql = "SELECT DISTINCT telefone from Cliente";
                    try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                        pstm.execute();
                        try(ResultSet rst = pstm.getResultSet()) {
                            while(rst.next()) {
                                String t = rst.getString(1);
                                telefones.add(t);
                            }
                            return telefones;
                        }
                    }
                }   catch(SQLException e) {
                    throw new RuntimeException(e);
                }
            }
}
