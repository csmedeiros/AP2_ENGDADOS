/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.*;

/**
 *
 * @author 202203369008
 */
public class GerenteDAO {
    
    private Connection connection;
    
    public GerenteDAO(Connection connection) {
        this.connection = connection;
    }
    
    public int registrarPagamento() {
        return 0;
    }
    
    public String gerarRelatorio() {
        return "";
    }
    
}
