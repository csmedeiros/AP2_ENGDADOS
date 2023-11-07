package projeto;

import java.sql.*;

public class JDBCTest {

    public static void main(String args[]) {
        try(
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/academia", "root", "mysqlroot");
        Statement stmt = conn.createStatement();
                ){
            String consulta = "select * from Cliente";
            ResultSet resultado = stmt.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco da dados!");
        }
                }
}



