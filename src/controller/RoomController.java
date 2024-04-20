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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomController {

    private final MySQLConnection connection;

    public RoomController() throws SQLException {
        this.connection = new MySQLConnection();
    }

    public void createRoom(int id, int roomNumber, String roomType, int pricePerNight, int availability, String amenitiesDetails, String hotel) throws SQLException {
        String createSQL = "INSERT INTO room (id, roomNumber, roomType, pricePerNight, availability, amenitiesDetails, hotel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setInt(1, id);
            statement.setInt(2, roomNumber);
            statement.setString(3, roomType);
            statement.setInt(4, pricePerNight);
            statement.setInt(5, availability);
            statement.setString(6, amenitiesDetails);
            statement.setString(7, hotel);
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

    public boolean verificarDisponibilidad(int roomId, LocalDate fechaEntrada, LocalDate fechaSalida) throws SQLException {
        boolean disponibilidad = true;
        // Verificar si hay reservas para la habitación en las fechas proporcionadas
        String sql = "SELECT * FROM reserva WHERE id_habitacion = ? AND ((fecha_entrada >= ? AND fecha_entrada < ?) OR (fecha_salida > ? AND fecha_salida <= ?))";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql)) {
            statement.setInt(1, roomId);
            statement.setDate(2, java.sql.Date.valueOf(fechaEntrada));
            statement.setDate(3, java.sql.Date.valueOf(fechaSalida));
            statement.setDate(4, java.sql.Date.valueOf(fechaEntrada));
            statement.setDate(5, java.sql.Date.valueOf(fechaSalida));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                disponibilidad = false; // Si hay al menos una reserva en esas fechas, la habitación no está disponible
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar disponibilidad: " + e.getMessage());
            throw e; // Relanzar la excepción para que sea manejada por el código que llama a este método
        }
        return disponibilidad;
    }

    public Room readRoom(int id) throws SQLException {
        String readSQL = "SELECT * FROM room WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int roomNumber = rs.getInt("roomNumber");
                String roomType = rs.getString("roomType");
                int pricePerNight = rs.getInt("pricePerNight");
                int availability = rs.getInt("availability");
                String amenitiesDetails = rs.getString("amenitiesDetails");
                String hotel = rs.getString("hotel");
                return new Room(id, roomNumber, roomType, pricePerNight, availability, amenitiesDetails, hotel);
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

    public List<Room> getAvailableRoomsForHotel(String hotelId, LocalDate startDate, LocalDate endDate) throws SQLException {
        List<Room> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE availability = 1 AND hotel_id = ? AND room_id NOT IN "
                + "(SELECT room_id FROM reservation WHERE hotel_id = ? AND (start_date <= ? AND end_date >= ?))";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql)) {
            statement.setString(1, hotelId);
            statement.setString(2, hotelId);
            statement.setDate(3, java.sql.Date.valueOf(endDate));
            statement.setDate(4, java.sql.Date.valueOf(startDate));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("roomNumber");
                String roomType = resultSet.getString("roomType");
                int pricePerNight = resultSet.getInt("pricePerNight");
                int availability = resultSet.getInt("availability");
                String amenitiesDetails = resultSet.getString("amenitiesDetails");
                String hotel = resultSet.getString("hotel");
                availableRooms.add(new Room(id, roomNumber, roomType, pricePerNight, availability, amenitiesDetails, hotel));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las habitaciones disponibles para el hotel: " + e.getMessage());
        }
        return availableRooms;
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("roomNumber");
                String roomType = resultSet.getString("roomType");
                int pricePerNight = resultSet.getInt("pricePerNight");
                int availability = resultSet.getInt("availability");
                String amenitiesDetails = resultSet.getString("amenitiesDetails");
                String hotel = resultSet.getString("hotel");
                rooms.add(new Room(id, roomNumber, roomType, pricePerNight, availability, amenitiesDetails, hotel));
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
                int roomNumber = resultSet.getInt("roomNumber");
                String roomType = resultSet.getString("roomType");
                int pricePerNight = resultSet.getInt("pricePerNight");
                int availability = resultSet.getInt("availability");
                String amenitiesDetails = resultSet.getString("amenitiesDetails");
                String hotel = resultSet.getString("hotel");
                availableRooms.add(new Room(id, roomNumber, roomType, pricePerNight, availability, amenitiesDetails, hotel));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las habitaciones disponibles para el hotel: " + e.getMessage());
        }
        return availableRooms;
    }
}
