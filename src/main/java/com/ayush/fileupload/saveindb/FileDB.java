package com.ayush.fileupload.saveindb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileDB {
    @Id
    private String id;
    private String name;
    private String type;

    @Lob
    private byte[] file;

    public FileDB() {
    }

    public FileDB(String id, String name, String type, byte[] file) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
