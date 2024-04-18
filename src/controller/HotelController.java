package controller;

import connect.MySQLConnection;
import model.Hotel;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelController {

    private final MySQLConnection connection;

    public HotelController() throws SQLException {
        this.connection = new MySQLConnection();
    }

    public void createHotel(int id, String name, String address, String classification, String amenities, String images) throws SQLException {
        String createSQL = "INSERT INTO hotel (id, name, address, classification, amenities, images) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, address);
            statement.setString(4, classification);
            statement.setString(5, amenities);
            statement.setString(6, images);
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

    public void readHotel(int id) throws SQLException {
        String readSQL = "SELECT * FROM hotel WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Nombre: " + rs.getString("name"));
                System.out.println("Dirección: " + rs.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
    }

    public void updateHotel(int id, String name, String address, String classification, String amenities, String images) throws SQLException {
        String updateSQL = "UPDATE hotel SET name = ?, address = ?, classification = ?, amenities = ?, images = ? WHERE id = ?";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, classification);
            statement.setString(4, amenities);
            statement.setString(5, images);
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

    public void deleteHotel(int id) throws SQLException {
        String deleteSQL = "DELETE FROM hotel WHERE id = ?";
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
    public boolean realizarReserva(String idHotel, LocalDate fechaEntrada, LocalDate fechaSalida) throws SQLException {
        try {
            boolean disponibilidad = verificarDisponibilidad(idHotel, fechaEntrada, fechaSalida);
            
            if (!disponibilidad) {
                return false;
            }

            System.out.println("Reserva realizada para el hotel con ID " + idHotel + " desde " + fechaEntrada + " hasta " + fechaSalida);
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error al realizar la reserva: " + e.getMessage());
            throw e;
        }
    }

    private boolean verificarDisponibilidad(String idHotel, LocalDate fechaEntrada, LocalDate fechaSalida) throws SQLException {
        return true;
    }

    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        try (PreparedStatement statement = connection.conectarMySQL().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String classification = resultSet.getString("classification");
                String amenities = resultSet.getString("amenities");
                String images = resultSet.getString("images");
                hotels.add(new Hotel(id, name, address, classification, amenities, images));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los hoteles: " + e.getMessage());
            throw e; 
        }
        return hotels;
    }

    public MySQLConnection getConnection() {
        return connection;
    }
}
