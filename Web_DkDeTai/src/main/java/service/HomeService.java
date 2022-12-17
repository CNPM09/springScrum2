package service;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class HomeService extends SuperService{

	public HomeService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public HomeService() {}
	
	public void handleGetStudentHome() throws ServletException, IOException {
		// define url
		String url = "/default/student/student.jsp";	
		
		// forward information to jsp file
		super.forwardToPage(url);
	}
	
	public void handlePostHome() throws ServletException, IOException {
		
	}
	
	public void handleGetTeacherHomeService() throws ServletException, IOException{
		// define url for teacher home page
		String url = "/default/lecturer/lecturer.jsp";
		//forward information to jsp file
		super.forwardToPage(url);
	}
	
	public void handleGetAdminHomeService() throws ServletException, IOException{
		// define url for Admin home page
		String url = "/default/admin/admin.jsp";
		//forward information to jsp file
		super.forwardToPage(url);
	}
	public void handleGetHeadHomeService() throws ServletException, IOException{
		// define url for Admin home page
		String url = "/default/headLecturer/headlecturer.jsp";
		//forward information to jsp file
		super.forwardToPage(url);
	}
	public void handleGetStudentHomeService() throws ServletException, IOException{
		// define url for Admin home page
		String url = "/default/student/student.jsp";
		//forward information to jsp file
		super.forwardToPage(url);
	}


}