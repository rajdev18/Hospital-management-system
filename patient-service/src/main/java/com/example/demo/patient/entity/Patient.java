package com.example.demo.patient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "patients")

public class Patient {
	
		
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

	    private String phone;
	    private int age;
	    private String bloodGroup;
	    private String address;
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
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getBloodGroup() {
			return bloodGroup;
		}
		public void setBloodGroup(String bloodGroup) {
			this.bloodGroup = bloodGroup;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Patient(Long id, @NotBlank(message = "Name is required") String name,
				@Email(message = "Invalid email") String email,
				@NotBlank(message = "Password is required") String password, String phone, int age, String bloodGroup,
				String address) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.phone = phone;
			this.age = age;
			this.bloodGroup = bloodGroup;
			this.address = address;
		}
	    
	    public Patient() {
	    	
	    }

}
