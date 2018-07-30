package com.evomatik.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author irvin
 * @version 1.0
 * @since Julio 2018
 * Clase para crear una conexion jdbc con una base de datos
 */

public class JDBCConnection {

    private Connection connection = null;

    /**
     * Constructor por default el cual establece la conexion a la base de datos
     * @param dbUrl Url de acceso a la base de datos
     * @param user Nombre del usuario para conectarse a la base de datos
     * @param claveAcceso Password para accesar a la base de datos
     * @param ssl Bandera para determinar si la conexion es SSL
     */
    public JDBCConnection(String dbUrl, String user, String claveAcceso, boolean ssl) {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", claveAcceso);
//        properties.setProperty("ssl", ssl?"true":"false");
        try {
            connection = DriverManager.getConnection(dbUrl, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Devuelve la conexion a la base de datos
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Metodo encargado de cerrar una conexion de base de datos abierta
     */
//    public Connection closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
