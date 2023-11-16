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
    
   // A aula já deve estar cadastrada no banco de dados
  
    public void atualizaCliente(Aula aula, Cliente cliente) {
        try {
            String sql = "INSERT INTO Aula (fk_cliente)VALUES (?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, cliente.getMatricula());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      public void remove(Aula aula) {
        try {
            String sql = "INSERT INTO Aula (data, modalidade, instrutor) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      
      public ArrayList<Aula> retriveAllComPessoaComTelefone() {

        ArrayList<Aula> aulas = new ArrayList<Aula>();
        Aula ultimaAula = null;
        Cliente ultimoCliente = null;
        Instrutor ultimoInstrutor = null;

        try {

            String sql = "SELECT e.id, e.data_evento, e.descricao, p.id, p.nome, p.cpf, p.data_nascimento, p.idade, t.id, t.tipo, t.codigo_pais, t.codigo_area, t.numero "
                    + "FROM evento AS e "
                    + "LEFT JOIN participacao AS ep ON ep.fk_evento = e.id "
                    + "LEFT JOIN pessoa AS p ON ep.fk_pessoa = p.id "
                    + "LEFT JOIN telefone AS t ON p.id = t.fk_pessoa";

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

                        if ((rst.getString(4)!=null) && (ultimoInstrutor == null || !ultimoInstrutor.getRegistro().equals(rst.getString(4)))) {
                            String i_registro = rst.getString(4);
                            String i_nome = rst.getString(5);
                            float i_salario = rst.getFloat(6);
                            String i_cpf = rst.getString(7);
                            Instrutor i = new Instrutor(i_nome, i_salario, i_registro, i_cpf);
                            ultimoInstrutor = i;
                        }

                        if (rst.getString(9) != null) {
                            String c_matricula = rst.getString(9);
                            String c_cpf = rst.getString(10);
                            String c_telefone = rst.getString(11);
                            String c_nome = rst.getString(12);
                            String c_endereco = rst.getString(13);
                            Cliente c = new Cliente(c_matricula, c_cpf, c_telefone, c_nome, c_endereco);
                            ultimoCliente = c;
                        }
                    }
                }
                return aulas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}