package DATABASE;

import com.mysql.jdbc.*;
import java.sql.*;

public class DatabaseOperationsContacts  {
    private  Connection connection;
    private  Statement statement;
    private final String connectionUrl = "jdbc:mysql://localhost:3306/skyvoyage";

    public DatabaseOperationsContacts() throws SQLException {
        connect();
    }

    public Connection connect() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.connection = DriverManager.getConnection(this.connectionUrl, "root", "0778330080@Sl");
            this.statement = this.connection.createStatement();
            System.out.println("Database connected...");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        try {
//            new   DatabaseOperationsContacts();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
