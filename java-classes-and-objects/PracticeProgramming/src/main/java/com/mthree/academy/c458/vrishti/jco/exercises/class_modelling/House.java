package com.mthree.academy.c458.vrishti.jco.exercises.class_modelling;

public class House {

    //Attributes
    private int roomsCount;
    private int floorsCount;
    private int[] coordinates;
    private String address;

    //Constructors
    public House() {}
    public House(int roomsCount, int floorsCount, int[] coordinates, String address) {
        this.roomsCount = roomsCount;
        this.floorsCount = floorsCount;
        this.coordinates = coordinates;
        this.address = address;
    }

    //Getters
    public int getRoomsCount() {return roomsCount;}
    public int getFloorsCount() {return floorsCount;}
    public int[] getCoordinates() {return coordinates;}
    public String getAddress() {return address;}

    //Setters
    public void setRoomsCount(int roomsCount) {this.roomsCount = roomsCount;}
    public void setFloorsCount(int floorsCount) {this.floorsCount = floorsCount;}
    public void setCoordinates(int[] coordinates) {this.coordinates = coordinates;}
    public void setAddress(String address) {this.address = address;}

}
