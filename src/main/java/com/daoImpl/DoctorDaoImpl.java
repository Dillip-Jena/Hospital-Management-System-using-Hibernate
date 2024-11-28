package com.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.GenericDao;
import com.entity.Doctor;
import com.util.HiberUtil;

public class DoctorDaoImpl implements GenericDao<Doctor>{

	@Override
	public void save(Doctor doctor) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.persist(doctor);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to save doctor", e);
		}
	}
	
	@Override
	public Doctor findById(int id) {
		try(Session session = HiberUtil.getSession()){
			return session.get(Doctor.class, id);
		}catch(Exception e) {
			throw new RuntimeException("Failed to find doctor with id "+id, e);
		}
	}
	
	@Override
	public List<Doctor> findAll(){
		try(Session session = HiberUtil.getSession()){
			String hql = "from Doctor";
			Query<Doctor> query = session.createQuery(hql, Doctor.class);
			List<Doctor> doctors = query.list();
			return doctors;
		}catch(Exception e) {
			throw new RuntimeException("Failed to retrieve all doctors", e);
		}
	}
	
	@Override
	public void update(Doctor doctor) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.merge(doctor);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to update doctor", e);
		}
	}
	
	@Override
	public void delete(int id) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			Doctor doctor = session.get(Doctor.class, id);
			if(doctor != null) {
				session.remove(doctor);
				transaction.commit();
			}
			else {
				System.out.println("Doctor with id "+ id + " not found.");
			}
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to delete doctor with id "+ id, e);
		}
	}
}
