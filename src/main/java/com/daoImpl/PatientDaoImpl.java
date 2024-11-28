package com.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.GenericDao;
import com.entity.Patient;
import com.util.HiberUtil;

public class PatientDaoImpl implements GenericDao<Patient>{
	
	@Override
	public void save(Patient patient) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.persist(patient);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to save patient", e);
		}
	}
	
	@Override
	public Patient findById(int id) {
		try(Session session = HiberUtil.getSession()){
			return session.get(Patient.class, id);
		}catch(Exception e) {
			throw new RuntimeException("Failed to find patient with id "+id, e);
		}
	}
	
	@Override
	public List<Patient> findAll(){
		try(Session session = HiberUtil.getSession()){
			String hql = "from Patient";
			Query<Patient> query = session.createQuery(hql, Patient.class);
			List<Patient> patients = query.list();
			return patients;
		}catch(Exception e) {
			throw new RuntimeException("Failed to retrieve all patients", e);
		}
	}
	
	@Override
	public void update(Patient patient) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.merge(patient);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to update patient", e);
		}
	}
	
	@Override
	public void delete(int id) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			Patient patient = session.get(Patient.class, id);
			if(patient != null) {
				session.remove(patient);
				transaction.commit();
			}
			else {
				System.out.println("Patient with id "+ id + "not found.");
			}
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to delete patient with id "+id, e);
		}
	}
}
