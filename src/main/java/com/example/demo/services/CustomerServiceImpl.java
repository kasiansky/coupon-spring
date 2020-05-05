package com.example.demo.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.UniqueValueException;
import com.example.demo.repositories.CouponRepository;
import com.example.demo.repositories.CustomerRepository;
/**
 * This class used as service, make operations as customer
 * @author mark
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService,ClientFacade {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired 
	private CouponRepository couponRepository;

	private int customerId;
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * this method used to purchase coupon, to bind coupon with customer in db
	 * @param couponId 
	 * @param customerId 
	 * @return void
	 * @throws NotFoundException if coupon not found
	 * @throws MySQLException if coupon already purchased
	 */
	@Override
	public void purchaseCoupon(int couponId, int customerId) throws NotFoundException, MySQLException {
		Coupon checkCoupon = couponRepository.findById(couponId).get();
		
		
		Coupon customerCoupon = customerRepository.getCustomerCoupon(couponId, customerId);
		
		Customer customer = customerRepository.findById(customerId).get();
		 
		
		if (checkCoupon == null){
			throw new NotFoundException("Coupon with ID : " + couponId + " is not found");
		}
			if (customerCoupon != null){
			throw new MySQLException("Coupon is already purchased");
		}
			if(checkCoupon.getAmount() <= 0){
			throw new ValidationException("Coupon is out of stock");
		}
			if(checkCoupon.getEndDate().before(new Date(System.currentTimeMillis())))
			throw new ValidationException("Coupon is expired " + checkCoupon.getEndDate());
		customer.addCoupon(checkCoupon);
		checkCoupon.addCustomer(customer);
		checkCoupon.setAmount(checkCoupon.getAmount()-1);
		couponRepository.save(checkCoupon);
		customerRepository.save(customer);
	}
	/**
	 * this method used to get all purchased coupons from db
	 * @param customerId - all purchased coupons belongs to
	 * @return all purchased coupons
	 */
	@Override
	public ArrayList<Coupon> getAllPurchasedCoupons(int customerId) {
		
		return customerRepository.getAllPurchasedCoupons(customerId);
		
	}
	/**
	 * this method used to get all purchased coupons by type from db
	 * @param customerId 
	 * @param type - of coupon
	 * @return AllPurchasedCouponsByType 
	 */
	@Override
	public ArrayList<Coupon> getAllPurchasedCouponsByType(int customerId, CouponType type) {
		return customerRepository.getAllPurchasedCouponsByType(customerId, type);
		
	}
	/**
	 * this method used to get all purchased coupons by price from db
	 * @param customerId
	 * @param price - of coupon
	 * @return AllPurchasedCouponsByPrice
	 */
	@Override
	public ArrayList<Coupon> getAllPurchasedCouponsByPrice(int customerId, double price) {
		return customerRepository.getAllPurchasedCouponsByPrice(customerId, price);
		
	}
	/**
	 * this method used to get customer details
	 * @param customerId 
	 * @return customer
	 */
	@Override
	public Optional<Customer> getCustomerDetails(int customerId) {
		return customerRepository.findById(customerId);
		
		
		
	}
	/**
	 * this method used to get all system coupons
	 * @return coupons
	 */
	@Override
	public ArrayList<Coupon> getAllSystemCoupons() {
		return (ArrayList<Coupon>) couponRepository.findAll();
	}



	
}
