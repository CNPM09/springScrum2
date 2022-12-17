package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.PersonDAO;
import model.Admin;
import model.Person;
import service.SuperService;
import service.AdminService;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilterServlet extends HttpFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	public AdminFilterServlet() {
        super();
    }

	 @Override
	 public void destroy() {
			//close any resources here
		}
	 
	 @Override
	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
		      throws IOException, ServletException {
		 
		    HttpServletRequest request = (HttpServletRequest) servletRequest;
		    HttpServletResponse response = (HttpServletResponse) servletResponse;

		    
		    // get cookie is existing 
			boolean flag = false;
			String personId = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i=0; i<cookies.length; i++ ) {
					if (cookies[i].getName().equals("uid")) {
						flag = true;
						personId = cookies[i].getValue();
					}
				}
			}
			
			
			Person person = null;
			if(flag) {
				
				PersonDAO personDAO = new PersonDAO();
				person = personDAO.find(Person.class, personId);
				
				if (person.getRole().equals("student")) {
					chain.doFilter(request, response);
			}else {
				response.sendRedirect(request.getContextPath());
				}
			}
			
		}


}
