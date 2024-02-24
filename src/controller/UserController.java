/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.ResultSet;
import connect.MySQLConnection;
import model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class UserController {

    private final MySQLConnection conection;

    public UserController() throws SQLException {
        this.conection = new MySQLConnection();
    }

    public void createUsers(int id, String nameUser, String emailUser, String password, String contactDetails, String rol) throws SQLException {
        String createSQL = "INSERT INTO users(id, nameUser, emailUser, password, contactDetails, rol) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setInt(1, id);
            statement.setString(2, nameUser);
            statement.setString(3, emailUser);
            statement.setString(4, password);
            statement.setString(5, contactDetails);
            statement.setString(6, rol);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insercion exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");

            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al realizar la insercion en la base de datos");
            e.printStackTrace();
        }
    }

    public void readUsers(int id) throws  SQLException {
        String readSQL = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nameUser"));
                System.out.println(rs.getString("emailUser"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }

    }

    public void updateUsers(String nameUser, String emailUser, String password, String contactDetails, String rol, int id) throws SQLException {
        String updateSQL = "UPDATE users SET nameUser = ?, emailUser = ?, password = ?, contactDetails = ?, rol = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, nameUser);
            statement.setString(2, emailUser);
            statement.setString(3, password);
            statement.setString(4, contactDetails);
            statement.setString(5, rol);
            statement.setInt(6, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteUsers(int id) throws SQLException {
        String deleteSQL = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }
}
