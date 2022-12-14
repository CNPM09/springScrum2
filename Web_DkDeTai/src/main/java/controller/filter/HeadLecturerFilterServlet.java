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
import model.HeadLecturer;
import model.Lecturer;
import model.Person;
import service.HeadLecturerService;
import service.LecturerService;

@WebFilter(urlPatterns = { "/lecturer/approval/*"})
public class HeadLecturerFilterServlet extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// get cookie is existing
		boolean check = false;
		String personId = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userIdCookie")) {
					check = true;
					personId = cookies[i].getValue();
					break;
				}
			}
		}

		Person person = null;
		// check to forward
		if (check) {
			// find user information
			PersonDAO personDAO = new PersonDAO();
			person = personDAO.find(personId);

			// Check user not null
			if (person != null) {
				// Check user is teacher
				if (person.getRole().equals("lecturer")) {
					HeadLecturer header = HeadLecturerService.getHeadLecturerByPerson(person);
					Lecturer lecturer = LecturerService.getLecturerByPerson(person);
					// Check head
					if (lecturer.getIsHead() == 1) {
						chain.doFilter(request, response);
					} else {
						response.sendRedirect(request.getContextPath() + "/lecturer/home/");
					}
				} else {
					response.sendRedirect(request.getContextPath() + "/student/home/");
				}

			} else {
				// user null
				response.sendRedirect(request.getContextPath() + "/login");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}

	}

}