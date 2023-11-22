package dao;

import java.sql.*;
import modelo.Funcionario;
import java.util.ArrayList;

public class FuncionarioDAO {
    
    Connection connection;
    
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }
    
public void create(Funcionario funcionario) {
        try {
            String sql = "INSERT INTO Funcionario (nome, cpf, salario, cargo, registro) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, funcionario.getNome());
                pstm.setString(2, funcionario.getCpf());
                pstm.setFloat(3, funcionario.getSalario());
                pstm.setString(4, funcionario.getCargo());
                pstm.setString(5, funcionario.getRegistro());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

public void remove(Funcionario funcionario) {
        try {
            String sql = "DELETE FROM Funcionario WHERE registro = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, funcionario.getRegistro());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSalario(Funcionario funcionario) {
            try {
                String sql 
   = "UPDATE Funcionario SET salario = ? WHERE registro = ?";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstm.setFloat(2, funcionario.getSalario());
                    pstm.setString(4, funcionario.getRegistro());
                    pstm.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public void updateCargo(Funcionario funcionario) {
            try {
                String sql = "UPDATE Funcionario SET cargo = ? WHERE registro = ?";
             try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    pstm.setString(1, funcionario.getCargo());
                    pstm.setString(2, funcionario.getRegistro());
                    pstm.execute();
                    }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public ArrayList<Funcionario> retrieveAll() {
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            Funcionario ultimoFuncionario = null;
            
            try {
                String sql = "SELECT * FROM Funcionario";
                
                try(PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.execute();
                    try(ResultSet rst = pstm.getResultSet()) {
                        while(rst.next()) {
                            if(ultimoFuncionario == null || !ultimoFuncionario.getRegistro().equals(rst.getString(5))) {
                                String f_nome = rst.getString(1);
                                String f_cpf = rst.getString(2);
                                float f_salario = rst.getFloat(3);
                                String f_cargo = rst.getString(4);
                                String f_registro = rst.getString(5);
                                Funcionario f = new Funcionario(f_nome, f_cpf, f_salario, f_cargo, f_registro);
                                ultimoFuncionario = f;
                            }
                        }
                        return funcionarios;
                    }
                }
            }   catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        public ArrayList<Object> retrieveSalarios() {
            
            ArrayList<Object> salarios = new ArrayList<>();
            
            try {
                String sql = "SELECT salario FROM Funcionario";
                try(PreparedStatement pstm = connection.prepareStatement(sql)){
                    pstm.execute();
                    try(ResultSet rst = pstm.getResultSet()) {
                        while(rst.next()) {
                        float s = rst.getFloat(1);
                        salarios.add(s);
                        System.out.println(s);
                        }
                        return salarios;
                    }
                }
            }   catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }
}