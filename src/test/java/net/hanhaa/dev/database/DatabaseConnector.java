package net.hanhaa.dev.database;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseConnector {

    private static final String USER = "parcelive_dev";
    private static final String PASSWORD = "parcelive_dev";

    private static final String DATA_SOURCE_URL;

    static {

//        boolean isWindowsOS = System.getProperty("os.name").startsWith("Windows");
//        DATA_SOURCE_URL = isWindowsOS ?
//                "jdbc:mysql://35.187.87.185:3306/parcelive_dev?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&useSSL=true" :
//                "jdbc:mysql://cloud-sql-proxy.c.g150129-01.internal/parcelive_test?autoReconnect=true&useSSL=false";

        DATA_SOURCE_URL =
                "jdbc:mysql://35.187.87.185:3306/parcelive_dev?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&useSSL=true";
        Path resourceDirectory = Paths.get("src/test/resources/truststore.jks");
        String filePath = resourceDirectory.toAbsolutePath().toString();
        System.setProperty("javax.net.ssl.trustStore", filePath);
        System.setProperty("javax.net.ssl.trustStorePassword", "123123");
    }

    public Statement createConnection() {
        Connection connection;

        try {
            connection = DriverManager
                    .getConnection(DATA_SOURCE_URL, USER, PASSWORD);

            return connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
