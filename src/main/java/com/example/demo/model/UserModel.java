package com.example.demo.model;
/**
 * This class used to create user object that saves the statement of client side user.
 * Login controller receiving from client side user model object as a parameter.
 * @author mark
 * @see LoginConroller
 */
public class UserModel {
	private String userName, password, type;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
