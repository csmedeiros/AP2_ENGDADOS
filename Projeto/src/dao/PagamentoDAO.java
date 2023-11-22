package dao;

import modelo.Pagamento;
import modelo.Funcionario;
import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class PagamentoDAO {
   private Connection connection;
    public PagamentoDAO(Connection connection) {
        this.connection = connection;
    }


        // Cliente e Gerente devem estar cadastrados no banco de dados

        public void createComGerenteComCliente(Pagamento pagamento, Funcionario funcionario, Cliente cliente) {
            try {
                String sql = "INSERT INTO Pagamento (valor, data, modalidade, fk_gerente, fk_cliente, plano) VALUES(?, ?, ?, ?, ?, ?)";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                    pstm.setFloat(1, pagamento.getValor());
                    pstm.setObject(2, pagamento.getData());
                    pstm.setString(3, pagamento.getModalidade());
                    pstm.setString(4, funcionario.getRegistro());
                    pstm.setString(5, cliente.getMatricula());
                    pstm.setInt(6, pagamento.getPlano());   
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void remove(Pagamento pagamento) {
            try {
                String sql = "DELETE FROM Pagamento WHERE data = ?";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstm.setObject(2, pagamento.getData());
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void atualizaModalidade(Pagamento pagamento) {
            try {
                String sql = "UPDATE Pagamento SET modalidade = ? WHERE data = ?";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstm.setString(1, pagamento.getModalidade());
                    pstm.setObject(2, pagamento.getData());
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public ArrayList<Pagamento> retrieveAll() {

            ArrayList<Pagamento> pagamentos = new ArrayList<>();
            Pagamento ultimoPagamento = null;
            Cliente ultimoCliente = null;
            Funcionario ultimoFuncionario = null;

            try {

                String sql = "SELECT p.data, p.valor, p.modalidade, p.fk_cliente, p.fk_gerente, p.plano, c.matricula, c.cpf, c.telefone, c.nome, c.endereco, f.registro, f.nome, f.cpf, f.salario, f.cargo"
                        + "FROM Pagamento AS p"
                        + "LEFT JOIN Cliente AS c ON c.matricula = p.fk_cliente"
                        + "LEFT JOIN Funcionario AS f ON f.registro = p.fk_gerente";

                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.execute();

                    try (ResultSet rst = pstm.getResultSet()) {
                        while (rst.next()) {
                            if (ultimoPagamento == null || ultimoPagamento.getData() != rst.getObject(1)) {
                                LocalDateTime p_data = rst.getObject(1, LocalDateTime.class);
                                float p_valor = rst.getFloat(2);
                                String p_modalidade = rst.getString(3);
                                String p_cliente = rst.getString(4);
                                String p_funcionario = rst.getString(5);
                                int p_plano = rst.getInt(6);
                                Pagamento p = new Pagamento(p_valor, p_data, p_modalidade, p_funcionario, p_cliente, p_plano);
                                pagamentos.add(p);
                                ultimoPagamento = p;

                            }

                            if ((rst.getString(7)!=null) && (ultimoCliente == null || !ultimoCliente.getMatricula().equals(rst.getString(7)))) {
                                String c_matricula = rst.getString(7);
                                String c_cpf = rst.getString(8);
                                String c_telefone = rst.getString(9);
                                String c_nome = rst.getString(10);
                                String c_endereco = rst.getString(11);
                                Cliente c = new Cliente(c_matricula, c_cpf, c_telefone, c_nome, c_endereco);
                                ultimoCliente = c;
                            }

                            if (rst.getString(12) != null && (ultimoFuncionario == null || !ultimoFuncionario.getRegistro().equals(rst.getString(12)))) {
                                String f_registro = rst.getString(12);
                                String f_nome = rst.getString(13);
                                String f_cpf = rst.getString(14);
                                float f_salario = rst.getFloat(15);
                                String f_cargo = rst.getString(16);
                                Funcionario f = new Funcionario(f_nome, f_cpf, f_salario, f_cargo, f_registro);
                                ultimoFuncionario = f;
                            }
                        }
                    }
                    return pagamentos;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        public ArrayList<LocalDateTime> retrieveDatas() {
            
            ArrayList<LocalDateTime> datas = new ArrayList<>();
            try {
                String sql = "SELECT DISTINCT data FROM Pagamento";
                
                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.execute();
                   try (ResultSet rst = pstm.getResultSet()){
                       while(rst.next()) {
                           datas.add(rst.getObject(1, LocalDateTime.class));
                           System.out.println(rst.getObject(1, LocalDateTime.class).toString());
                       }
                       return datas;
                   }
                }
            } catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }



}
