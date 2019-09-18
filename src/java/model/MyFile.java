
package model;

import java.sql.Blob;

public class MyFile {
 private    int id;  
 private  String file_name;
 private Blob the_blob;
 private byte[] byte_array;

    public MyFile() {
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setThe_blob(Blob the_blob) {
        this.the_blob = the_blob;
    }

    public void setByte_array(byte[] byte_array) {
        this.byte_array = byte_array;
    }

    public String getFile_name() {
        return file_name;
    }

    public Blob getThe_blob() {
        return the_blob;
    }

    public byte[] getByte_array() {
        return byte_array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyFile{" + "id=" + id + ", file_name=" + file_name + ", the_blob=" + the_blob + ", byte_array=" + byte_array + '}';
    }
 
 
    
}
