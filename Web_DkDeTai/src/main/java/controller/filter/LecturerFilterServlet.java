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

import DAO.PersonDAO;
import model.Person;

@WebFilter("/lecturer/*")
public class LecturerFilterServlet extends HttpFilter implements Filter {
    
  	private static final long serialVersionUID = 1L;


	public LecturerFilterServlet() {
        super();
        
    }

	
	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// cast object type 
				HttpServletRequest req = (HttpServletRequest) request;
				HttpServletResponse res = (HttpServletResponse) response;
				
				// get cookie is existing 
				String personId = "";
				Cookie[] cookies = req.getCookies();
				if (cookies != null) {
					for (int i=0; i<cookies.length; i++ ) {
						if (cookies[i].getName().equals("uid")) {
							personId = cookies[i].getValue();
						}
					}
				}
				
				Person person = null;
				if (!personId.equals("")) {
					PersonDAO personDAO = new PersonDAO();
					person = personDAO.find(Person.class, personId);
					if (person != null) {
						if (person.getRole().equals("lecturer")) {
							chain.doFilter(request, response);
						}else {
							res.sendRedirect(req.getContextPath());
						}
					}
				}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}