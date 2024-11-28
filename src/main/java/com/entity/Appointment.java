package com.entity;

import java.sql.Date;
import java.sql.Time;

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
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentId;
	
	@Column(name = "doctor_id", nullable = false)
	private int doctorId;
	
	@Column(name = "patient_id", nullable = false)
	private int patientId;
	
	@Column(name = "appointment_date", nullable = false)
	private Date appointmentDate;
	
	@Column(name = "appointment_time", nullable = false)
	private Time appointmentTime;
}
