package com.carnicos.proyecto.service.controller;

import com.carnicos.proyecto.service.entity.File;
import com.carnicos.proyecto.service.service.CloudinaryService;
import com.carnicos.proyecto.service.service.FileService;
import com.carnicos.proyecto.service.util.GenericResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/file")
public class FileController {
    private FileService service;

    @Autowired 
	CloudinaryService imgService;
    
    public FileController(FileService service) {
        this.service = service;
    }

    //listar file
    @GetMapping
    public GenericResponse list() {
        return service.list();
    }


    //buscar file por id
    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {
        return null;
    }


    //descargar el file
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return service.downloadByFileName(fileName, request);
    }

    //guardar la file
    @PostMapping
    public GenericResponse save(@ModelAttribute File obj) throws IOException {
    	MultipartFile multipartFile = obj.getFile();
    	BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
    	Map result = imgService.upload(multipartFile);
    	obj.setUrlfileremoto(result.get("url").toString());
        return service.save(obj);
    }

    public GenericResponse update(Long aLong, File obj) {
        return null;
    }
    
    public GenericResponse delete(Long aLong) {
        return null;
    }
}
