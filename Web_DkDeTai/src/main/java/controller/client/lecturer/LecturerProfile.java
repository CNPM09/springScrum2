package controller.client.lecturer;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.HomeService;
import service.LecturerProfileService;

@WebServlet("/lecturer/profile")
public class LecturerProfile extends HttpServlet{
		private static final long serialVersionUID = 1L;
	       
	    
	    public LecturerProfile() {
	        super();
	        
	    }
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			LecturerProfileService profile = new LecturerProfileService(request, response);
			profile.GetLecturerProfile();
		}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
