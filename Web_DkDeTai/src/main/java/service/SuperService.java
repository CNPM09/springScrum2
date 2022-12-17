package service;

import model.Person;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuperService {
  protected HttpServletRequest request = null;
  protected HttpServletResponse response = null;

  public SuperService(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }
  
  protected void setEncoding() throws UnsupportedEncodingException {
		this.request.setCharacterEncoding("UTF-8");
		this.response.setCharacterEncoding("UTF-8");
	}
  public SuperService() {}

  public void forwardToPage(String url) throws ServletException, IOException {
	  this.setEncoding();
	  this.request.getRequestDispatcher(url).forward(request, response);
  }

//  Redirect to url Pattern
  public void redirectToPage(String urlPattern) throws IOException {
    this.response.sendRedirect(urlPattern);
  }

//  Log info or error
  public void log(String msg) {
    System.out.println(msg);
  }

  // set Attribute
  public void setAttribute(String key, Object value) {
    this.request.setAttribute(key, value);
  }

  // Get Param
  public String getParameter(String key) {
    return this.request.getParameter(key);
  }

  // Get context

  public String getContextPath() {
    return this.request.getContextPath();
  }

}