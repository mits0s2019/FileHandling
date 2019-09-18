package controller;

import dao.FileDao;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MyFile;

public class Download_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        FileDao filedao = new FileDao();
        MyFile file = (filedao.getFileById(Integer.parseInt(request.getParameter("id"))));

        InputStream inputstream = null;

        inputstream = file.getThe_blob().getBinaryStream();
        OutputStream out_stream = response.getOutputStream();

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(file.getFile_name());
        if (mimeType != null) {
            mimeType = "application/octet-stream";
        }

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputstream.read(buffer)) != -1) {
            //send responce to browser
            out_stream.write(buffer, 0, bytesRead);
    
        }
        
        
        inputstream.close();
        out_stream.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Download_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Download_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
