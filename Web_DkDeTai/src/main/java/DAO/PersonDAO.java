package DAO;

import java.util.List;
import java.util.Map;

import model.Person;
import org.hibernate.Hibernate;

import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonDAO extends JpaUtils<Person> implements GenericDAO<Person>{
	public PersonDAO( ) {
		super();
	}
	
	@Override
	public Person create(Person entity) {
		return super.create(entity);
	}
	
	@Override
	public Person update(Person entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Person.class, id);
	}
	
	@Override
	public Person find(Object primaryKey) {
		return super.find(Person.class, primaryKey);
	}
	
	@Override
	public List<Person> findAll(){
		String queryName = "Person.findAll";
		return super.findAll(queryName, Person.class);
	}
	
	@Override
	public List<Person> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public String randomId() {
		return super.randomId("PE");
	}

	@Override
	public List<Person> pagination(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}