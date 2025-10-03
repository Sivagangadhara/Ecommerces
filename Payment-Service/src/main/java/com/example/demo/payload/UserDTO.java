package com.example.demo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	private Long id;
    private String name;
    @JsonProperty("email")
    private String email;
    
    
    
	public UserDTO() {
		super();
	}
	public UserDTO(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
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
    
    
}
