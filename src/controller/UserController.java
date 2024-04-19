/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connect.MySQLConnection;
import model.User;

public class UserController {

    public UserController() {
    }

    public void createUsers(String userName, String email, String password, String contactDetails, String rol) throws SQLException {
        String createSQL = "INSERT INTO users(userName, email, password, contactDetails, rol) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = MySQLConnection.conectarMySQL();
             PreparedStatement statement = conn.prepareStatement(createSQL)) {
            statement.setString(1, userName);
            statement.setString(2, email);
            statement.setString(3, password); 
            statement.setString(4, contactDetails);
            statement.setString(5, rol);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Inserción exitosa" : "No se pudo insertar los datos");
        }
    }

    public User readUsers(int id) throws SQLException {
        String readSQL = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = MySQLConnection.conectarMySQL();
             PreparedStatement statement = conn.prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("email"),
                    rs.getString("password"), 
                    rs.getString("contactDetails")
                );
            }
        }
        return null;
    }

    public void updateUsers(int id, String userName, String email, String password, String contactDetails) throws SQLException {
        String updateSQL = "UPDATE users SET userName = ?, email = ?, password = SHA2(?, 256), contactDetails = ? WHERE id = ?";
        try (Connection conn = MySQLConnection.conectarMySQL();
             PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            statement.setString(1, userName);
            statement.setString(2, email);
            statement.setString(3, password); 
            statement.setString(4, contactDetails);
            statement.setInt(5, id);

            System.out.println(statement.executeUpdate() > 0 ? "Actualización exitosa" : "No se pudo actualizar el usuario");
        }
    }

    public void deleteUsers(int id) throws SQLException {
        String deleteSQL = "DELETE FROM users WHERE id = ?";
        try (Connection conn = MySQLConnection.conectarMySQL();
             PreparedStatement statement = conn.prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            System.out.println(statement.executeUpdate() > 0 ? "Eliminación exitosa" : "No se pudo eliminar el usuario");
        }
    }

    public static User obtenerUsuarioPorCredenciales(String usuario, String contraseña) throws SQLException {
        String query = "SELECT * FROM users WHERE userName = ? AND password = SHA2(?, 256)";
        try (Connection conn = MySQLConnection.conectarMySQL();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, usuario);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("userName"),
                    resultSet.getString("email"),
                    resultSet.getString("password"), 
                    resultSet.getString("contactDetails")
                );
            }
        }
        return null;
    }
}
