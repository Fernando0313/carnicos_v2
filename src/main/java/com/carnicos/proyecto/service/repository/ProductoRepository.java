package com.carnicos.proyecto.service.repository;

import com.carnicos.proyecto.service.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
    @Query("SELECT P FROM Producto P WHERE P.recomendado IS 1")
    Iterable<Producto> listarProductos();

    @Query("SELECT P FROM Producto P WHERE P.categoria.id=:idC")
    Iterable<Producto> listarProductosPorCategoria(int idC);
}