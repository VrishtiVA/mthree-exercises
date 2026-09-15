package mthree.academy.c458.vrishti.car_lot_service_layer.service;

public class UnderpaidPriceException extends RuntimeException {

    public UnderpaidPriceException(String message) {
        super(message);
    }

    public UnderpaidPriceException(String message, Throwable cause) {
        super(message, cause);
    }

}
