package controller.client.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.HomeService;

@WebServlet("/admin/home")
public class HomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HomeAdmin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HomeService home = new HomeService(request, response);
		home.handleGetAdminHomeService();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}