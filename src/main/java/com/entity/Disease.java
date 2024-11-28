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
@Table(name = "diseases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disease {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int diseaseId;
	
	@Column(name = "disease_name", nullable = false)
	private String diseaseName;
	
	@Column(name = "patient_id", nullable = false)
	private int patientId;
	
	@Column(name = "patient_name", nullable = false, length = 50)
	private String patientName;
	
	@Column(name = "patient_age", nullable = false)
	@Min(value = 0, message = "Age cannot be negative")
	@Max(value = 120, message = "Age cannot exceed 120")
	private int patientAge;
	
	@Column(name = "status", nullable = false, length = 30)
	private String status;
}
