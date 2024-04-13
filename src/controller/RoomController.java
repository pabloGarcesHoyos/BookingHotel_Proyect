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

public class RoomController {

    public RoomController() {
    }

    public void createRooms(int id, int roomNumber, String roomType, int pricePerNight, int availability, String amenitiesDetails) throws SQLException {
        String createSQL = "INSERT INTO room(id, roomNumber, roomType, pricePerNight, availability,amenitiesDetails) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setInt(1, id);
            statement.setInt(2, roomNumber);
            statement.setString(3, roomType);
            statement.setInt(4, pricePerNight);
            statement.setInt(5, availability);
            statement.setString(6, amenitiesDetails);

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
        String readSQL = "SELECT * FROM room WHERE id = ?";
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

    public void updateUsers(int id, int roomNumber, String roomType, int pricePerNight, int availability, String amenitiesDetails) throws SQLException {
        String updateSQL = "UPDATE room SET roomNumber = ?, roomType = ?, pricePerNight = ?, availability = ?, amenitiesDetails =? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setInt(1, id);
            statement.setInt(2, roomNumber);
            statement.setString(3, roomType);
            statement.setInt(4, pricePerNight);
            statement.setInt(5, availability);
            statement.setString(6, amenitiesDetails);

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
        String deleteSQL = "DELETE FROM room WHERE id = ?";
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
