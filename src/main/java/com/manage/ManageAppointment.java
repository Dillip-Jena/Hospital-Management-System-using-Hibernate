package com.manage;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import com.daoImpl.AppointmentDaoImpl;
import com.entity.Appointment;

public class ManageAppointment {
	private final AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
	private final ManageDoctor manageDoctor = new ManageDoctor();
	private final ManagePatient managePatient = new ManagePatient();
	private final Scanner scanner = new Scanner(System.in);
	
	public void manage() {
		int choice;
		
		do {
			System.out.println("\n----------Manage Appointment---------------");
			System.out.println("1. Add Appointment");
			System.out.println("2. View Appointment by ID");
			System.out.println("3. View All Appointments");
			System.out.println("4. Update Appointment");
			System.out.println("5. Delete Appointment");
			System.out.println("6. Go Back to Main Menu");
			
			System.out.print("\nSelect an option: ");
			choice = scanner.nextInt();
			
			switch(choice) {
					case 1 :
								addAppointment();
								break;
								
					case 2 :
								viewAppointmentById();
								break;
								
					case 3 :
								viewAllAppointments();
								break;
								
					case 4 : 
								updateAppointment();
								break;
								
					case 5 : 	
								deleteAppointment();
								break;
								
					case 6 : 
								System.out.println("Returning to main menu...");
								break;
								
					default: 
								System.out.println("Invalid choice! Please try again.");
			}
		}while(choice != 6);
	}
	
	private void addAppointment() {
		Appointment appointment = new Appointment();
		
		System.out.println("\nAvailable Doctors: ");
		manageDoctor.viewAllDoctors();
		
		System.out.println("\nAvailable Patients: ");
		managePatient.viewAllPatients();
		
		System.out.print("Enter doctor ID: ");
		appointment.setDoctorId(scanner.nextInt());
		scanner.nextLine();
		System.out.print("Enter patient ID: ");
		appointment.setPatientId(scanner.nextInt());
		scanner.nextLine();
		System.out.print("Enter appointment date (YYYY-MM-DD): ");
		appointment.setAppointmentDate(Date.valueOf(scanner.next()));
		System.out.print("Enter appointment time (HH:MM:SS): ");
		appointment.setAppointmentTime(Time.valueOf(scanner.next()));
		
		appointmentDao.save(appointment);
		System.out.println("Appointment added successfully!");
	}
	
	private void viewAppointmentById() {
		System.out.print("Enter appointment ID: ");
		int appointmentId = scanner.nextInt();
		scanner.nextLine();
		
		Appointment appointment = appointmentDao.findById(appointmentId);
		if(appointment != null) {
			System.out.println("+----------+---------------+----------------+------------------+-------------+");
			System.out.println("|    ID    |   Doctor ID   |   Patient ID   |       Date       |    Time     |");
			System.out.println("+----------+---------------+----------------+------------------+-------------+");
			System.out.printf("|     %-5d|       %-8d|        %-8d|    %-14s|   %-10s|\n",appointment.getAppointmentId(), appointment.getDoctorId(), appointment.getPatientId(), appointment.getAppointmentDate(), appointment.getAppointmentTime());
			System.out.println("+----------+---------------+----------------+------------------+-------------+");
	  	}
	  	else {
	  		System.out.println("Appointment not found.");
		}
	}
	
	private void viewAllAppointments() {
		List<Appointment> appointments = appointmentDao.findAll();
		
		if(appointments == null || appointments.isEmpty()) {
			System.out.println("No appointment found.");
			return;
		}
		
		System.out.println("+----------+---------------+----------------+------------------+-------------+");
		System.out.println("|    ID    |   Doctor ID   |   Patient ID   |       Date       |    Time     |");
		System.out.println("+----------+---------------+----------------+------------------+-------------+");
		for(Appointment appointment : appointments) {
			System.out.printf("|     %-5d|       %-8d|        %-8d|    %-14s|   %-10s|\n",appointment.getAppointmentId(), appointment.getDoctorId(), appointment.getPatientId(), appointment.getAppointmentDate(), appointment.getAppointmentTime());
			System.out.println("+----------+---------------+----------------+------------------+-------------+");
		}
	}
	
	private void updateAppointment() {
		System.out.print("Enter appointment ID to update: ");
		int appointmentId = scanner.nextInt();
		scanner.nextLine();
		
		Appointment appointment = appointmentDao.findById(appointmentId);
		if(appointment == null) {
			System.out.println("Appointment with ID "+ appointmentId + " not found");
			return;
		}
		
		System.out.print("Enter new doctor ID: ");
		appointment.setDoctorId(scanner.nextInt());
		scanner.nextLine();
		System.out.print("Enter new patient ID: ");
		appointment.setPatientId(scanner.nextInt());
		scanner.nextLine();
		System.out.print("Enter new appointment date (YYYY-MM-DD): ");
		appointment.setAppointmentDate(Date.valueOf(scanner.next()));
		System.out.print("Enter new appointment time (HH:MM:SS): ");
		appointment.setAppointmentTime(Time.valueOf(scanner.next()));
		
		appointmentDao.update(appointment);
		System.out.println("Appointment updated successfully!");
	}
	
	private void deleteAppointment() {
		System.out.print("Enter appointment Id to delete: ");
		int appointmentId = scanner.nextInt();
		scanner.nextLine();
		
		if(appointmentDao.findById(appointmentId) == null) {
			System.out.println("Appointment with ID "+ appointmentId + " does not exist!");
			return;
		}
		
		appointmentDao.delete(appointmentId);
		System.out.println("Appointment deleted successfully!");
	}
}
