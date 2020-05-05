package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemException;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.ClientFacade;
import com.example.demo.services.CustomerServiceImpl;
/**
 * This class controller to client side as the Customer user type.
 * @author mark
 *
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerFacade;
	@Autowired
	SessionManager sessionManager;
	
	
	/**
	 * This method receives from client side coupon id to be purchased.
	 * @param couponId - to be purchased
	 * @param token - for client authorization
	 * @return status
	 * @throws NotFoundException if customer not found 
	 * @throws CouponSystemException if server side error occurred
	 * @throws MySQLException if sql error occurred
	 */
	@PostMapping(path = "purchaseCoupon/{couponId}")
		ResponseEntity<?> purchaseCoupon(@PathVariable int couponId, @RequestHeader("Authorization") String token) throws NotFoundException, CouponSystemException, MySQLException {
		 customerFacade = (CustomerServiceImpl) sessionManager.checkIfExist(token);
		 customerFacade.purchaseCoupon(couponId, customerFacade.getCustomerId());
		return new ResponseEntity<>(HttpStatus.OK );
		 
	}
	/**
	 * This method receives from client side request to get all purchased coupons
	 * @param token - for client authorization
	 * @return all purchased coupons
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping(path = "getAllPurchasedCoupons")
	 ArrayList<Coupon>getAllPurchasedCoupons(@RequestHeader("Authorization") String token) throws CouponSystemException{
		customerFacade = (CustomerServiceImpl) sessionManager.checkIfExist(token);
		return customerFacade.getAllPurchasedCoupons(customerFacade.getCustomerId());
	}
	/**
	 * This method receives from client side type to get all purchased coupons
	 * @param type - to get all purchased coupons
	 * @param token - for client authorization
	 * @return all purchased coupons
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping(path = "getAllPurchasedCouponsByType/{type}")
	 ArrayList<Coupon> getAllPurchasedCouponsByType(@PathVariable CouponType type, @RequestHeader("Authorization") String token) throws CouponSystemException{
		customerFacade = (CustomerServiceImpl) sessionManager.checkIfExist(token);
		return customerFacade.getAllPurchasedCouponsByType(customerFacade.getCustomerId(), type);
	}
	/**
	 * This method receives from client side price to get all purchased coupons
	 * @param token - for client authorization
	 * @param price - to get all purchased coupons
	 * @return all purchased coupon
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping(path = "getAllPurchasedCouponsByPrice/{price}")
	 ArrayList<Coupon> getAllPurchasedCouponsByPrice(@RequestHeader("Authorization") String token, @PathVariable double price) throws CouponSystemException{
		customerFacade = (CustomerServiceImpl) sessionManager.checkIfExist(token);
		return customerFacade.getAllPurchasedCouponsByPrice(customerFacade.getCustomerId(), price);
	}
	/**
	 * This method receives from client side request to get customer details
	 * @param token - for client authorization
	 * @return customer details 
	 * @throws CouponSystemException if server side error occurred
	 */
	@GetMapping(path = "getCustomerDetails")
	 Optional<Customer> getCustomerDetails(@RequestHeader("Authorization") String token) throws CouponSystemException{
		customerFacade = (CustomerServiceImpl) sessionManager.checkIfExist(token);
			return customerFacade.getCustomerDetails(customerFacade.getCustomerId());
	}
	/**
	 * This method receives from client side request to get all coupons in system
	 * @return all coupons in system
	 */
	@GetMapping(path = "getAllSystemCoupons")
	ArrayList<Coupon> getAllSystemCoupons(){
		return customerFacade.getAllSystemCoupons();
	}
	

}
