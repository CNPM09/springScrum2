package service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LecturerDAO;
import DAO.PersonDAO;
import model.Lecturer;
import model.Person;

public class LecturerProfileService extends SuperService {
	
	PersonDAO personDAO = null;
	LecturerDAO lecturerDAO= null;


	public LecturerProfileService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		
		this.personDAO = new PersonDAO();
		this.lecturerDAO = new LecturerDAO();
	}
	public LecturerProfileService() {}
	public void GetLecturerProfile() throws ServletException, IOException{
		// define default url
		String url = "/default/lecturer/LecturerProfile.jsp";
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
