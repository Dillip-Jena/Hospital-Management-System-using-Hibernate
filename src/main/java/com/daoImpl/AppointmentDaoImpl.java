package com.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.GenericDao;
import com.entity.Appointment;
import com.util.HiberUtil;

public class AppointmentDaoImpl implements GenericDao<Appointment>{
	
	@Override
	public void save(Appointment appointment) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.persist(appointment);
			transaction.commit();
		}catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save appointment " +e);
        }
	}
	
	@Override
	public Appointment findById(int id) {
		try(Session session = HiberUtil.getSession()){
			return session.get(Appointment.class, id);
		}catch(Exception e) {
			throw new RuntimeException("Failed to find appoint with id "+id, e);
		}
	}
	
	@Override
	public List<Appointment> findAll(){
		try(Session session = HiberUtil.getSession()){
			String hql = "from Appointment";
			Query<Appointment> query = session.createQuery(hql, Appointment.class);
			List<Appointment> appointments = query.list();
			return appointments;
		}catch(Exception e) {
			throw new RuntimeException("Failed to retrieve all appointments", e);
		}
	}
	
	@Override
	public void update(Appointment appointment) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			session.merge(appointment);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to update appoinment", e);
		}
	}
	
	@Override
	public void delete(int id) {
		Transaction transaction = null;
		try(Session session = HiberUtil.getSession()){
			transaction = session.beginTransaction();
			Appointment appointment = session.get(Appointment.class, id);
			if(appointment != null) {
				session.remove(appointment);
				transaction.commit();
			}
			else {
				System.out.println("Appointment with id "+id+" not found.");
			}
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Failed to delete appointment with id "+id, e);
		}
	}
}
