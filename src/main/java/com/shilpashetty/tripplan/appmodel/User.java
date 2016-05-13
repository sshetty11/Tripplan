package com.shilpashetty.Tripplan.appmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	public User(){

	}

	public User(String firstName,String lastName,String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@JsonProperty
	public String getFirstName() {
		return firstName;
	}
	@JsonProperty
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@JsonProperty
	public String getLastName() {
		return lastName;
	}
	@JsonProperty
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@JsonProperty
	public String getEmail() {
		return email;
	}
	@JsonProperty
	public void setEmail(String email) {
		this.email = email;
	}
}
