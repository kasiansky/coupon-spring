package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
import com.example.demo.exceptions.CouponException;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UniqueValueException;

public interface CompanyService {
	
	
	void createCoupon(Coupon coupon, int companyId) throws UniqueValueException, MySQLException, CouponException, NotFoundException;

	void removeCoupon(int couponId, int companyId) throws MySQLException;

	
	Coupon getCoupon(int id, int companyId);
	
	ArrayList<Coupon> getCouponByType(CouponType couponType, int companyId);


	ArrayList<Coupon> getAllCoupons(int companyid);

	void updateCoupon(Coupon coupon, int companyId) throws MySQLException;
	
	void removeExpiredCoupon();
	
	Optional<Company> getCompanyDetails(int companyId);
	 
	
}
