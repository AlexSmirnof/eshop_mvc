package ex.soft.domain.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alex108 on 11.10.2016.
 */
public class Executor {

    public static final String GET = "SELECT * FROM %s WHERE id = %d";
    public static final String SAVE = "INSERT INTO %s VALUES (%s)";
    public static final String FIND_ALL = "SELECT * FROM %s";

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public int execUpdate(String update){
        int res = 0;
        Statement stmt;
        try {
            stmt = connection.createStatement();
            res = stmt.executeUpdate(update);
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return res;
    }

    public <T> T execQuery(String query, IResultHandler<T> handler)  {
        Statement stmt;
        T value = null;
        try {
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            value = handler.handle(resultSet);
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return value;
    }

    public interface IResultHandler<T> {
        T handle(ResultSet resultSet) throws SQLException;
    }
}
