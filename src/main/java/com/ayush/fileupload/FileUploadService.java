package com.ayush.fileupload;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    public String DYNAMIC_PATH= new ClassPathResource("static/images/").getFile().getAbsolutePath();

    /*Static Path*/
    public String UPLOAD_DIR=
            "E:\\SpringBootLearn\\SpringBootFileUpload\\file-upload\\src\\main\\resources\\static\\images";

    public FileUploadService() throws IOException {
    }

    public Boolean uploadFile(MultipartFile file){
        boolean uploaded=false;

        try{
            //file read
            InputStream inputStream = file.getInputStream();
            byte data[]=new byte[inputStream.available()];
            inputStream.read(data);

            //file write
            FileOutputStream outputStream=
                    new FileOutputStream(DYNAMIC_PATH+ File.separator+file.getOriginalFilename());
            outputStream.write(data);
            outputStream.close();
            inputStream.close();

            /*alternative */
            /*Files.copy(
                    file.getInputStream(),
                    Paths.get(DYNAMIC_PATH+ File.separator+file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );*/

            uploaded=true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return uploaded;
    }

}
