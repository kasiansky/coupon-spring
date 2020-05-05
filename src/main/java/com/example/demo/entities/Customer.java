package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column( nullable = false)
	private String customerName;
	@Column(nullable = false)
	private String password;
	@Column(unique = true, nullable = false)
	private String email;
	@JsonIgnore
	@ManyToMany ( cascade= CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Collection<Coupon> coupons = new ArrayList<>(); 
	
	public Customer(){}
	public Customer(int id, String customerName, String password, String email) {
		
		this.id = id;
		this.customerName = customerName;
		this.password = password;
		this.email = email;
	}
	
	public Customer(String customerName, String password) {
		super();
		this.customerName = customerName;
		this.password = password;
		
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public ArrayList<Coupon> getCoupons() {
		return (ArrayList<Coupon>) coupons;
	}
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	public int getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + customerName + ", password=" + password + ", coupons=" + coupons
				+ "]";
	}
	public void addCoupon(Coupon checkCoupon) {
		coupons.add(checkCoupon);
		
	}
	
}
