package DAO;

import java.util.List;
import java.util.Map;

import model.Groupstudent;

public class GroupStudentDAO extends JpaUtils<Groupstudent> implements GenericDAO<Groupstudent> {
	public GroupStudentDAO() {
		super();
	}

	@Override
	public Groupstudent create(Groupstudent entity) {
		return super.create(entity);
	}

	@Override
	public Groupstudent update(Groupstudent entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Groupstudent.class, id);
	}

	@Override
	public Groupstudent find(Object primaryKey) {
		return super.find(Groupstudent.class, primaryKey);
	}

	@Override
	public List<Groupstudent> findAll() {
		String queryName = "GroupStudent.findAll";
		return super.findAll(queryName, Groupstudent.class);
	}

	@Override
	public List<Groupstudent> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public List<Groupstudent> pagination(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String randomId() {
		return super.randomId("GS");
	}
	
	public List<Groupstudent> findByBoard(Map<String, Object> params) {
		String queryName = "GroupStudent.findByBoard";
		return super.findWithNamedQuery(queryName, params);
	}
}