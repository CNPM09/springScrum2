package controller.client.lecturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DeTaiService;

@WebServlet(urlPatterns = { "/lecturer/manage-topic/add", "/lecturer/manage-topic/add/" })
public class AddTopicLecturer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddTopicLecturer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeTaiService topic = new DeTaiService(request, response);
		topic.GetLecturerAddTopic();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeTaiService topic = new DeTaiService(request, response);
		topic.PostLecturerAddTopic();
		doGet(request, response);
	}

}