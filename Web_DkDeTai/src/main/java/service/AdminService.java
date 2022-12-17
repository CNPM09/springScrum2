package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AdminDAO;
import model.Person;
import model.Admin;

public class AdminService extends SuperService{
	private static AdminDAO AdminDAO = new AdminDAO();

	public AdminService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public static Admin getAdminByPerson(Person person) {
		Admin pAdmin = null;
		Map<String, Object> maping = new HashMap<String, Object>();
		maping.put("person", person);
		if (AdminDAO.findWithNamedQuery("Admin.findAdminByPerson", maping).size() >0 )
		{
			pAdmin = AdminDAO.findWithNamedQuery("Admin.findAdminByPerson", maping).get(0);
		}
		return pAdmin;
	}
}
