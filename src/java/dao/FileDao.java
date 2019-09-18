package dao;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.MyFile;
import utils.DBUtils;
import java.util.*;
import javax.servlet.http.Part;

public class FileDao {

    

    public MyFile getFileById(int id) throws SQLException {
        String qr = "select * from myfiles where id= ? ";
        MyFile file = new MyFile();

        Connection conn = DBUtils.getConnection();
        PreparedStatement pr = conn.prepareStatement(qr);
        pr.setInt(1, id);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            file.setFile_name(rs.getString(1));
            file.setId(rs.getInt(3));
            file.setThe_blob(rs.getBlob(2));
            file.setSize(rs.getInt(4));
        }
        rs.close();
        pr.close();
        conn.close();

        return file;
    }

    public static List<MyFile> getAllFiles() throws SQLException {
        List<MyFile> filesList = new ArrayList<>();
        String qr = "select * from myfiles";

        Connection conn = DBUtils.getConnection();
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(qr);
        while (rs.next()) {
            MyFile file = new MyFile();
            file.setFile_name(rs.getString(1));
            file.setThe_blob(rs.getBlob(2));
            file.setId(rs.getInt(3));
            file.setSize(rs.getInt(4));
            
            filesList.add(file);
        }
        rs.close();
        st.close();
        conn.close();

        return filesList;
    }

    public static void insertFileIntoDb(Part p) throws SQLException, IOException {

        String filename = p.getSubmittedFileName();

        String qr = "insert into myfiles (filename,thefile,size) values(?,?,?)";
        Connection conn = DBUtils.getConnection();
        PreparedStatement pr = conn.prepareStatement(qr);
        pr.setString(1, filename);
        pr.setBinaryStream(2, p.getInputStream());
        pr.setLong(3,p.getSize());
        
        pr.executeUpdate();

        pr.close();
        conn.close();
    }

    public static void deleteFile(int id) throws SQLException {

        String qr = "DELETE FROM myfiles WHERE id = ? ";

        Connection conn = DBUtils.getConnection();
        PreparedStatement pr = conn.prepareStatement(qr);

        pr.setInt(1, id);

        pr.executeUpdate();

        pr.close();
        conn.close();

    }

}
