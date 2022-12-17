package DAO;

import java.util.List;
import java.util.Map;

import model.Admin;
public class AdminDAO extends JpaUtils<Admin> implements GenericDAO<Admin>{
	public AdminDAO( ) {
		super();
	}
	
	@Override
	public Admin create(Admin entity) {
		return super.create(entity);
	}
	
	@Override
	public Admin update(Admin entity) {
		return super.update(entity);
	}
	
	@Override
	public void delete(Object id) {
		super.delete(Admin.class, id);
	}
	
	@Override
	public Admin find(Object primaryKey) {
		return super.find(Admin.class, primaryKey);
	}
	
	@Override
	public List<Admin> findAll(){
		String queryName = "Admin.findAll";
		return super.findAll(queryName, Admin.class);
	}
	
	@Override
	public List<Admin> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		return super.findWithNamedQuery(queryName, parameters);
	}

	@Override
	public String randomId() {
		return super.randomId("AD");
	}

	@Override
	public List<Admin> pagination(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
}