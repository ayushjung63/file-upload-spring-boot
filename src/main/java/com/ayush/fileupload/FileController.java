package com.ayush.fileupload;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
public class FileController {
    private FileUploadService fileUploadService;

    public FileController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        /* File upload code*/
        if (file.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "File not found"), HttpStatus.BAD_REQUEST);
        } else if (!file.getContentType().equals("image/jpeg")) {
            return new ResponseEntity<>(Map.of("message", "Only Image upload is available"), HttpStatus.BAD_REQUEST);
        } else {
            fileUploadService.uploadFile(file);
            return ResponseEntity.ok(
                    ServletUriComponentsBuilder.
                            fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString()
            );
        }
    }

}