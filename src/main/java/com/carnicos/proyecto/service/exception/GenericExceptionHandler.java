package com.carnicos.proyecto.service.exception;
import com.carnicos.proyecto.service.util.GenericResponse;
import com.carnicos.proyecto.service.util.Global;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {
    //para mostrar una operacion erronea en caso de que la imagen no se pueda subir

    @ExceptionHandler(Exception.class)
    public GenericResponse genericException(Exception ex) {
        return new GenericResponse("exception", -1, Global.OPERACION_ERRONEA, ex.getMessage());
    }
}
