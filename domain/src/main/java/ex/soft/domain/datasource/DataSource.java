package ex.soft.domain.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton класс
 *
 */

public class DataSource {

    private static final String PATH = "db.properties";
    private static Properties properties = new Properties();
    private Connection connection;

    static {
        try {
            properties.load(DataSource.class.getClassLoader().getResourceAsStream(PATH));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private DataSource(){
        initConnection();
    }

    private void initConnection(){
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static DataSource getInstance(){
        return DataSourceHolder.INSTANCE;
    }

    public Connection getConnection() {
        if (connection == null) {
            initConnection();
        }
        return connection;
    }

    public void closeQuietly(){
        try{
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Вложенный класс, кот содержит в себе единственный экземпляр DataSource
     */
    private static class DataSourceHolder{
        private static final DataSource INSTANCE = new DataSource();
        private DataSourceHolder(){}
    }

}
