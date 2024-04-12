/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.ResultSet;
import connect.MySQLConnection;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connect.MySQLConnection;

public class UserController {

    public UserController() {
    }

    public void createUsers(int id, String userName, String email, String password, String contactDetails) throws SQLException {
        String createSQL = "INSERT INTO users(id, userName, email, password, contactDetails) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setInt(1, id);
            statement.setString(2, userName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, contactDetails);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al realizar la inserción en la base de datos: " + e.getMessage());
        }
    }

    public void readUsers(int id) throws SQLException {
        String readSQL = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("userName: " + rs.getString("userName"));
                System.out.println("email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer los usuarios: " + e.getMessage());
        }
    }

    public void updateUsers(int id, String userName, String email, String password, String contactDetails) throws SQLException {
        String updateSQL = "UPDATE users SET userName = ?, email = ?, password = ?, contactDetails = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, userName);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, contactDetails);
            statement.setInt(5, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Actualización exitosa");
            } else {
                System.out.println("No se pudo actualizar el usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar los usuarios: " + e.getMessage());
        }
    }

    public void deleteUsers(int id) throws SQLException {
        String deleteSQL = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Eliminación exitosa");
            } else {
                System.out.println("No se pudo eliminar el usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuarios: " + e.getMessage());
        }
    }
}

