package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LecturerDAO;
import model.Person;
import model.Lecturer;

public class LecturerService extends SuperService{
	private static LecturerDAO LecturerDAO = new LecturerDAO();

	public LecturerService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	public LecturerService() {}
	
	public static Lecturer getLecturerByPerson(Person person) {
		Lecturer pLecturer = null;
		Map<String, Object> maping = new HashMap<String, Object>();
		maping.put("person", person);
		if (LecturerDAO.findWithNamedQuery("Lecturer.findLecturerByPerson", maping).size() >0 )
		{
			pLecturer = LecturerDAO.findWithNamedQuery("Lecturer.findLecturerByPerson", maping).get(0);
		}
		return pLecturer;
	}
}
