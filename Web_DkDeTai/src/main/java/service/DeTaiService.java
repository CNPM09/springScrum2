package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TopicDAO;
import model.Lecturer;
import model.Person;
import model.RegistrationPeriod;
import model.Student;
import model.Topic;
import DAO.LecturerDAO;
import DAO.GroupStudentDAO;

public class DeTaiService extends SuperService {

	private static TopicDAO topicDAO = new TopicDAO();
	private static GroupStudentDAO groupDAO = new GroupStudentDAO();
	LecturerDAO LecturerDAO = new LecturerDAO();

	public DeTaiService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);

	}

	public DeTaiService() {
	}

	public void getTopic(byte option) throws ServletException, IOException {
		String activeButtonUnselected = "";
		String activeButtonSelected = "topic_registration-filter-active";
		String url = "/default/student/topicRegistration.jsp";
		List<Topic> topics = new ArrayList<Topic>();
		DeTaiService topicService = new DeTaiService(request, response);

		if (option == 0) {
			activeButtonUnselected = "topic_registration-filter-active";
			activeButtonSelected = "";
		} else if (option == 1) {
			activeButtonUnselected = "";
			activeButtonSelected = "topic_registration-filter-active";
		}
		topics = topicService.getTopicByOptionSelect(option);
		this.request.setAttribute("activeButtonUnselected", activeButtonUnselected);
		this.request.setAttribute("activeButtonSelected", activeButtonSelected);
		this.request.setAttribute("topics", topics);
		this.request.getRequestDispatcher(url).forward(request, response);
	}

	

	public void handleGetListTopic() throws ServletException, IOException {
		try {
			String pageUrl = "/pages/admin/topic/topic.jsp";
			List<Topic> topics = topicDAO.findAll();
			this.request.setAttribute("topics", topics);
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		} catch (Exception e) {
			String pageUrl = "/pages/500.jsp";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}

	public List<Topic> getTopicByOptionSelect(byte option) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_selected", option);
		List<Topic> topics = new ArrayList<Topic>();
		topics = DeTaiService.topicDAO.findWithNamedQuery("Topic.getTopicByConditionSelect", map);
		return topics;
	}

	public void GetLecturerAddTopic() throws ServletException, IOException {
		String url = "/default/lecturer/ThemDeTai.jsp";
		super.forwardToPage(url);
	}

	public void PostLecturerAddTopic() throws ServletException, IOException {
		this.request.setCharacterEncoding("UTF-8");
		try {
			// get saved information in session
			HttpSession session = this.request.getSession();
			Lecturer lecturer = (Lecturer) session.getAttribute("lecturer");
			RegistrationPeriod period = (RegistrationPeriod) session.getAttribute("period");

			// get parameter from the add topic form
			String topicId = this.request.getParameter("topicId");
			System.out.println("topic Id is: " + topicId);
			String topicName = this.request.getParameter("topicName");
			int noOfMember = Integer.parseInt(request.getParameter("numberOfMember"));
			String description = this.request.getParameter("topicDescription");

			// set topic properties
			Topic newTopic = new Topic();
			newTopic.setTopicId(topicId);
			newTopic.setLecturer(lecturer);
			newTopic.setMajor(lecturer.getMajor());
			newTopic.setRegistrationperiod(period);
			newTopic.setTopicName(topicName);
			newTopic.setMaxMoMember(noOfMember);
			newTopic.setDescription(description);

			// save
			TopicDAO td = new TopicDAO();
			td.create(newTopic);

			this.request.setAttribute("isAdded", "1");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}


	private List<Topic> findSelectedTopic(Byte isSelected) {
		List<Topic> foundTopics = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSelected", isSelected);
		foundTopics = topicDAO.findWithNamedQuery("Topic.findSelectedTopic", map);
		return foundTopics;
	}

	public void getLecturerTopic() throws ServletException {

		try {
			String url = "/default/lecturer/quanlydetaiGV.jsp";
			System.out.print("Hello");
			HttpSession session = request.getSession();
			Person person = (Person) session.getAttribute("person");
			String isSelected = request.getParameter("select");
			String isActive = request.getParameter("status");
			if (isSelected == null) {
				isSelected = "0";
			}
			// get lecturer
			Lecturer lecturer = LecturerService.getLecturerByPerson(person);
			// get list lecturer's topic
			List<Topic> topics = null;
			if (lecturer != null) {
				if (isActive == null) {
					// if isActive null, choose the topics that were not selected by student
					topics = getSpecifiedTopic(lecturer, Byte.valueOf(isSelected));
				} else {
					topics = getTopicByLecturerAndStatus(lecturer);
				}
				request.setAttribute("topics", topics);
			}
			super.forwardToPage(url);
			
			// remove session
			request.getSession().setAttribute("deletedTopicStatus", null);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Topic> getTopicByLecturerAndStatus(Lecturer lecturer) {
		List<Topic> topics = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lecturer", lecturer);
		topics = topicDAO.findWithNamedQuery("Topic.findTopicByLecturerAndStatus", map);
		return topics;
	}

	public static List<Topic> getSpecifiedTopic(Lecturer lecturer, Byte isSelected) {
		List<Topic> topics = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lecturer", lecturer);
		map.put("isSelected", isSelected);
		topics = topicDAO.findWithNamedQuery("Topic.findSpecifiedTopic", map);
		return topics;
	}


	public void xoaTopic() throws ServletException, IOException {
		try {
			super.setEncoding();
			// Url
			String pageUrl = super.getContextPath() + "/lecturer/manage-topic?status=0";

			System.out.print("haba");
			// Get param
			String topicId = request.getParameter("topic");
			String deletedTopicStatus = "";

			// Get data
			Topic topic = DeTaiService.topicDAO.find(topicId);

			// Check
			if (topic != null && topic.getStatus() == 0) {
				DeTaiService.topicDAO.delete(topicId);
				deletedTopicStatus = "success";
			} else {
				deletedTopicStatus = "fail";
			}

			request.getSession().setAttribute("deletedTopicStatus", deletedTopicStatus);
			// redirect
			super.redirectToPage(pageUrl);
		} catch (Exception e) {
			String pageUrl = "/lecturer/manage-topic?status=0";
			this.request.getRequestDispatcher(pageUrl).forward(request, response);
		}
	}

}
