package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Company;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.UniqueValueException;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.CouponRepository;
import com.example.demo.repositories.CustomerRepository;

import javassist.NotFoundException;

/**
 * this class service is used for operations as Admin.
 * 
 * @author Mark
 *
 */
@Service
public class AdminServiceImpl implements AdminService, ClientFacade {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;

	public AdminServiceImpl() {
	}

	/**
	 * this method used for getting created company from company repository
	 * 
	 * @param name - of company to get
	 * @return created company
	 */
	public Company getCreatedCompany(String name) {
		return companyRepository.findByCompanyName(name);
	}

	/**
	 * this method used for creating the new company
	 * @param company - to be created
	 * @throws UniqueValueException if company name or email already exists
	 * @return void
	 */
	public void createCompany(Company company) throws UniqueValueException {

		if (companyRepository.findByCompanyName(company.getCompanyName()) != null) {

			throw new UniqueValueException("Company with name : " + company.getCompanyName() + " already exist");
		}
		if (companyRepository.findByEmail(company.getEmail()) != null) {
			throw new UniqueValueException("Company with email : " + company.getEmail() + " already exist");
		}
		companyRepository.save(company);

	}
	/**
	 * this method used for removing company from db
	 * @param companyId - of company to remove
	 * @return void
	 */
	@Transactional
	public void removeCompany(int companyId) {
		if (!companyRepository.getCoupons(companyId).isEmpty()) {
			couponRepository.deleteBycompanyId(companyId);
		}
		companyRepository.deleteById(companyId);

	}
	/**
	 * this method used for updating company in db
	 * @param company - to update
	 * @return void 
	 * @throws NotFoundException if company not found
	 */
	public void updateCompany(Company company) throws NotFoundException {
		Company updatedCompany = companyRepository.findByCompanyName(company.getCompanyName());
		if (updatedCompany == null) {
			throw new NotFoundException("company not found");
		}
		updatedCompany.setEmail(company.getEmail());
		updatedCompany.setPassword(company.getPassword());
		companyRepository.save(updatedCompany);
	}
	/**
	 * this method used for get company from db
	 * @param id - of company to get
	 * @return company
	 */
	public Company getCompany(int id) {

		return companyRepository.findById(id).get();

	}
	/**
	 * this method used for get all companies from db
	 * @return all companies
	 */
	public ArrayList<Company> getAllCompanies() {
		return (ArrayList<Company>) companyRepository.findAll();

	}
	/**
	 * this method used for get created customer from db
	 * @param email - search customer by customer's email
	 * @return created customer
	 */
	public Customer getCreatedCustomer(String email) {
		return customerRepository.findByEmail(email);
	}
	/**
	 * this method used for create the new customer in db
	 * @param customer - to be created
	 * @return void
	 * @throws UniqueValueException if name or email of customer is already exists
	 */
	public void createCustomer(Customer customer) {
		if (customerRepository.findByCustomerName(customer.getCustomerName()) != null) {

			throw new UniqueValueException("Customer with name : " + customer.getCustomerName() + " already exist");
		}
		if (customerRepository.findByEmail(customer.getEmail()) != null) {
			throw new UniqueValueException("Customer with email : " + customer.getEmail() + " already exist");
		}
		customerRepository.save(customer);

	}
	/**
	 * this method used for remove customer from db
	 * @param customerId - of customer to remove
	 * @return void
	 */
	public void removeCustomer(int customerId) {

		customerRepository.deleteById(customerId);

	}
	/**
	 * this method used for update customer in db
	 * @param customer - to update
	 * @return void
	 * @throws NotFoundException if customer not found
	 */
	public void updateCustomer(Customer customer) throws NotFoundException {

		Customer updatedCustomer = customerRepository.findByCustomerName(customer.getCustomerName());
		if (updatedCustomer == null) {
			throw new NotFoundException("customer not found : 	" + customer.getCustomerName());
		}
		updatedCustomer.setEmail(customer.getEmail());
		updatedCustomer.setCustomerName(customer.getCustomerName());
		customerRepository.save(updatedCustomer);

	}
	/**
	 * this method used to get customer from db
	 * @param id - of customer to get
	 * @return customer
	 */
	public Customer getCustomer(int id) {

		return customerRepository.findById(id).get();
	}
	/**
	 * this method used to get all customers from db
	 * @return all customers
	 */
	@Override
	public ArrayList<Customer> getAllCustomer() {

		return (ArrayList<Customer>) customerRepository.findAll();

	}

}
