package service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ThongBaoDAO;
import DAO.StudentDAO;
import model.Notification;
import model.Person;
import model.Topic;
import service.StudentService;

public class ThongBaoService extends SuperService {
	ThongBaoDAO thongbaoDAO = null;

	public ThongBaoService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.thongbaoDAO = new ThongBaoDAO();
	}

	public ThongBaoService() {
	}

	public String randomIdNotDuplicate() {
		ThongBaoDAO thongbaoDAO = new ThongBaoDAO();
		String id = "";
		do {
			id = thongbaoDAO.randomId();
		} while (thongbaoDAO.find(id) != null);
		return id;
	}

	public void addNotification(String sender_id, String reciever_id, String title, String content)
			throws UnsupportedEncodingException {
		super.setEncoding();
		StudentDAO studentDAO = new StudentDAO();
		Date date = new Date();

		Person senderPerson = new Person();
		Person recieverPerson = new Person();
		Notification notification = new Notification();

		if (sender_id != reciever_id) {
			senderPerson = studentDAO.find(sender_id).getPerson();
			recieverPerson = studentDAO.find(reciever_id).getPerson();

			notification.setNotificationId(this.randomIdNotDuplicate());
			notification.setNotificationTitle(title);
			notification.setPerson1(senderPerson);
			notification.setPerson2(recieverPerson);
			notification.setStatus(0); // chưa đọc
			notification.setContent(content);
			notification.setTime(date);
			thongbaoDAO.create(notification);
		} else {
			System.out.println("No Success");
		}
	}

	public void getNotificationByLoginAccount() throws ServletException, IOException {
		String url = "/pages/client/student/home.jsp";
		StudentService studentService = new StudentService(request, response);
		ThongBaoDAO thongbaoDAO = new ThongBaoDAO();
		List<Notification> notifications = new ArrayList<Notification>();
		String person_id = studentService.laysinhviendata().getPerson().getPersonId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId", person_id);

		notifications = thongbaoDAO.findWithNamedQuery("Notification.getNotificationByLoginAccount", map);
		this.request.setAttribute("notifications", notifications);
		this.request.getRequestDispatcher(url).forward(request, response);

		request.getSession().setAttribute("registrationPeriodForTeacherStatus", null);
	}

	public void showDetailOneNotification() throws ServletException, IOException {
		String notification_id = this.request.getParameter("notification_id");
		ThongBaoDAO notificationDAO = new ThongBaoDAO();
		Notification notification = new Notification();

		notification = this.thongbaoDAO.find(notification_id);
		notification.setStatus((byte) 1);
		notificationDAO.update(notification);
		getNotificationByLoginAccount();
//		System.out.println("test");
	}

	public boolean createApprovalTopicNotification(Person sentPerson, Topic topic)
			throws ServletException, IOException {
		super.setEncoding();
		Notification notification = new Notification();
		try {
			notification.setNotificationId(randomIdNotDuplicate());
			notification.setPerson1(sentPerson);
			notification.setPerson2(topic.getLecturer().getPerson());
			notification.setTime(new Date());
			notification.setNotificationTitle("Đề tài được duyệt");
			notification.setContent("Đề tài " + topic.getTopicName() + " đã được duyệt");
			notification.setStatus(0);
			thongbaoDAO.create(notification);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}

	}

	public void getNotificationsByPerson(Person receivedPerson) throws ServletException, IOException {
		List<Notification> notifications = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("person2", receivedPerson);
		notifications = thongbaoDAO.findWithNamedQuery("Notification.getNotificationsByPerson", map);
		System.out.println(notifications);
		if (notifications != null) {
			for (Notification n : notifications) {
				System.out.println(n.getContent());
			}
		}
		this.request.setAttribute("notifications", notifications);
	}

	public boolean updateStatusNotification() throws IOException {
		String url = "/teacher/home";
		try {
			String id = this.request.getParameter("id");
			Notification notification = thongbaoDAO.find(Notification.class, id);
			if (notification == null) {
				return false;
			}
			notification.setStatus(1);
			thongbaoDAO.update(notification);
			super.redirectToPage(request.getContextPath() + url);
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			url = "/pages/500.jsp";
			super.redirectToPage(request.getContextPath() + url);
			return false;
		}
	}

}
