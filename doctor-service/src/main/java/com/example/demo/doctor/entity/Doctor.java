package com.example.demo.doctor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "doctors")
public class Doctor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
 
    private String password;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    private String phone;
    private int experience;
    private String qualification;
    private boolean available = true;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Doctor(Long id, @NotBlank(message = "Name is required") String name,
			@Email(message = "Invalid email") String email, @NotBlank(message = "Password is required") String password,
			@NotBlank(message = "Specialization is required") String specialization, String phone, int experience,
			String qualification, Boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.specialization = specialization;
		this.phone = phone;
		this.experience = experience;
		this.qualification = qualification;
		this.available = available;
	}

    public Doctor() {
    	
    }
    
}
