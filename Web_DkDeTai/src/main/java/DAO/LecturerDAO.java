package DAO;

import java.util.List;
import java.util.Map;

import model.Lecturer;
public class LecturerDAO extends JpaUtils<Lecturer> implements GenericDAO<Lecturer>{
	public LecturerDAO( ) {
		super();
	}
	
	@Override
	public Lecturer create(Lecturer entity) {
		return super.create(entity);
	}
	
	@Override
	public Lecturer update(Lecturer entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Lecturer.class, id);
	}
	
	@Override
	public Lecturer find(Object primaryKey) {
		return super.find(Lecturer.class, primaryKey);
	}
	
	@Override
	public List<Lecturer> findAll(){
		String queryName = "Lecturer.findAll";
		return super.findAll(queryName, Lecturer.class);
	}
	
	@Override
	public List<Lecturer> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public String randomId() {
		return super.randomId("LE");
	}

	@Override
	public List<Lecturer> pagination(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}