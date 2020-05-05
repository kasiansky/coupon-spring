package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Company;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.UniqueValueException;

import javassist.NotFoundException;

public interface AdminService {
	
	void createCompany(Company company) throws UniqueValueException;

	void removeCompany(int companyId) throws MySQLException;

	void updateCompany(Company company) throws MySQLException, NotFoundException;
	
	Company getCompany(int id);
	
	ArrayList<Company> getAllCompanies();
	
	void createCustomer(Customer customer) throws UniqueValueException;

	void removeCustomer(int customerId)throws MySQLException;

	void updateCustomer(Customer customer) throws MySQLException, NotFoundException;

	Customer getCustomer(int id);

	ArrayList<Customer> getAllCustomer();
	
	

}
