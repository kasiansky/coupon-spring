package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UniqueValueException;

public interface CustomerService {
	

	
     void purchaseCoupon(int couponId, int customerId) throws  NotFoundException, MySQLException;

	 ArrayList<Coupon>getAllPurchasedCoupons(int customerId);

	 ArrayList<Coupon> getAllPurchasedCouponsByType(int customerId, CouponType type);

	 ArrayList<Coupon> getAllPurchasedCouponsByPrice(int customerId, double price);

	Optional<Customer> getCustomerDetails(int customerId);
     
	ArrayList<Coupon>getAllSystemCoupons();
	

	
}
