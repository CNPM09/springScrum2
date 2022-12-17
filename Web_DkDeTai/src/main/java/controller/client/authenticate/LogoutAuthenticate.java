package controller.client.authenticate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LogoutService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns = { "/logout", "/logout/" })
public class LogoutAuthenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LogoutAuthenticate() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogoutService logoutService = new LogoutService(request, response);
		logoutService.GetLogout();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		LogoutService logoutService = new LogoutService(request, response);
		logoutService.GetLogout();
	}

}