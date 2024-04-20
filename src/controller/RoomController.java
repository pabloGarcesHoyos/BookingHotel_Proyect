/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connect.MySQLConnection;
import model.Room;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomController {

    private final MySQLConnection connection;

    public RoomController() throws SQLException {
        this.connection = new MySQLConnection();
    }

    public void createRoom(int id, int roomNumber, String roomType, int pricePerNight, int availability, String amenitiesDetails) throws SQLException {
        String createSQL = "INSERT INTO room (id, room_number, room_type, price_per_night, availability, amenities_details) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(createSQL)) {
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

    public Room readRoom(int id) throws SQLException {
        String readSQL = "SELECT * FROM room WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int roomNumber = rs.getInt("room_number");
                String roomType = rs.getString("room_type");
                int pricePerNight = rs.getInt("price_per_night");
                int availability = rs.getInt("availability");
                String amenitiesDetails = rs.getString("amenities_details");
                return new Room(id, roomNumber, roomType, pricePerNight, true, amenitiesDetails);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        return null;
    }

    public void updateRoom(int id, int roomNumber, String roomType, int pricePerNight, int availability, String amenitiesDetails) throws SQLException {
        String updateSQL = "UPDATE room SET room_number = ?, room_type = ?, price_per_night = ?, availability = ?, amenities_details = ? WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setInt(1, roomNumber);
            statement.setString(2, roomType);
            statement.setInt(3, pricePerNight);
            statement.setInt(4, availability);
            statement.setString(5, amenitiesDetails);
            statement.setInt(6, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Actualización exitosa");
            } else {
                System.out.println("No se actualizó ningún dato");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos: " + e.getMessage());
        }
    }

    public void deleteRoom(int id) throws SQLException {
        String deleteSQL = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Eliminación exitosa");
            } else {
                System.out.println("No se eliminó ningún dato");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar datos: " + e.getMessage());
        }
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("room_number");
                String roomType = resultSet.getString("room_type");
                int pricePerNight = resultSet.getInt("price_per_night");
                int availability = resultSet.getInt("availability");
                String amenitiesDetails = resultSet.getString("amenities_details");
                rooms.add(new Room(id, roomNumber, roomType, pricePerNight, true, amenitiesDetails));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las habitaciones: " + e.getMessage());
        }
        return rooms;
    }

    public List<Room> getAvailableRoomsForHotel(int hotelId) throws SQLException {
        List<Room> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE availability = 1 AND hotel_id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("room_number");
                String roomType = resultSet.getString("room_type");
                int pricePerNight = resultSet.getInt("price_per_night");
                int availability = resultSet.getInt("availability");
                String amenitiesDetails = resultSet.getString("amenities_details");
                availableRooms.add(new Room(id, roomNumber, roomType, pricePerNight, true, amenitiesDetails));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las habitaciones disponibles para el hotel: " + e.getMessage());
        }
        return availableRooms;
    }
}
