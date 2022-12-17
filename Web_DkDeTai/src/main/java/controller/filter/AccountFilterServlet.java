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

import model.Account;
import model.Person;
import DAO.AccountDAO;
import DAO.PersonDAO;
import model.Lecturer;
import model.Student;
import model.Admin;
import model.HeadLecturer;

import service.StudentService;
import service.LecturerService;
import service.SuperService;
import service.HeadLecturerService;
import service.AdminService;

@WebFilter(urlPatterns = {"/student/*", "/lecturer/*","/head-lecturer/*","/admin/*"})
public class AccountFilterServlet extends SuperService implements Filter {
	
	  public void init(FilterConfig filterConfig) throws ServletException {
	    Filter.super.init(filterConfig);
	  }

	 @Override
	 public void destroy() {
			//close any resources here
		}
	 
	 @Override
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		      throws IOException, ServletException {
		 
		    HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse res = (HttpServletResponse) response;

		    
		    // get cookie is existing 
			boolean flag = false;
			String personId = "";
			Cookie[] cookies = req.getCookies();
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
				HttpSession session = req.getSession();
				PersonDAO personDAO = new PersonDAO();
				person = personDAO.find(Person.class, personId);
				session.setAttribute("person", person);
				
				if (person.getRole().equals("student")) {
					Student student = null;
					student = StudentService.getStudentByPerson(person);
					session.setAttribute("student", student);
				}else if (person.getRole().equals("lecturer")){
					Lecturer lecturer = null;
					lecturer = LecturerService.getLecturerByPerson(person);
					session.setAttribute("lecturer", lecturer);
				}
				else if (person.getRole().equals("head_lecturer")){
					HeadLecturer head_lecturer = null;
					head_lecturer = HeadLecturerService.getHeadLecturerByPerson(person);
					session.setAttribute("head_lecturer",head_lecturer);
				}
				else if (person.getRole().equals("admin")){
					Admin admin = null;
					admin = AdminService.getAdminByPerson(person);
					session.setAttribute("admin",admin);
				}
				// action on goal page
				chain.doFilter(request, response);
			}else {
				// destroy session
				HttpSession session = req.getSession(false);
				if (session != null) {
					session.invalidate();
				}
				// forward to login page
				String url = "/login";
				res.sendRedirect(req.getContextPath() + url);
			}
			
		}

}
