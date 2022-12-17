package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.StudentDAO;
import model.Person;
import model.Student;

public class StudentService extends SuperService{
	private static StudentDAO studentDAO = new StudentDAO();

	public StudentService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public static Student getStudentByPerson(Person person) {
		Student pStudent = null;
		Map<String, Object> maping = new HashMap<String, Object>();
		maping.put("person", person);
		if (studentDAO.findWithNamedQuery("Student.findStudentByPerson", maping).size() >0 )
		{
			pStudent = studentDAO.findWithNamedQuery("Student.findStudentByPerson", maping).get(0);
		}
		return pStudent;
	}
	public Student laysinhviendata() {
		Map<String, Object> map = new HashMap<String, Object>();	
		Student student = new Student();
		Person person = new Person();
		PersonService personService = new PersonService(request, response);
		StudentDAO studentDAO = new StudentDAO();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		person = personService.getPersonByEmail(username);
		
		map.put("personId", person.getPersonId());
		student = studentDAO.findWithNamedQuery("Student.getStudentByPersonId", map).get(0);
		return student;
	}

}
