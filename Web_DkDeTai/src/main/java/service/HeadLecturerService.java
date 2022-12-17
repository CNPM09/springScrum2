package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HeadLecturerDAO;
import model.Person;
import model.HeadLecturer;

public class HeadLecturerService extends SuperService{
	private static HeadLecturerDAO HeadLecturerDAO = new HeadLecturerDAO();

	public HeadLecturerService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	public HeadLecturerService() {}
	
	public static HeadLecturer getHeadLecturerByPerson(Person person) {
		HeadLecturer pHLecturer = null;
		Map<String, Object> maping = new HashMap<String, Object>();
		maping.put("person", person);
		if (HeadLecturerDAO.findWithNamedQuery("HeadLecturer.findHeadLecturerByPerson", maping).size() >0 )
		{
			pHLecturer = HeadLecturerDAO.findWithNamedQuery("HeadLecturer.findHeadLecturerByPerson", maping).get(0);
		}
		return pHLecturer;
	}
	
}