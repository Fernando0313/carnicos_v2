package com.carnicos.proyecto.service.service;

import com.carnicos.proyecto.service.repository.CategoriaRepository;
import com.carnicos.proyecto.service.util.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.carnicos.proyecto.service.util.Global.*;

@Service
@Transactional
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }
    public GenericResponse listarCategoriasActivas(){
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.repository.listarCategoriasActivas());
    }
}
