package service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GroupStudentDAO;
import DAO.HelperUtils;
import DAO.RegistrationPeriodDAO;
import model.RegistrationPeriod;

public class RegistrationPriodService extends SuperService {

	RegistrationPeriodDAO registrationPeriodDAO = null;

	public RegistrationPriodService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.registrationPeriodDAO = new RegistrationPeriodDAO();
	}

	public RegistrationPriodService() {
	}

	public void handleGetList() throws ServletException, IOException {
		HttpSession session = this.request.getSession();

		String pageUrl = "/dafault/admin/registrationPriod/registrationPriod.jsp";
		try {
			String type = this.request.getParameter("type");
			byte isForLecturer = 1;
			if (type.equals("student")) {
				isForLecturer = 0;
			}
			List<RegistrationPeriod> registrationPeriods = this.registrationPeriodDAO
					.findByIsRegistrationLecturer(isForLecturer);
			this.request.setAttribute("type", type);
			this.request.setAttribute("registrationPeriods", registrationPeriods);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
		session.invalidate();
	}

	public void handleGetListIsDeleted() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		String pageUrl = "/pages/admin/registrationPriod/registrationPriod.jsp";
		try {
			String type = this.request.getParameter("type");
			byte isForLecturer = 1;
			if (type.equals("student")) {
				isForLecturer = 0;
			}
			List<RegistrationPeriod> registrationPeriodsIsDeleted = this.registrationPeriodDAO
					.findByIsRegistrationLecturerIsDeleted(isForLecturer);
			this.request.setAttribute("type", type);
			this.request.setAttribute("registrationPeriodsIsDeleted", registrationPeriodsIsDeleted);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
		session.invalidate();
	}

	public void handleGetEditForm() throws ServletException, IOException {
		String pageUrl = "/pages/admin/registrationPriod/editRegistrationPriod.jsp";
		String id = this.request.getParameter("id");
		try {
			String type = this.request.getParameter("type");
			this.request.setAttribute("type", type);
			RegistrationPeriod rp = this.registrationPeriodDAO.find(id);
			this.request.setAttribute("rp", rp);
//			System.out.println(rp.getCloseDate());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			pageUrl = "/pages/500.jsp";
		}
		this.request.getRequestDispatcher(pageUrl).forward(request, response);
	}

	public RegistrationPeriod getRegistrationPeriod(Byte isActive) {
		RegistrationPeriod period = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("isActive", isActive);
		if (this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).size() > 0) {
			period = this.registrationPeriodDAO.findWithNamedQuery("RegistrationPeriod.findByStatus", map).get(0);
		}
		return period;
	}

	public void updateRegistrationPeriod() throws ServletException, IOException {
		super.setEncoding();
		HttpSession session = this.request.getSession();
		String isRegistrationPeriodUpdate = "FAILED";
		String role = "student";
		String context = this.request.getContextPath();
		String url = context + "/admin/registration-priods?type=";

		try {
			Date date = new Date();
			String registrationPeriodId = this.request.getParameter("registrationPeriodId");
			String registrationPeriodName = this.request.getParameter("registrationPeriodName");
			String semeter = this.request.getParameter("semeter");
			String schoolYear = this.request.getParameter("schoolYear");

			Date openDate = HelperUtils.convertStringToDate(this.request.getParameter("openDate").split(" ")[0]);
			Date closeDate = HelperUtils.convertStringToDate(this.request.getParameter("closeDate").split(" ")[0]);
			String description = this.request.getParameter("description");
			Date currentDate = HelperUtils.convertStringToDate(HelperUtils.formatDate(date));
			byte isActive = Byte.parseByte(request.getParameter("isActive"));

			// Get data
			RegistrationPeriod registrationPeriod = registrationPeriodDAO.find(registrationPeriodId);

			if (registrationPeriod.getIsRegistrationLecturer() == 1) {
				role = "lecturer";
			}

			// Get count registration active
			Map<String, Object> param = new HashMap<>();
			param.put("isActive", (byte) 1);

			RegistrationPeriod registrationPeriodActive = this.registrationPeriodDAO.findByIsActive(param);
			int countRegistrationPriActive = this.registrationPeriodDAO.countByIsActive(param);
			final int maxRegistrationActive = 1;

			// Check: Ki???m tra xem trong b???ng ????ng k?? c?? bao nhi??u th???i gian ????ng k?? ??ang
			// ???????c active r???i. Code ??? d?????i ??ang l??m theo ki???u ch??? c?? m???t th???i gian ????ng k??
			// ???????c m??? th??i. N???u mu???n m??? th???i gian kh??c th?? c???n ????ng th???i gian ??ang m??? l???i
			if (countRegistrationPriActive >= maxRegistrationActive && isActive == 1 && registrationPeriodActive != null
					&& !registrationPeriodActive.getRegistrationPeriodId().equals(registrationPeriodId)) {
				isRegistrationPeriodUpdate = "FAILED";
			} else {
				if (closeDate.compareTo(currentDate) > 0 || closeDate.compareTo(currentDate) == 0) {
					if (openDate.compareTo(closeDate) < 0 || openDate.compareTo(closeDate) == 0) {

						registrationPeriod.setCloseDate(closeDate);
						registrationPeriod.setDescription(description);
						registrationPeriod.setOpenDate(openDate);
						registrationPeriod.setRegistrationPeriodName(registrationPeriodName);
						registrationPeriod.setSchoolYear(Integer.parseInt(schoolYear));
						registrationPeriod.setSemeter(Integer.parseInt(semeter));
						registrationPeriod.setIsActive(isActive);
						this.registrationPeriodDAO.update(registrationPeriod);
						isRegistrationPeriodUpdate = "SUCCESS";
					} else {
						isRegistrationPeriodUpdate = "FAILED";
					}
				} else {
					isRegistrationPeriodUpdate = "FAILED";
				}
			}

			url = url + role;
			session.setAttribute("isRegistrationPeriodUpdate", isRegistrationPeriodUpdate);
			this.response.sendRedirect(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			url = url + role;
			session.setAttribute("isRegistrationPeriodUpdate", isRegistrationPeriodUpdate);
			this.response.sendRedirect(url);
		}
	}

	public void softDeleteRegistrationPeriod() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		String isSoftDeleteRegistrationPeriod = "FAILED";
		String role = "student";
		String context = this.request.getContextPath();
		String url = context + "/admin/registration-priods/?type=";
		try {
			String registrationPeriodId = this.request.getParameter("registration-period-id");
			RegistrationPeriod registrationPeriod = registrationPeriodDAO.find(registrationPeriodId);
			registrationPeriod.setIsDeleted((byte) 1);

			if (registrationPeriod.getIsRegistrationLecturer() == 1) {
				System.out.println("Bang 1");
				role = "lecturer";
			}

			registrationPeriodDAO.update(registrationPeriod);

			url = url + role;
			isSoftDeleteRegistrationPeriod = "SUCCESS";
			session.setAttribute("isSoftDeleteRegistrationPeriod", isSoftDeleteRegistrationPeriod);
			this.response.sendRedirect(url);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			url = url + role;
			session.setAttribute("isSoftDeleteRegistrationPeriod", isSoftDeleteRegistrationPeriod);
			this.response.sendRedirect(url);
		}
	}

	public void restoreRegistrationPeriod() throws ServletException, IOException {
		HttpSession session = this.request.getSession();
		try {
			String type = this.request.getParameter("type");
			String id = this.request.getParameter("id");
			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
			RegistrationPeriod registrationPeriod = new RegistrationPeriod();

			String registrationPeriodId = this.request.getParameter("id");
			registrationPeriod = registrationPeriodDAO.find(registrationPeriodId);
			registrationPeriod.setIsDeleted((byte) 0);

			registrationPeriodDAO.update(registrationPeriod);
			session.setAttribute("isRestoreRegistrationPeriod", "SUCCESS");
			this.request.setAttribute("type", type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.setAttribute("isRestoreRegistrationPeriod", "FAILED");
		}
	}

	@SuppressWarnings("deprecation")
	public String createRegistrationPeriodForLecturer(String registrationPeriodName, int semeter, int schoolYear,
			byte isRegistrationLecturer, Date openDate, Date closeDate, String description) {
		try {
			Date date = new Date();
			Date currentDate =HelperUtils.convertStringToDate(HelperUtils.formatDate(date));
			RegistrationPeriod registrationPeriod = new RegistrationPeriod();
			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
			if (closeDate.compareTo(currentDate) > 0 || closeDate.compareTo(currentDate) == 0) {
				if (openDate.compareTo(closeDate) < 0 || openDate.compareTo(closeDate) == 0) {
					if (openDate.getYear() == closeDate.getYear() && closeDate.getYear() == currentDate.getYear()) {
						registrationPeriod.setRegistrationPeriodId(this.randomIdNotDuplicate());
						registrationPeriod.setRegistrationPeriodName(registrationPeriodName);
						registrationPeriod.setSemeter(semeter);
						registrationPeriod.setSchoolYear(schoolYear);
						registrationPeriod.setIsRegistrationLecturer(isRegistrationLecturer);
						registrationPeriod.setOpenDate(openDate);
						registrationPeriod.setCloseDate(closeDate);
						registrationPeriod.setDescription(description);
						// set active = 0 when created
						registrationPeriod.setIsActive((byte) 0);
						registrationPeriodDAO.create(registrationPeriod);

						return "SUCCESS";
					}
				}
			}
			return "FAILED";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAILED";
		}
	}

	public String createRegistrationPeriodForStudent(String registrationPeriodName, int semeter, int schoolYear,
			byte isRegistrationLecturer, Date openDate, Date closeDate, String description) {
		try {
			Date date = new Date();
			Date currentDate = HelperUtils.convertStringToDate(HelperUtils.formatDate(date));
			RegistrationPeriod registrationPeriod = new RegistrationPeriod();
			RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
			Map<String, Object> map = new HashMap<>();

			List<RegistrationPeriod> registrationPeriods = new ArrayList<RegistrationPeriod>();
			if (closeDate.compareTo(openDate) > 0 || closeDate.compareTo(openDate) == 0) {
				map.put("semeter", semeter);
				map.put("schoolYear", schoolYear);
				map.put("openDate", openDate);
				registrationPeriods = registrationPeriodDAO
						.findWithNamedQuery("RegistrationPeriod.checkConditionsToCreateRegistrationPeriod", map);
				if (registrationPeriods.size() > 0) {
//					System.out.println("===========STUDENT ????NG K?? TH??NH C??NG====================");
					registrationPeriod.setRegistrationPeriodId(this.randomIdNotDuplicate());
					registrationPeriod.setRegistrationPeriodName(registrationPeriodName);
					registrationPeriod.setSemeter(semeter);
					registrationPeriod.setSchoolYear(schoolYear);
					registrationPeriod.setIsRegistrationLecturer(isRegistrationLecturer);
					registrationPeriod.setOpenDate(openDate);
					registrationPeriod.setCloseDate(closeDate);
					registrationPeriod.setDescription(description);
					registrationPeriod.setIsActive((byte) 0);
					registrationPeriodDAO.create(registrationPeriod);

					return "SUCCESS";
				}
			}
			return "FAILED";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAILED";
		}
	}

	public void createRegistrationPeriod() throws ServletException, IOException {
		super.setEncoding();
		String role = "student";
		String context = this.request.getContextPath();
		String url = context + "/admin/registration-priods?type=";
		HttpSession session = this.request.getSession();
		String isCreateRegistrationPeriod = "";

		try {
			String registrationPeriodName = this.request.getParameter("registrationPeriodName");
			int semeter = Integer.parseInt(this.request.getParameter("semester"));
			int schoolYear = Integer.parseInt(this.request.getParameter("schoolYear"));
			byte isRegistrationLecturer = Byte.parseByte(this.request.getParameter("isRegistrationLecturer"));
			Date openDate = HelperUtils.convertStringToDate(this.request.getParameter("openDate").split(" ")[0]);
			Date closeDate = HelperUtils.convertStringToDate(this.request.getParameter("closeDate").split(" ")[0]);
			String description = this.request.getParameter("description");

			// Get role
			if (isRegistrationLecturer == 1) {
				role = "lecturer";
			}

			Map<String, Object> params = new HashMap<>();
			params.put("schoolYear", schoolYear);
			params.put("isRegistrationLecturer", isRegistrationLecturer);
			int countRegistrationInYear = this.registrationPeriodDAO.countBySchoolYearAndIsHead(params);
			final int maxCountRegistrationInYear = 2;

			if (countRegistrationInYear < maxCountRegistrationInYear) {
				if (isRegistrationLecturer == 1) {
					isCreateRegistrationPeriod = createRegistrationPeriodForLecturer(registrationPeriodName, semeter,
							schoolYear, isRegistrationLecturer, openDate, closeDate, description);
				} else if (isRegistrationLecturer == 0) {
					isCreateRegistrationPeriod = createRegistrationPeriodForStudent(registrationPeriodName, semeter,
							schoolYear, isRegistrationLecturer, openDate, closeDate, description);
				}
			} else {
				isCreateRegistrationPeriod = "FAILED";
			}
			url = url + role;
			session.setAttribute("isCreateRegistrationPeriod", isCreateRegistrationPeriod);
			this.response.sendRedirect(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			url = url + role;
			session.setAttribute("isCreateRegistrationPeriod", isCreateRegistrationPeriod);
			this.response.sendRedirect(url);
		}
	}

	public String randomIdNotDuplicate() {
		RegistrationPeriodDAO registrationPeriodDAO = new RegistrationPeriodDAO();
		String id = "";
		do {
			id = registrationPeriodDAO.randomId();
		} while (registrationPeriodDAO.find(id) != null);
		return id;
	}

}