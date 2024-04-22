/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author pablo
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connect.MySQLConnection;
import model.User;
import singleton.DatabaseSingleton;

public class Database {

    //Singleton
    private Connection connection;

    public Database() {
        connection = DatabaseSingleton.getInstance().getConnection();
    }

    public static User obtenerUsuarioPorCredenciales(String usuario, String contraseña) {
        try (Connection conn = MySQLConnection.conectarMySQL()) {
            if (conn == null) {
                System.err.println("No se pudo establecer una conexión con la base de datos.");
                return null;
            }

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, usuario);
                statement.setString(2, contraseña);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(resultSet.getInt("id"), resultSet.getString("username"),
                                usuario, resultSet.getString("rol"), contraseña);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos: " + e.getMessage());
        }
        return null;
    }
}
