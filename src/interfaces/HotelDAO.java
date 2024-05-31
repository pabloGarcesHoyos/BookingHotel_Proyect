/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import model.Hotel;
import model.User;

/**
 *
 * @author Usuario
 */
public interface HotelDAO {

    void addHotel(Hotel hotel);
    User getHotel(int id);
    List<Hotel> getAllHotel();
    void updateHotel(Hotel hotel);
    void deleteHotel(int id);
}
