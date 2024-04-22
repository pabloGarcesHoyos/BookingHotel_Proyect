package controller;

import connect.MySQLConnection;
import model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomController {

    private final MySQLConnection connection;

    public RoomController() throws SQLException {
        this.connection = new MySQLConnection();
    }

    public void createRoom(Room room) throws SQLException {
        String createSQL = "INSERT INTO rooms (id, room_number, room_type, price_per_night, amenities_details, hotel_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setInt(1, room.getId());
            statement.setInt(2, room.getRoomNumber());
            statement.setString(3, room.getRoomType());
            statement.setDouble(4, room.getPricePerNight());
            statement.setString(5, room.getAmenitiesDetails());
            statement.setInt(6, room.getHotelId());
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
        String sql = "SELECT * FROM reservation WHERE room_id = ? AND ((check_in_date >= ? AND check_in_date < ?) OR (check_out_date > ? AND check_out_date <= ?))";
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
        String readSQL = "SELECT * FROM rooms WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int roomNumber = rs.getInt("room_number");
                String roomType = rs.getString("room_type");
                int pricePerNight = rs.getInt("price_per_night");
                String amenitiesDetails = rs.getString("amenities_details");
                String hotel = rs.getString("hotel_id");
                return new Room(id, roomNumber, roomType, pricePerNight, amenitiesDetails, id);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        return null;
    }

    public void updateRoom(int id, int roomNumber, String roomType, int pricePerNight, String amenitiesDetails) throws SQLException {
        String updateSQL = "UPDATE rooms SET room_number = ?, room_type = ?, price_per_night = ?, amenities_details = ? WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setInt(1, roomNumber);
            statement.setString(2, roomType);
            statement.setInt(3, pricePerNight);
            statement.setString(4, amenitiesDetails);
            statement.setInt(5, id);
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
        String deleteSQL = "DELETE FROM rooms WHERE id = ?";
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

    public List<Room> getAvailableRoomsForHotel(int hotelId) throws SQLException {
        List<Room> availableRooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE hotel_id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("room_number");
                String roomType = resultSet.getString("room_type");
                int pricePerNight = resultSet.getInt("price_per_night");
                String amenitiesDetails = resultSet.getString("amenities_details");
                String hotel = resultSet.getString("hotel_id");
                availableRooms.add(new Room(id, roomNumber, roomType, pricePerNight, amenitiesDetails, id));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las habitaciones disponibles para el hotel: " + e.getMessage());
        }
        return availableRooms;
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("room_number");
                String roomType = resultSet.getString("room_type");
                int pricePerNight = resultSet.getInt("price_per_night");
                String amenitiesDetails = resultSet.getString("amenities_details");
                String hotel = resultSet.getString("hotel_id");
                rooms.add(new Room(id, roomNumber, roomType, pricePerNight, amenitiesDetails, id));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las habitaciones: " + e.getMessage());
        }
        return rooms;
    }

    public List<Room> getAvailableRoomsForDates(LocalDate fechaEntrada, LocalDate fechaSalida) throws SQLException {
        List<Room> habitacionesDisponibles = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE id NOT IN (SELECT room_id FROM reservation WHERE check_in_date <= ? AND check_out_date >= ?)";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(fechaSalida));
            statement.setDate(2, java.sql.Date.valueOf(fechaEntrada));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("room_number");
                String roomType = resultSet.getString("room_type");
                double pricePerNight = resultSet.getDouble("price_per_night");
                String amenitiesDetails = resultSet.getString("amenities_details");
                int hotelId = resultSet.getInt("hotel_id");
                String hotel = resultSet.getString("hotel_id");
                habitacionesDisponibles.add(new Room(id, roomNumber, roomType, pricePerNight, amenitiesDetails, id));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las habitaciones disponibles para las fechas proporcionadas: " + e.getMessage());
            throw e;
        }
        return habitacionesDisponibles;
    }

    public boolean realizarReserva(int roomId, LocalDate fechaEntrada, LocalDate fechaSalida) throws SQLException {
        try {
            boolean disponibilidad = verificarDisponibilidad(roomId, fechaEntrada, fechaSalida);

            if (!disponibilidad) {
                return false;
            }

            String insertSQL = "INSERT INTO reservation (room_id, check_in_date, check_out_date) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(insertSQL)) {
                statement.setInt(1, roomId);
                statement.setDate(2, java.sql.Date.valueOf(fechaEntrada));
                statement.setDate(3, java.sql.Date.valueOf(fechaSalida));
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reserva realizada para la habitación con ID " + roomId + " desde " + fechaEntrada + " hasta " + fechaSalida);
                    return true;
                }
            }

            return false;
        } catch (SQLException e) {
            System.out.println("Error al realizar la reserva: " + e.getMessage());
            throw e;
        }
    }
}
