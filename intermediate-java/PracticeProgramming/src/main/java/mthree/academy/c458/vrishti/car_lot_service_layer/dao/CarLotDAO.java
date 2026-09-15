package mthree.academy.c458.vrishti.car_lot_service_layer.dao;

import mthree.academy.c458.vrishti.car_lot_service_layer.dto.Car;

import java.util.List;

public interface CarLotDAO {

    public Car addCar(String VIN, Car car);

    public Car getCar(String VIN);
    public List<Car> getCars();

    public void editCar(String VIN, Car car);

    public Car removeCar(String VIN);

}
