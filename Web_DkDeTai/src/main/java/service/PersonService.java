package service;

//import DAO.JpaUtils;
//import model.Person;
//
//import javax.persistence.*;

import java.io.Console;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.JpaUtils;
import DAO.LecturerDAO;
import DAO.PersonDAO;
import service.SuperService;
import model.Account;
import model.Lecturer;
import model.Person;
import DAO.AccountDAO;

public class PersonService extends SuperService{
	PersonDAO personDAO = null;

	public PersonService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.personDAO = new PersonDAO();
	}
	
	public PersonService() {}

	public Person getPersonByUsername(String username){
        System.out.println("getPersonByUsername()");

//        JpaUtils<Person> jpaUtils = new JpaUtils();
//        System.out.println("1");
//        EntityManagerFactory emf = jpaUtils.getEntityManagerFactory();
//        System.out.println("2");
//        EntityManager em = jpaUtils.getEntityManager();
//        System.out.println("3");
//        em.getTransaction().begin();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_DkDeTai");
        System.out.println("1");
        EntityManager em = emf.createEntityManager();
        System.out.println("2");
        em.getTransaction().begin();

        Person person = new Person();

        try {
            System.out.println("11");
            final String strQuery = "Select a.person " +
                    "From Account a " +
                    "WHERE a.username = :username";
            TypedQuery<Person> query = em.createQuery(strQuery, Person.class);
            query.setParameter("username", username);
            person = query.getSingleResult();
            System.out.println("person: " + person.getPersonId());
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        finally {
            em.close();
            emf.close();
        }
        return person;
    }
	public Person getPersonByPersonId(String personId){
		Person person = new Person();
		person = this.personDAO.find(personId);
		return person;
	}
	
	public Person getPersonByEmail(String email){
		Person person = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		if (this.personDAO.findWithNamedQuery("Person.findPersonByEmail", map).size() >0 )
		{
			person = this.personDAO.findWithNamedQuery("Person.findPersonByEmail", map).get(0);
		}
		return person;
	}
	public Lecturer getLecturerByPerson() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Lecturer lecturer = new Lecturer();
		Person person = new Person();
		
		PersonService personService = new PersonService(request, response);
		
		LecturerDAO LecturerDAO = new LecturerDAO();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		person = personService.getPersonByEmail(username);
		
		map.put("personId", person.getPersonId());
		lecturer = LecturerDAO.findWithNamedQuery("Student.getStudentByPersonId", map).get(0);
		return lecturer;
	}

	
}
