package com.ayush.fileupload.saveindb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FIleDBService {
    @Autowired
    private FileRepo fileRepo;

    public FileDB store(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String fileType=file.getContentType();
        FileDB db=new FileDB(UUID.randomUUID().toString(),fileName,fileType,file.getBytes());
        return  fileRepo.save(db);
    }

    public FileDB getFileById(String fileId){
        Optional<FileDB> byId = fileRepo.findById(fileId);
        if (byId.isPresent()){
            return byId.get();
        }else{
            return null;
        }
    }

    public List<FileDB> getAllFiles(){
        return fileRepo.findAll();
    }
}
