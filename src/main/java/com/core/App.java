package com.core;

import java.util.Scanner;

import com.manage.ManageAppointment;
import com.manage.ManageDisease;
import com.manage.ManageDoctor;
import com.manage.ManagePatient;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\n-------------Hospital Management System--------------");
			System.out.println("1. Manage Doctors");
			System.out.println("2. Manage Patients");
			System.out.println("3. Manage Appointments");
			System.out.println("4. Manage Diseases");
			System.out.println("5. Exit");
			
			System.out.print("\nSelect an option: ");
			choice = scanner.nextInt();
			
			switch(choice) {
					case 1 : 
							new ManageDoctor().manage();
							break;
							
					case 2 :
							new ManagePatient().manage();
							break;
							
					case 3 :
							new ManageAppointment().manage();
							break;
							
					case 4 : 
							new ManageDisease().manage();
							break;
							
					case 5 : 
							System.out.println("Exiting the application...");
							break;
							
					default:
							System.out.println("Invalid choice! Please try again.");
			}
		}while(choice != 5);
		
		scanner.close();
	}
}