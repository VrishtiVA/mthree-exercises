package mthree.academy.c458.vrishti.car_lot_service_layer.service;

public class NoSuchCarException extends RuntimeException {

    public NoSuchCarException(String message) {
        super(message);
    }

    public NoSuchCarException(String message, Throwable cause) {
        super(message, cause);
    }
}
