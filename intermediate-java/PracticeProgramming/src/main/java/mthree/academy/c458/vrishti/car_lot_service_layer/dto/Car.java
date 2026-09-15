package mthree.academy.c458.vrishti.car_lot_service_layer.dto;

import java.math.BigDecimal;

public class Car {

    private String VIN; //Vehicle Identification Number
    private String make;
    private String model;
    private String color;

    private BigDecimal price;
    private long odometerMiles;

    private CarKey key;

    //Constructor
    public Car() {}
    public Car(String VIN) {
        this.VIN = VIN;
    }

    //Getters
    public String getVIN() {return VIN;}
    public String getMake() {return make;}
    public String getModel() {return model;}
    public String getColor() {return color;}
    public BigDecimal getPrice() {return price;}
    public long getOdometerMiles() {return odometerMiles;}
    public CarKey getKey() {return key;}

    //Setters
    public void setMake(String make) {this.make = make;}
    public void setModel(String model) {this.model = model;}
    public void setColor(String color) {this.color = color;}
    public void setPrice(BigDecimal price) {this.price = price;}
    public void setOdometerMiles(long odometerMiles) {this.odometerMiles = odometerMiles;}
    public void setKey(CarKey key) {this.key = key;}
}
