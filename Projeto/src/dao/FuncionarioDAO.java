/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import modelo.Funcionario;

public class FuncionarioDAO {
    
    Connection connection;
    
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }
    
public void create(Funcionario funcionario) {
        try {
            String sql = "INSERT INTO Funcionario (nome, salario, cargo, registro) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, funcionario.getNome());
                pstm.setFloat(2, funcionario.getSalario());
                pstm.setString(3, funcionario.getCargo());
                pstm.setString(4, funcionario.getRegistro());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}