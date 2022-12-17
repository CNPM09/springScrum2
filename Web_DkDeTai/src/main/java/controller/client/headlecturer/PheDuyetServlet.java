package controller.client.headlecturer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.query.criteria.internal.expression.ConcatExpression;

import DAO.PersonDAO;
import DAO.TopicDAO;
import model.Lecturer;
import model.Topic;
import service.ThongBaoService;


@WebServlet(urlPatterns = { "/head-lecturer/approve/accept"})
public class PheDuyetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TopicDAO topicDAO = null;
	public PheDuyetServlet() {
		super();
		topicDAO = new TopicDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			Lecturer head = (Lecturer)session.getAttribute("lecturer");
			// Topic
			byte status = 1;
			String acceptApprovalTopicStatus = "";
			String topicId = request.getParameter("topic_id");
			System.out.println(topicId);
			if (topicId != null) {
				Topic topic = this.topicDAO.find(topicId);

				// Update
				topic.setStatus(status);
				this.topicDAO.update(topic);
				// create notification
				ThongBaoService ns = new ThongBaoService(request, response);
				if (ns.createApprovalTopicNotification(head.getPerson(), topic)) {
					System.out.println("Create notification successfully");
				}else {
				System.out.println("Create notification fail");
			}
				acceptApprovalTopicStatus = "success";
			}
			request.getSession().setAttribute("acceptApprovalTopicStatus", acceptApprovalTopicStatus);
			response.sendRedirect(request.getContextPath() + "/head-lecturer/approve");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}