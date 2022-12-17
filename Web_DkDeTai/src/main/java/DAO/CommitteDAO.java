package DAO;

import model.Committe;

import java.util.List;
import java.util.Map;

public class CommitteDAO extends JpaUtils<Committe> implements GenericDAO<Committe>{
    public CommitteDAO( ) {
        super();
    }

    @Override
    public Committe create(Committe entity) {
        return super.create(entity);
    }

    @Override
    public Committe update(Committe entity) {
        return super.update(entity);
    }

    @Override
    public void delete(Object id) {
        super.delete(Committe.class, id);
    }

    @Override
    public Committe find(Object primaryKey) {
        return super.find(Committe.class, primaryKey);
    }

    @Override
    public List<Committe> findAll(){
        String queryName = "Committe.findAll";
        return super.findAll(queryName, Committe.class);
    }

    @Override
    public List<Committe> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
        return super.findWithNamedQuery(queryName, parameters);
    }

    @Override
    public String randomId() {
        return super.randomId("CM");
    }

    @Override
    public List<Committe> pagination(int currentPage, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return 0;
    }
}