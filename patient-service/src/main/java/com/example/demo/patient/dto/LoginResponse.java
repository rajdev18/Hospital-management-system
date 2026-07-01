package com.example.demo.patient.dto;

public class LoginResponse {
	  private String token;
	    private String email;
	    private String message;

	    public LoginResponse(String token, String email, String message) {
	        this.token = token;
	        this.email = email;
	        this.message = message;
	    }

	    // getters
	    public String getToken() { return token; }
	    public String getEmail() { return email; }
	    public String getMessage() { return message; }

}
