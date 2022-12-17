package controller.client.headlecturer;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.HomeService;
import service.HeadLecturerProfileService;

@WebServlet("/head-lecturer/profile")
public class HeadLecturerProfile extends HttpServlet{
		private static final long serialVersionUID = 1L;
	       
	    
	    public HeadLecturerProfile() {
	        super();
	        
	    }
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HeadLecturerProfileService profile = new HeadLecturerProfileService(request, response);
			profile.GetHeadLecturerProfile();
		}
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
