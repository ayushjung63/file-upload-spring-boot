package com.ayush.fileupload.saveindb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/db")
public class FileDBController {
    @Autowired
    private FIleDBService service;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public FileDB upload(@RequestParam("file") MultipartFile file) throws IOException {
        return service.store(file);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        FileDB file = service.getFileById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +file.getName() + "\"")
                .body(new ByteArrayResource(file.getFile()));
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<FileDB> all(){
        return service.getAllFiles();
    }
}
