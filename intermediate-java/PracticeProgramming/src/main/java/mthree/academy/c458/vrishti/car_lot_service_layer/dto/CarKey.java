package mthree.academy.c458.vrishti.car_lot_service_layer.dto;

public class CarKey {

    //Attributes
    private String VIN;
    private boolean laserCut;

    //Constructors
    public CarKey() {}
    public CarKey(String VIN) {
        this.VIN = VIN;
    }

    //Getters
    public String getVIN() {return VIN;}
    public boolean isLaserCut() {return laserCut;}

    //Setters
    public void setLaserCut(boolean laserCut) {this.laserCut = laserCut;}

}
