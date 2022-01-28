package com.carnicos.proyecto.service.exception;

public class MyFileNotFoundException extends RuntimeException {
    //archivo no encontrado

    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
