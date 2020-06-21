package com.example.demo.controller;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Company;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemException;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.UniqueValueException;
import com.example.demo.model.Token;
import com.example.demo.services.AdminServiceImpl;
import com.example.demo.services.ClientFacade;
import com.example.demo.services.LoginManager;

import javassist.NotFoundException;
/**
 * This class controller talking with client side as admin user type. 
 * 
 * @author mark
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminServiceImpl admin;

	@Autowired
	private SessionManager sessionManager;
	
	
	/**
	 * This method receives company object from client side and creating new company. 
	 * 
	 * @param company - the company to be created
	 * @param token - for authorization
	 * @return created company for client side uses
	 * @throws UniqueValueException - if company's email already exist
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@PostMapping(path = "/createCompany")
	public  Company createCompany (@RequestBody Company company, @RequestHeader("Authorization") String token) throws UniqueValueException, CouponSystemException {
		
			 admin = (AdminServiceImpl)sessionManager.checkIfExist(token);
			 admin.createCompany(company);
			 return  admin.getCreatedCompany(company.getCompanyName());
			
	}
	/**
	 * This method receives from client side company id and deletes company. 
	 * 
	 * @param id - company to be deleted
	 * @param token - for authorization
	 * @return status 
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@DeleteMapping(path = "removeCompany/{id}")
	public ResponseEntity<?> remove(@PathVariable int id, @RequestHeader ("Authorization") String token) throws CouponSystemException{
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		admin.removeCompany(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/**
	 * This method receives from client side the updated company to be updated. 
	 * 
	 * @param company - to be updated
	 * @param token - for authorization
	 * @return status 
	 * @throws CouponSystemException - if server side error occurred 
	 * @throws NotFoundException - if company not found
	 */
	@PutMapping(path = "updateCompany")
	ResponseEntity<?> updateCompany(@RequestBody Company company, @RequestHeader("Authorization") String token) throws CouponSystemException, NotFoundException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		admin.updateCompany(company);
		return new ResponseEntity<>( HttpStatus.OK);	
	}
	/**
	 * This method receives from client side id to get company.
	 * 
	 * @param id - of company to get
	 * @param token - for authorization
	 * @return company
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@GetMapping(path = "/getCompany/{id}")
	Company getCompany(@PathVariable int id, @RequestHeader ("Authorization") String token) throws CouponSystemException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		return admin.getCompany(id);
	}
	/**
	 * This method receives from client side the request to get all companies.
	 * 
	 * @param token - for authorization
	 * @return all companies
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@GetMapping(path = "getAllCompanies")
	Collection<Company> getAllCompanies(@RequestHeader("Authorization") String token) throws CouponSystemException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		return admin.getAllCompanies();
	}
	/**
	 * This method receives from client side customer to created.
	 * 
	 * @param customer - to be created
	 * @param token - for authorization
	 * @return created customer for client side uses
	 * @throws UniqueValueException - if customer's email already exist
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@PostMapping(path = "createCustomer")
	public Customer createCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String token) throws UniqueValueException, CouponSystemException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		admin.createCustomer(customer);
		return admin.getCreatedCustomer(customer.getEmail());
	}
	/**
	 * This method receives from client side if of customer to be removed
	 * 
	 * @param id - of customer to be removed
	 * @param token - for authorization
	 * @return status
	 * @throws MySQLException - if sql error occurred
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@DeleteMapping(path = "removeCustomer/{id}")
	ResponseEntity<?> removeCustomer(@PathVariable int id, @RequestHeader("Authorization") String token) throws MySQLException, CouponSystemException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		admin.removeCustomer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/**
	 * This method receives from client side updated customer to be updated.
	 * 
	 * @param customer - to be updated
	 * @param token - for authorization
	 * @return status
	 * @throws MySQLException - if sql error occurred
	 * @throws CouponSystemException - if server side error occurred 
	 * @throws NotFoundException - if customer not found
	 */
	@PutMapping(path = "updateCustomer")
	ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String token) throws MySQLException, CouponSystemException, NotFoundException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		admin.updateCustomer(customer);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	/**
	 * This method receives from client side request to get customer by id.
	 * 
	 * @param id - of customer to get
	 * @param token - for authorization
	 * @return customer
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@GetMapping(path = "getCustomer/{id}")
	Customer getCustomer(@RequestBody int id, @RequestHeader("Authorization") String token) throws CouponSystemException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		return admin.getCustomer(id);
	}
	/**
	 * This method receives from client side request to get all customers.
	 * 
	 * @param token - for authorization
	 * @return all customers
	 * @throws CouponSystemException - if server side error occurred 
	 */
	@GetMapping(path = "getAllCustomers")
	ArrayList<Customer> getAllCustomer( @RequestHeader("Authorization") String token) throws CouponSystemException {
		admin = (AdminServiceImpl) sessionManager.checkIfExist(token);
		return admin.getAllCustomer();
	}

}
