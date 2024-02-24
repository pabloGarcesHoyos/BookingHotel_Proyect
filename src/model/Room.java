/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pablo
 */
public class Room {
     
    private int id;
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean availability;
    private String amenitiesDetails;

    public Room(int id, int roomNumber, String roomType, double pricePerNight, boolean availability, String amenitiesDetails) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.availability = availability;
        this.amenitiesDetails = amenitiesDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getAmenitiesDetails() {
        return amenitiesDetails;
    }

    public void setAmenitiesDetails(String amenitiesDetails) {
        this.amenitiesDetails = amenitiesDetails;
    }

}
