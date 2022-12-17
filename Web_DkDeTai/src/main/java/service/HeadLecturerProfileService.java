package service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HeadLecturerDAO;
import DAO.PersonDAO;
import model.HeadLecturer;
import model.Person;

public class HeadLecturerProfileService extends SuperService {
	
	PersonDAO personDAO = null;
	HeadLecturerDAO HeadlecturerDAO= null;


	public HeadLecturerProfileService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		
		this.personDAO = new PersonDAO();
		this.HeadlecturerDAO = new HeadLecturerDAO();
	}
	public HeadLecturerProfileService() {}
	public void GetHeadLecturerProfile() throws ServletException, IOException{
		// define default url
		String url = "/default/headLecturer/headlecturerprofile.jsp";
		// forward to specified url
		HttpSession session = this.request.getSession();
		String username = (String) session.getAttribute("username");
		PersonService personService = new PersonService(request, response);
		Person person = new Person();
		person = personService.getPersonByEmail(username);
		this.request.setAttribute("person", person);
		this.request.getRequestDispatcher(url).forward(request, response);
	}

}
