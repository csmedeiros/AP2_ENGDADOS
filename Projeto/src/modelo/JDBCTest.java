package modelo;

import java.sql.*;

public class JDBCTest {

    public static void main(String args[]) throws SQLException {
        String sql = "select fk_instrutor from Aula";
        String sgbd = "mysql";
        String endereco = "localhost";
        String bd = "academia";
        String usuario = "root";
        String senha = "mysqlroot";

        Connection connection = DriverManager.getConnection(
          "jdbc:" + sgbd + "://" + endereco + "/" + bd + "?useTimezone=true&serverTimezone=UTC", usuario, senha);
        
         try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.execute();
                try (ResultSet rst = pstm.getResultSet()) {
                    while(rst.next()) {
                    String instrutor = rst.getString(1);
                    System.out.println(instrutor);
                    if(instrutor == null) {
                        System.out.println("Nulo");
                    }
                    }
                }
            } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
}
}



