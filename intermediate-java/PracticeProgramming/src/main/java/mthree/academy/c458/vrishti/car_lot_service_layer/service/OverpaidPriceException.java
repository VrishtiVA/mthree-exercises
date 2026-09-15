package mthree.academy.c458.vrishti.car_lot_service_layer.service;

public class OverpaidPriceException extends RuntimeException {

    public OverpaidPriceException(String message) {
        super(message);
    }

    public OverpaidPriceException(String message, Throwable cause) {
        super(message, cause);
    }
}
