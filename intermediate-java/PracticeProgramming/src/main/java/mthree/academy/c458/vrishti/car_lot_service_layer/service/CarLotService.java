package mthree.academy.c458.vrishti.car_lot_service_layer.service;

import mthree.academy.c458.vrishti.car_lot_service_layer.dto.Car;
import mthree.academy.c458.vrishti.car_lot_service_layer.dto.CarKey;

import java.math.BigDecimal;
import java.util.List;

public interface CarLotService {

    /**
     * Given a VIN, it should be able to get a single Car.
     * @param VIN
     * @return Car
     */
    public Car getACar(String VIN);

    /**
     * It should be able to get all the Cars and return them in a List.
     * @return
     */
    public List<Car> getAllCars();

    /**
     * Given a color, it should be able to return all the available Cars of that color in a List.
     * @param color
     * @return
     */
    public List<Car> getCarsByColor(String color);

    /**
     * Given a max Price, it should be able to return a List of all available Cars at or under that price.
     * @param maxPrice
     * @return
     */
    public List<Car> getCarsInBudget(BigDecimal maxPrice);

    /**
     * Given a make and model, it should be able to return a List of all the available Cars.
     * @param make
     * @param model
     * @return
     */
    public List<Car> getCarByMakeAndModel(String make, String model);

    /**
     * Given a VIN and a discount amount (i.e. 15%),
     * this method should discount the car's price (updating the official price records of that car).
     * @param VIN
     * @param percentDiscount
     * @return return the new final price.
     * @throws NoSuchCarException If no car matches, it should throw a NoSuchCarException
     */
    public BigDecimal discountCar(String VIN, BigDecimal percentDiscount) throws
        NoSuchCarException;

    /**
     * Given a VIN and a cash Amount,
     * it should 'buy' - checking if the price matches,
     * removing the car from the lot,
     * and returning the associated CarKey.
     * @param VIN
     * @param cashPaid
     * @return Returning the associated CarKey.
     * @throws NoSuchCarException If no car matches, it should throw a NoSuchCarException.
     * @throws OverpaidPriceException If they gave too much money, it should throw an OverpaidPriceException.
     * @throws UnderpaidPriceException If they gave too little money, it should throw an UnderpaidPriceException.
     */
    public CarKey sellCar(String VIN, BigDecimal cashPaid) throws
        NoSuchCarException,
        OverpaidPriceException,
        UnderpaidPriceException;

}
