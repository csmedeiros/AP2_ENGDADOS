package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreation {

  public Connection criaConexao() {
    try {
      String sgbd = "mysql";
      String endereco = "localhost";
      String bd = "agenda";
      String usuario = "root";
      String senha = "mysqlroot";

      Connection connection = DriverManager.getConnection(
          "jdbc:" + sgbd + "://" + endereco + "/" + bd + "?useTimezone=true&serverTimezone=UTC", usuario, senha);

      return connection;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}