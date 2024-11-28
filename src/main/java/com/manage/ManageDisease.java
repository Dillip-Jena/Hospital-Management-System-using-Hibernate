package com.manage;

import java.util.List;
import java.util.Scanner;

import com.daoImpl.DiseaseDaoImpl;
import com.daoImpl.PatientDaoImpl;
import com.entity.Disease;
import com.entity.Patient;

public class ManageDisease {
	private final DiseaseDaoImpl diseaseDao = new DiseaseDaoImpl();
	private final PatientDaoImpl patientDao = new PatientDaoImpl();
	private final Scanner scanner = new Scanner(System.in);
	
	public void manage() {
		int choice;
		
		do {
			System.out.println("\n---------------Manage Disease-------------");
			System.out.println("1. Add Disease");
			System.out.println("2. View Disease by ID");
			System.out.println("3. View All Diseases");
			System.out.println("4. Update Disease");
			System.out.println("5. Delete Disease");
			System.out.println("6. Go Back to Main Menu");
			
			System.out.print("\nSelect an option: ");
			choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
					case 1 :
								addDisease();
								break;
								
					case 2 : 
								viewDiseaseById();
								break;
								
					case 3 : 
								viewAllDiseases();
								break;
						        
					case 4 : 
								updateDisease();
								break;
								
					case 5 :
								deleteDisease();
								break;
								
					case 6 :
								System.out.println("Returning to Main Menu....");
								
					default : 
								System.out.println("Invalid option. Plz enter valid choice!!!");
			} 
		}while(choice != 6);
	}
	
	private void addDisease() {
		System.out.print("Enter patient ID: ");
		int patientId = scanner.nextInt();
		scanner.nextLine();
		
		Patient patient = patientDao.findById(patientId);
		if(patient == null) {
			System.out.println("Patient with ID "+ patientId + "not found!");
			return;
		}
		
		Disease newDisease = new Disease();
		newDisease.setPatientId(patientId);
		newDisease.setPatientName(patient.getName());
		newDisease.setPatientAge(patient.getAge());
		
		System.out.print("Enter Disease Name: ");
		newDisease.setDiseaseName(scanner.nextLine());
		System.out.print("Enter Status: ");
		newDisease.setStatus(scanner.nextLine());
		
		diseaseDao.save(newDisease);
		System.out.println("Disease added successfully!");
	}
	
	private void viewDiseaseById() {
		System.out.print("Enter disease ID: ");
		int diseaseId = scanner.nextInt();
		scanner.nextLine();
		
		Disease disease = diseaseDao.findById(diseaseId);
		if(disease != null) {
			System.out.println("+--------+--------------+-----------------+--------------+-----------------+-------------+");
            System.out.println("|   ID   |  patient_id  |   patient_name  |  patient_age |   disease_name  |    status   |");
            System.out.println("+--------+--------------+-----------------+--------------+-----------------+-------------+");
            System.out.printf("|   %-5d|      %-8d|   %-14s|      %-8d|   %-14s|  %-11s|\n", disease.getDiseaseId(), disease.getPatientId(), disease.getPatientName(), disease.getPatientAge(), disease.getDiseaseName(), disease.getStatus());
            System.out.println("+--------+--------------+-----------------+--------------+-----------------+-------------+");
		}
		else {
			System.out.println("Disease not found!");
		}
	}
	
	private void viewAllDiseases() {
		List<Disease> diseases = diseaseDao.findAll();
		
		if(diseases == null || diseases.isEmpty()) {
			System.out.println("No diseases found.");
			return;
		}
		
		System.out.println("+--------+--------------+-----------------+--------------+-----------------+-------------+");
        System.out.println("|   ID   |  patient_id  |   patient_name  |  patient_age |   disease_name  |    status   |");
        System.out.println("+--------+--------------+-----------------+--------------+-----------------+-------------+");
        for(Disease disease : diseases) {
        	System.out.printf("|   %-5d|      %-8d|   %-14s|      %-8d|   %-14s|  %-11s|\n", disease.getDiseaseId(), disease.getPatientId(), disease.getPatientName(), disease.getPatientAge(), disease.getDiseaseName(), disease.getStatus());
            System.out.println("+--------+--------------+-----------------+--------------+-----------------+-------------+");
        }
	}
	
	private void updateDisease() {
		System.out.print("Enter disease ID to update: ");
		int diseaseId = scanner.nextInt();
		scanner.nextLine();
		
		Disease disease = diseaseDao.findById(diseaseId);
		if(disease == null) {
			System.out.println("Disease with ID "+ diseaseId +" not found!");
			return;
		}
		
		System.out.print("Enter patient ID: ");
		int patientId = scanner.nextInt();
		scanner.nextLine();
		
		Patient patient = patientDao.findById(patientId);
		if(patient == null) {
			System.out.println("Patient with ID "+ patientId + " not found!");
			return;
		}
		
		disease.setPatientId(patientId);
		disease.setPatientName(patient.getName());
		disease.setPatientAge(patient.getAge());
		
		System.out.print("Enter New Disease Name: ");
		disease.setDiseaseName(scanner.nextLine());
		System.out.print("Enter New Status: ");
		disease.setStatus(scanner.nextLine());
		
		diseaseDao.update(disease);
		System.out.println("Disease updated successfully!");
	}
	
	private void deleteDisease() {
		System.out.print("Enter disease ID to delete: ");
		int diseaseId = scanner.nextInt();
		scanner.nextLine();
		
		if(diseaseDao.findById(diseaseId) == null) {
			System.out.println("Disease with ID "+ diseaseId + " does not exist!");
			return;
		}
		
		diseaseDao.delete(diseaseId);
		System.out.println("Disease deleted successfully!");
	}
}
