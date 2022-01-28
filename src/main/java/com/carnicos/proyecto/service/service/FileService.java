package com.carnicos.proyecto.service.service;

import com.carnicos.proyecto.service.entity.File;
import com.carnicos.proyecto.service.repository.FileRepository;
import com.carnicos.proyecto.service.util.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

import static com.carnicos.proyecto.service.util.Global.*;


@Service
@Transactional
public class FileService {
    private FileRepository repo;
    private FileStorageService storageService;

    public FileService(FileRepository repo, FileStorageService storageService) {
        this.repo = repo;
        this.storageService = storageService;
    }

    //listar las imagenes

    public GenericResponse<Iterable<File>> list() {
        return new GenericResponse<Iterable<File>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

    //encontrar las imagenes

    public GenericResponse find(Long aLong) {
        return null;
    }


    //guardar un archivo

    public GenericResponse save(File obj) {
        String fileName = (repo.findById(obj.getId())).orElse(new File()).getFileName();

        String originalFilename = obj.getFile().getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        fileName = storageService.storeFile(obj.getFile(), fileName);

        obj.setFileName(fileName);
        obj.setExtension(extension);

        return new GenericResponse(TIPO_DATA, RPTA_OK,OPERACION_CORRECTA,repo.save(obj));
    }

    //descargar un archivo

    public ResponseEntity<Resource> download(String completefileName, HttpServletRequest request) {
        Resource resource = storageService.loadResource(completefileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //descargar un archivo

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        File doc = repo.findByFileName(fileName).orElse(new File());
        return download(doc.getCompleteFileName(), request);
    }

    //eliminar
    public GenericResponse delete(Long aLong) {
        return null;
    }


    //validar
    public HashMap<String, Object> validate(File obj) {
        return null;
    }
}
