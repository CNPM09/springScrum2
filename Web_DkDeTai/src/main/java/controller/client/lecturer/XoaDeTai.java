package controller.client.lecturer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Topic;
import service.DeTaiService;

@WebServlet(urlPatterns = { "/lecturer/manage-topic/delete" })
public class XoaDeTai extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XoaDeTai() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeTaiService topicService = new DeTaiService(request, response);
		topicService.xoaTopic();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
