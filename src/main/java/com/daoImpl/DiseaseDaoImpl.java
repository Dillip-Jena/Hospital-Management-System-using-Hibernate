package com.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.GenericDao;
import com.entity.Disease;
import com.util.HiberUtil;

public class DiseaseDaoImpl implements GenericDao<Disease>{
	
	@Override
	public void save(Disease disease) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.persist(disease);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to save disease", e);
		}
	}
	
	@Override
	public Disease findById(int id) {
		try(Session session = HiberUtil.getSession()){
			return session.get(Disease.class, id);
		}catch(Exception e) {
			throw new RuntimeException("Failed to find disease with id" + id, e);
		}
	}
	
	@Override
	public List<Disease> findAll(){
		try(Session session = HiberUtil.getSession()){
			String hql = "from Disease";
			Query<Disease> query = session.createQuery(hql, Disease.class);
			List<Disease> diseases = query.list();
			return diseases;
		}catch(Exception e) {
			throw new RuntimeException("Failed to retrieve all diseases", e);
		}
	}
	
	@Override
	public void update(Disease disease) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.merge(disease);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to update disease", e);
		}
	}
	
	@Override
	public void delete(int id) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			Disease disease = session.get(Disease.class, id);
			if(disease != null) {
				session.remove(disease);
				transaction.commit();
			}
			else {
				System.out.println("Disease with id"+ id + "not found.");
			}
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to delete disease with id "+ id, e);
		}
	}
}
