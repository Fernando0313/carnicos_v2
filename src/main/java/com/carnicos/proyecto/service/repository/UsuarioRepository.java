package com.carnicos.proyecto.service.repository;

import com.carnicos.proyecto.service.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query("SELECT U FROM Usuario U WHERE U.email=:correo AND U.clave=:password")
    Optional<Usuario> login(String correo, String password);
}
