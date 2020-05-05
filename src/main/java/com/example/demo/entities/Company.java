package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Company {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String companyName;
	@Column(nullable = false)
	private String password;
	@Column(unique = true, nullable = false)
	private String email;
	@JsonIgnore
	@OneToMany( mappedBy="company" , cascade = CascadeType.ALL )
	private Collection<Coupon> coupons = new ArrayList<>();
	
	
	public Company(){}

	public Company(int id, String companyName, String password, String email) {
		this.id = id;
		this.companyName = companyName;
		this.password = password;
		this.email = email;
	}
	


	public Company(String companyName, String password, String email) {
		super();
		this.companyName = companyName;
		this.password = password;
		this.email = email;
		
	}


	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}
	
	
	
	
}
