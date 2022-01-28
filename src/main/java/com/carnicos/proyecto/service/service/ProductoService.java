package com.carnicos.proyecto.service.service;

import com.carnicos.proyecto.service.repository.ProductoRepository;
import com.carnicos.proyecto.service.util.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.carnicos.proyecto.service.util.Global.*;

@Service
@Transactional
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }
    public GenericResponse listarProductos(){
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.repository.listarProductos());
    }
    public GenericResponse listarProductosPorCategoria(int idC){
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.repository.listarProductosPorCategoria(idC));
    }
}
