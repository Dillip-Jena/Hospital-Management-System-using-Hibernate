package com.manage;

import java.util.List;
import java.util.Scanner;

import com.daoImpl.PatientDaoImpl;
import com.entity.Patient;

public class ManagePatient {
	private final PatientDaoImpl patientDao = new PatientDaoImpl();
	private final Scanner scanner = new Scanner(System.in);
	
	public void manage() {
		int choice;
		
		do {
			System.out.println("\n----------Manage Patient-----------");
			System.out.println("1. Add Patient");
			System.out.println("2. View Patient by ID");
			System.out.println("3. View All Patients");
			System.out.println("4. Update Patient");
			System.out.println("5. Delete Patient");
			System.out.println("6. Go Back to Main Menu");
			
			System.out.print("\nSelect an option: ");
			choice = scanner.nextInt();
			
			switch(choice) {
					case 1 : 
								addPatient();
								break;
								
					case 2 :
								viewPatientById();
								break;
								
					case 3 : 
								viewAllPatients();
								break;
								
					case 4 : 
								updatePatient();
								break;
								
					case 5 : 
								deletePatient();
								break;
								
					case 6 : 
								System.out.println("Returning to main menu...");
								break;
								
					default: 
								System.out.println("Invalid choice! Please try again.");
			}
		}while(choice != 6);
	}
	
	private void addPatient() {
		Patient patient = new Patient();
		
		System.out.print("Enter patient name: ");
		patient.setName(scanner.next());
		System.out.print("Enter contact number: ");
		patient.setContactNumber(scanner.next());
		System.out.print("Enter age: ");
		patient.setAge(scanner.nextInt());
		System.out.print("Enter address: ");
		patient.setAddress(scanner.next());
		
		patientDao.save(patient);
		System.out.println("Patient added successfully!");
	}
	
	private void viewPatientById() {
		System.out.print("Enter Patient ID: ");
		int patientId = scanner.nextInt();
		
		Patient patient = patientDao.findById(patientId);
		if(patient != null) {
			System.out.println("+----------+-------------------+--------+------------------+---------------------+");
			System.out.println("|    ID    |       Name        |  Age   |    Contact no    |       Address       |");
			System.out.println("+----------+-------------------+--------+------------------+---------------------+");
			System.out.printf("|     %-5d|       %-12s|   %-5d|    %-14s|       %-14s|\n", patient.getPatientId(), patient.getName(), patient.getAge(), patient.getContactNumber(), patient.getAddress());
			System.out.println("+----------+-------------------+--------+------------------+---------------------+");
		}
		else {
			System.out.println("Patient not found.");
		}
	}
	
	public void viewAllPatients() {
		List<Patient> patients = patientDao.findAll();
		
		if(patients == null || patients.isEmpty()) {
			System.out.println("No patients found.");
			return;
		}
		
		System.out.println("+----------+-------------------+--------+------------------+---------------------+");
		System.out.println("|    ID    |       Name        |  Age   |    Contact no    |       Address       |");
		System.out.println("+----------+-------------------+--------+------------------+---------------------+");
		for(Patient patient : patients) {
			System.out.printf("|     %-5d|       %-12s|   %-5d|    %-14s|       %-14s|\n", patient.getPatientId(), patient.getName(), patient.getAge(), patient.getContactNumber(), patient.getAddress());
			System.out.println("+----------+-------------------+--------+------------------+---------------------+");
		}
	}
	
	private void updatePatient() {
		System.out.print("Enter patient ID to update: ");
		int patientId = scanner.nextInt();
		
		Patient patient = patientDao.findById(patientId);
		if(patient == null) {
			System.out.println("Patient with ID "+ patientId + " not found");
			return;
		}
		
		System.out.print("Enter new name: ");
		patient.setName(scanner.next());
		System.out.print("Enter new contact no: ");
		patient.setContactNumber(scanner.next());
		System.out.print("Enter new age: ");
		patient.setAge(scanner.nextInt());
		System.out.print("Enter new address: ");
		patient.setAddress(scanner.next());
		
		patientDao.update(patient);
		System.out.println("Patient updated successfully!");
	}
	
	private void deletePatient() {
		System.out.print("Enter patient ID to delete: ");
		int patientId = scanner.nextInt();
		
		if(patientDao.findById(patientId) == null) {
			System.out.println("Patient with ID "+ patientId + " does not exist!");
			return;
		}
		
		patientDao.delete(patientId);
		System.out.println("Patient deleted successfully!");
	}
}
