package DAO;

import java.util.List;
import java.util.Map;

import model.HeadLecturer;

public class HeadLecturerDAO extends JpaUtils<HeadLecturer> implements GenericDAO<HeadLecturer>{
	public  HeadLecturerDAO() {
		super();
	}
	
	@Override
	public  HeadLecturer create( HeadLecturer entity) {
		return super.create(entity);
	}
	
	@Override
	public  HeadLecturer update( HeadLecturer entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete( HeadLecturer.class, id);
	}
	
	@Override
	public HeadLecturer find(Object primaryKey) {
		return super.find(HeadLecturer.class, primaryKey);
	}
	
	@Override
	public List<HeadLecturer> findAll(){
		String queryName = "HeadLecturer.findAll";
		return super.findAll(queryName, HeadLecturer.class);
	}
	
	@Override
	public List<HeadLecturer> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public String randomId() {
		return super.randomId("HL");
	}

	@Override
	public List<HeadLecturer> pagination(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}