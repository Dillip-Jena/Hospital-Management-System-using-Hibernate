package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "contact_number", unique = true, nullable = false, length = 10)
	private String contactNumber;
	
	@Column(name = "specialization", nullable = false)
	private String specialization;
}
