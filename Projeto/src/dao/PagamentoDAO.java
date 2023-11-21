package dao;

import modelo.Pagamento;
import modelo.Funcionario;
import java.sql.*;

public class PagamentoDAO {
   private Connection connection;
    public PagamentoDAO(Connection connection) {
        this.connection = connection;
    }
    
        public void create(Pagamento pagamento) {
            try {
                String sql 
   = "INSERT INTO Pagamento (valor, data, modalidade, plano) VALUES(?, ?, ?, ?)";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstm.setFloat(1, pagamento.getValor());
                    pstm.setObject(2, pagamento.getData(), LocalDateTime.class);
                    pstm.setString(3, pagamento.getModalidade());
                    pstm.setInt(4, pagamento.getPlano());
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        // Gerente deve estar cadastrado no banco de dados
        
            public void createComGerente(Pagamento pagamento, Funcionario funcionario) {
            try {
                String sql = "INSERT INTO Pagamento (valor, data, modalidade, fk_gerente, fk_cliente, plano) VALUES(?, ?, ?, ?, ?, ?)"
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    
                    pstm.setFloat(1, pagamento.getValor());
                    pstm.setObject(2, pagamento.getData(), LocalDateTime.class);
                    pstm.setString(3, pagamento.getModalidade());
                    pstm.setString(4, funcionario.getRegistro());
                    pstm.setString(5, pagamento.getCliente());
                    pstm.setInt(6, pagamento.getPlano());   
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    
            // Cliente e Gerente devem estar cadastrados no banco de dados
            
                    public void createComGerenteComCliente(Pagamento pagamento, Funcionario funcionario, Cliente cliente) {
            try {
                String sql = "INSERT INTO Pagamento (valor, data, modalidade, fk_gerente, fk_cliente, plano) VALUES(?, ?, ?, ?, ?, ?)"
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    
                    pstm.setFloat(1, pagamento.getValor());
                    pstm.setObject(2, pagamento.getData(), LocalDateTime.class);
                    pstm.setString(3, pagamento.getModalidade());
                    pstm.setString(4, funcionario.getRegistro());
                    pstm.setString(5, cliente.getMatricula);
                    pstm.setInt(6, pagamento.getPlano());   
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
            public void remove(Pagamento pagamento) {
                try {
                    String sql = "DELETE FROM Pagamento WHERE data = ?"
                 try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        pstm.setObject(2, pagamento.getData(), LocalDateTime.class));
                        pstm.execute();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            }
       
        
            public void atualizaModalidade(Pagamento pagamento) {
                try {
                    String sql = "UPDATE Pagamento SET modalidade = ? WHERE data = ?"
                 try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        pstm.setString(1, pagamento.getModalidade());
                        pstm.setObject(2, pagamento.getData(), LocalDateTime.class));
                        pstm.execute();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            }
        
}
