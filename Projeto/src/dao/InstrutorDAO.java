package dao;

import java.sql.*;
import modelo.Instrutor;
import java.util.ArrayList;

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
                    pstm.setFloat(1, instrutor.getSalario());
                    pstm.setString(2, instrutor.getRegistro());
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public ArrayList<Instrutor> retrieveAll() {
            ArrayList<Instrutor> instrutores = new ArrayList<>();
            Instrutor ultimoInstrutor = null;
            try {
                String sql = "SELECT * FROM Instrutor";
                try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.execute();
                    try(ResultSet rst = pstm.getResultSet()) {
                        while(rst.next()) {
                            if(ultimoInstrutor == null || !ultimoInstrutor.getRegistro().equals(rst.getString(4))) {
                                String i_nome = rst.getString(1);
                                String i_cpf = rst.getString(2);
                                float i_salario = rst.getFloat(3);
                                String i_registro = rst.getString(4);
                                Instrutor i = new Instrutor(i_nome, i_cpf, i_salario, i_registro);
                                ultimoInstrutor = i;
                                instrutores.add(i);
                            }
                        }
                        return instrutores;
                    }
                }
            }   catch(SQLException e) {
               throw new RuntimeException(e);
            }
        }

        public ArrayList<Object> retrieveSalarios() {
            ArrayList<Object> salarios = new ArrayList<>();

            try {
                String sql = "SELECT salario FROM Instrutor";
                try(PreparedStatement pstm = connection.prepareStatement(sql)){
                    pstm.execute();
                    try(ResultSet rst = pstm.getResultSet()) {
                        while(rst.next()) {
                        float s = rst.getFloat(1);
                        salarios.add(s);
                        }
                        return salarios;
                    }
                }
            }   catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
    
}
