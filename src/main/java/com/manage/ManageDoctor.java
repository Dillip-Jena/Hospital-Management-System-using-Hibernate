package com.manage;

import java.util.List;
import java.util.Scanner;

import com.daoImpl.DoctorDaoImpl;
import com.entity.Doctor;

public class ManageDoctor {
	private final DoctorDaoImpl doctorDao = new DoctorDaoImpl();
	private final Scanner scanner = new Scanner(System.in);
	
	public void manage() {
		int choice;
		
		do {
			System.out.println("\n------------Manage Doctor-----------------");
			System.out.println("1. Add Doctor");
			System.out.println("2. View Doctor by ID");
			System.out.println("3. View All Doctors");
			System.out.println("4. Update Doctor");
			System.out.println("5. Delete Doctor");
			System.out.println("6. Go Back to Main Menu");
			
			System.out.print("\nSelect an option: ");
			choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
					case 1 : 
								addDoctor();
								break;
								
					case 2 : 
								viewDoctorById();
								break;
								
					case 3 : 
								viewAllDoctors();
								break;
								
					case 4 : 
								updateDoctor();
								break;
								
					case 5 : 
								deleteDoctor();
								break;
								
					case 6 :
								System.out.println("Returning to main menu...");
								break;
								
					default:
								System.out.println("Invalid choice! Please try again.");
			}
		}while(choice != 6);
	}
	
	private void addDoctor() {
		Doctor doctor = new Doctor();
		
		System.out.print("Enter doctor name: ");
		doctor.setName(scanner.nextLine());
		System.out.print("Enter contact number: ");
		doctor.setContactNumber(scanner.nextLine());
		System.out.print("Enter specialization: ");
		doctor.setSpecialization(scanner.nextLine());
		
		doctorDao.save(doctor);
		System.out.println("Doctor added successfully!");
	}
	
	private void viewDoctorById() {
		System.out.print("Enter Doctor ID: ");
		int doctorId = scanner.nextInt();
		scanner.nextLine();
		
		Doctor doctor = doctorDao.findById(doctorId);
		if(doctor != null) {
			System.out.println("+--------------+------------------+-----------------------+-------------------+");
			System.out.println("|    ID        |      Name        |    Specialization     |     Contact no    |");
			System.out.println("+--------------+------------------+-----------------------+-------------------+");
			System.out.printf("|     %-9d|     %-13s|     %-18s|     %-14s|\n",doctor.getDoctorId(), doctor.getName(), doctor.getSpecialization(), doctor.getContactNumber());
			System.out.println("+--------------+------------------+-----------------------+-------------------+");
		}
		else {
			System.out.println("Doctor not found.");
		}
	}
	
	public void viewAllDoctors() {
		List<Doctor> doctors = doctorDao.findAll();
		
		if(doctors == null || doctors.isEmpty()) {
			System.out.println("No doctors found.");
			return;
		}
		
		System.out.println("+--------------+------------------+-----------------------+-------------------+");
		System.out.println("|    ID        |      Name        |    Specialization     |     Contact no    |");
		System.out.println("+--------------+------------------+-----------------------+-------------------+");
		for(Doctor doctor : doctors) {
			System.out.printf("|     %-9d|     %-13s|     %-18s|     %-14s|\n",doctor.getDoctorId(), doctor.getName(), doctor.getSpecialization(), doctor.getContactNumber());
			System.out.println("+--------------+------------------+-----------------------+-------------------+");
		}
	}
	
	private void updateDoctor() {
		System.out.print("Enter doctor ID to update: ");
		int doctorId = scanner.nextInt();
		scanner.nextLine();
		
		Doctor doctor = doctorDao.findById(doctorId);
		if(doctor == null) {
			System.out.println("Doctor with ID "+ doctorId +" not found");
			return;
		}
		
		System.out.print("Enter new name: ");
		doctor.setName(scanner.nextLine());
		System.out.print("Enter new contact no: ");
		doctor.setContactNumber(scanner.nextLine());
		System.out.print("Enter new specialization: ");
		doctor.setSpecialization(scanner.nextLine());
		
		doctorDao.update(doctor);
		System.out.println("Doctor updated successfully!");
	}
	
	private void deleteDoctor() {
		System.out.print("Enter doctor ID to delete: ");
		int doctorId = scanner.nextInt();
		scanner.nextLine();
		
		if(doctorDao.findById(doctorId) == null) {
			System.out.println("Doctor with ID "+ doctorId + " does not exist!");
			return;
		}
		
		doctorDao.delete(doctorId);
		System.out.println("Doctor deleted successfully!");
	}
}
