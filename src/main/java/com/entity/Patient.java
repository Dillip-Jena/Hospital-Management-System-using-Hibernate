package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "contact_number", unique = true, nullable = false, length = 10)
	private String contactNumber;
	
	@Column(name = "age", nullable = false)
	@Min(value = 0, message = "Age cannot be negative")
	@Max(value = 120, message = "Age cannot be greater than 120")
	private int age;
	
	@Column(name = "address", length = 255)
	private String address;
}
