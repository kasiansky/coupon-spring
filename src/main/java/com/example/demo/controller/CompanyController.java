package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
import com.example.demo.exceptions.CouponException;
import com.example.demo.exceptions.CouponSystemException;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UniqueValueException;
import com.example.demo.services.ClientFacade;
import com.example.demo.services.CompanyServiceImpl;
/**
 * This class controller talking with client side as company user type. 
 * @author mark
 *
 */
@RequestMapping( "/company" )
@RestController
public class CompanyController {

	@Autowired
	private CompanyServiceImpl companyFacade;
	@Autowired
	private SessionManager sessionManager;
	
	/**
	 * This method receives from client side coupon object to create new one. 	
	 * @param coupon - to be created
	 * @param token - for client authorization
	 * @return Created company for client side uses
	 * @throws CouponSystemException  if server side error occurred
	 * @throws UniqueValueException  if coupon already exists
	 * @throws MySQLException  if sql error occurred
	 * @throws CouponException  if dates validation failed
	 * @throws NotFoundException  if company not found
	 */
	@PostMapping("/createCoupon")
	public Coupon createCoupon( @RequestBody Coupon coupon, @RequestHeader("Authorization") String token ) throws  CouponSystemException,UniqueValueException, MySQLException, CouponException, NotFoundException {
		 ClientFacade facade = sessionManager.checkIfExist(token);
			companyFacade = (CompanyServiceImpl) facade;
			companyFacade.createCoupon(coupon, companyFacade.getCompanyId());
			return companyFacade.getCreatedCoupon(coupon.getTitle(), companyFacade.getCompanyId());
	}
	/**
	 * This method receives from client side coupon id to be deleted.
	 * @param id - of coupon		
	 * @param token - for client authorization
	 * @return status
	 * @throws MySQLException if sql error occurred
	 * @throws CouponSystemException if server side error occurred
	 */
	@DeleteMapping( path = "removeCoupon/{id}")
    ResponseEntity<?> removeCoupon(@PathVariable int id, @RequestHeader("Authorization") String token) throws MySQLException, CouponSystemException {
		companyFacade = (CompanyServiceImpl) sessionManager.checkIfExist(token);
		companyFacade.removeCoupon(id, companyFacade.getCompanyId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/**
	 * This method receives from client side coupon id to get 
	 * @param id - of coupon to get
	 * @param token - for client authorization
	 * @return coupon
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping( path = "getCoupon/{id}")
	Coupon getCoupon(@PathVariable int id, @RequestHeader("Authorization") String token) throws CouponSystemException{
		companyFacade = (CompanyServiceImpl) sessionManager.checkIfExist(token);
		return companyFacade.getCoupon(id, companyFacade.getCompanyId());
	}
	/**
	 * This method receives from client side coupon type to get all coupons of that type
	 * @param couponType - to get all coupons of that type
	 * @param token - for client authorization
	 * @return all coupons of type
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping(path = "getCouponByType/{couponType}")
	ArrayList<Coupon> getCouponByType(@PathVariable CouponType couponType, @RequestHeader("Authorization") String token) throws CouponSystemException{
		companyFacade = (CompanyServiceImpl) sessionManager.checkIfExist(token);
		return companyFacade.getCouponByType(couponType, companyFacade.getCompanyId());
	}
	/**
	 * This method receives from client side request to get all coupons
	 * @param token - for client authorization
	 * @return all coupons
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping(path = "getAllCoupons")
	ArrayList<Coupon> getAllCoupons( @RequestHeader("Authorization") String token) throws CouponSystemException{
		companyFacade = (CompanyServiceImpl) sessionManager.checkIfExist(token);
		return companyFacade.getAllCoupons(companyFacade.getCompanyId());
	}
	/**
	 * This method receives from client side updated coupon obj to be updated
	 * @param coupon - to be updated
	 * @param token - for client authorization
	 * @return status
	 * @throws MySQLException if sql error occurred
	 * @throws CouponSystemException if server side error occurred
	 */
	@PutMapping(path = "update")
	ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader("Authorization") String token) throws MySQLException, CouponSystemException{
		companyFacade = (CompanyServiceImpl) sessionManager.checkIfExist(token);
		companyFacade.updateCoupon(coupon, companyFacade.getCompanyId());
		 return new ResponseEntity<>( HttpStatus.OK);
	}
	/**
	 * This method receives from client side request to get company details
	 * @param token - for client authorization
	 * @return company details
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping(path = "getCompanyDetails")
	Optional<Company> getCompanyDetails(@RequestHeader("Authorization") String token) throws CouponSystemException {
		companyFacade = (CompanyServiceImpl) sessionManager.checkIfExist(token);
		return companyFacade.getCompanyDetails(companyFacade.getCompanyId());
	}
	
	
}
