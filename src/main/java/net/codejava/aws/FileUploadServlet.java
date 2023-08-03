package net.codejava.aws;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*11)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUploadServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = "";
		description = request.getParameter("description");
		String[] email1 = new String[5];
		email1[0] = request.getParameter("email1");
		email1[1] = request.getParameter("email2");
		email1[2] = request.getParameter("email3");
		email1[3] = request.getParameter("email4");
		email1[4] = request.getParameter("email5");
		
		System.out.println("Description: "  + description);
		System.out.println("Email1: "  + email1);

		Part filePart = request.getPart("file");
        
        String fileName = getFileName(filePart);
         
        System.out.println("File name = " + fileName);
 
        String message = "";
        try {
            S3Util.uploadFile(fileName, filePart.getInputStream(),email1,description);
            message = "The file has been uploaded successfully";
        } catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);

	}
	private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;
         
        return contentDisposition.substring(beginIndex, endIndex);
    }
	
	
}