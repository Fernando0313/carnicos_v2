package com.carnicos.proyecto.service.controller;

import com.carnicos.proyecto.service.service.ProductoService;
import com.carnicos.proyecto.service.util.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/producto")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public GenericResponse listarProductos(){
        return this.service.listarProductos();
    }
    @GetMapping("/{idC}")
    public GenericResponse listarProductosPorCategoria(@PathVariable int idC){
        return this.service.listarProductosPorCategoria(idC);
    }
}
