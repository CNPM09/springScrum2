package service;

import java.io.Console;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;


import service.SuperService;
import model.Account;
import model.Person;
import DAO.AccountDAO;


public class LoginService extends SuperService{
	
	AccountDAO accountDAO = null;
	
	public LoginService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.accountDAO = new AccountDAO();
	}
	
	public void GetLogin() throws ServletException, IOException {
		String url = "/default/login.jsp";
		super.forwardToPage(url);
		
	}
	
	
	public void PostLogin() throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			super.setEncoding();
			// define default url
			String url = "/default/login.jsp";
			String errorMessage = "";
			

			// get parameters from login box
			String role = this.request.getParameter("position_choice");
			String username = this.request.getParameter("username");
			String password = this.request.getParameter("password");
			System.out.print(username);
			System.out.print(role);
			
			Account foundAccount = getAccount(username);

			//Account foundAccount = this.accountDAO.findbyUser(username);
			System.out.print(foundAccount.getUsername());
			System.out.print("000");

			// find Person
			Person person = null;
			if (foundAccount != null) {
				person = foundAccount.getPerson();
				System.out.print(person.getFullName());
			}
			
			System.out.print("abc");

	
			// check if this account is existing
			if (foundAccount != null && checkRole(role, person)) {
				if (password.equals(foundAccount.getPassword())) {
					
					// define user id cookie timeout 30'
					Cookie c = new Cookie("uid", person.getPersonId());
					c.setMaxAge(30 * 60);
					c.setPath("/");
					this.response.addCookie(c);
					
					// define url base on role
					if (role.equals("student")) {
						// forward to student home page
						url = "/student/home";
					} 
						else if (role.equals("lecturer")) {
						// forward to teacher home page
						url = "/lecturer/home";
						
					} 
						else if (role.equals("admin")) {
						// forward to admin home page
						url = "/admin/home";
					}
						else if (role.equals("head_lecturer")) {
							// forward to admin home page
							url = "/head-lecturer/home";
					}
					

				} else {
					// exist account but incorrect password was found
					url = "/default/login.jsp";
					errorMessage = "* Login password is not correct !";
				}

			} else {
				// doesn't exist account
				url = "/default/login.jsp";
				errorMessage = "* Account not found !";
			}

			// save information to reuse
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			request.setAttribute("error", errorMessage);

			// forward request to jsp file
			if (!url.equals("/default/login.jsp")) {
				super.redirectToPage(request.getContextPath() + url);
			} else {
				System.out.print("Login fail");
				super.forwardToPage(url);
			}
		} catch (Exception e) {
			
		}
	}
	private boolean checkRole(String roleName, Person user) {
		if (user.getRole().equals(roleName)) {
			return true;
		}
		return false;
	}
	private Account getAccount(String username) {
		AccountDAO ad = new AccountDAO();
		List<Account> listAccounts = ad.findAll();
		for (Account a : listAccounts) {
			if (username.equals(a.getUsername())) {
				System.out.print(a);
				return a;
			}
		}
		return null;
	}

}