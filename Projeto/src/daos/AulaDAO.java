/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.*;
import projeto.Aula;
import projeto.Cliente;
import projeto.Instrutor;

/**
 *
 * @author 202203369008
 */
public class AulaDAO {
    
    private Connection connection;
    
    public AulaDAO(Connection connection) {
        this.connection = connection;
    }
    public void cadastraAula(Aula aula, Cliente cliente, Instrutor instrutor) {        
        try {
            String sql = "INSERT INTO Aula (data, modalidade, fk_instrutor, id, fk_cliente) values(?, ?, ?, ?, ?)";
                    try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                   
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        // Codigo para pegar o resultado da consulta
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}