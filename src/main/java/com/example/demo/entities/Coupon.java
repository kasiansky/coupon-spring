	package com.example.demo.entities;






import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Coupon {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date startDate,endDate;
	@Column(nullable = false)
	private int amount;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CouponType type;
	@Column(nullable = false)
	private String message;
	@Column(nullable = false)
	private double price;
	private String image;
	@JsonIgnore
	@ManyToOne
	private Company company;
	@JsonIgnore
	@ManyToMany( cascade= CascadeType.PERSIST, mappedBy="coupons", fetch =FetchType.LAZY )
	private Collection<Customer> customers = new ArrayList<>(); 

	
	/**
	 * @return the customers
	 */
	public Collection<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

	public Coupon(int id, String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, String image, Company company, Collection<Customer> customers) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
		this.company = company;
		this.customers = customers;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	public Coupon(){}

	public Coupon(int id, String title, Date startDate,Date endDate, int amount, CouponType type, String message,
			double price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price,
			String image) {
		
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	
}
