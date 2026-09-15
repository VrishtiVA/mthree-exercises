package mthree.academy.c458.vrishti.car_lot_service_layer.service;

import mthree.academy.c458.vrishti.car_lot_service_layer.dao.CarLotDAO;
import mthree.academy.c458.vrishti.car_lot_service_layer.dto.Car;
import mthree.academy.c458.vrishti.car_lot_service_layer.dto.CarKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CarLotServiceImpl implements CarLotService {

    private CarLotDAO dao;

    public CarLotServiceImpl(CarLotDAO dao) {
        this.dao = dao;
    }

    @Override
    public Car getACar(String VIN) {
        return dao.getCar(VIN);
    }

    @Override
    public List<Car> getAllCars() {
        return dao.getCars();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return dao.getCars().stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getCarsInBudget(BigDecimal maxPrice) {
        return dao.getCars().stream()
                .filter(car -> car.getPrice().compareTo(maxPrice) <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getCarByMakeAndModel(String make, String model) {
        //Formatted like this for readability.
        return dao.getCars().stream()
                .filter(car -> car.getMake().equals(make))
                .filter(car -> car.getModel().equals(model))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal discountCar(String VIN, BigDecimal percentDiscount) throws NoSuchCarException {

        //Get car
        Car car = dao.getCar(VIN);
        if (car == null)
            throw new NoSuchCarException("There is no such car.");

        //Calculate discount
        BigDecimal newPrice = car.getPrice().multiply(percentDiscount);

        //Update official car price on record
        car.setPrice(newPrice);

        //Return the new final price
        return newPrice;
    }

    @Override
    public CarKey sellCar(String VIN, BigDecimal cashPaid) throws NoSuchCarException, OverpaidPriceException, UnderpaidPriceException {

        //Get the car
        Car car = dao.getCar(VIN);
        if (car == null)
            throw new NoSuchCarException("There is no such car.");

        //Check if price matches (otherwise throw error to escape)
        if (car.getPrice().compareTo(cashPaid) < 0)
            throw new OverpaidPriceException("You gave too much money.");
        else if (car.getPrice().compareTo(cashPaid) > 0)
            throw new UnderpaidPriceException("You have too little money.");

        //Remove the car from the lot
        dao.removeCar(VIN);

        //Return associated car key
        return car.getKey();
    }

}
