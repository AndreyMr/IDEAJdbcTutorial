package db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBH2Connection {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USERNAME = "ss";
    private static final String DB_PASSWORD = "";

    private static final Logger logger = LoggerFactory.getLogger(DBH2Connection.class.getName());

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            logger.info("Connection success");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return connection;
    }
}
