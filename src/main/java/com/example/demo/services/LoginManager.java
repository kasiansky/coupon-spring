package com.example.demo.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Company;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponSystemException;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.CustomerRepository;
/**
 * This class used to manage user login
 * @author mark
 *
 */
@Component
public class LoginManager {

	@Autowired
	private AdminServiceImpl adminFacade;
	@Autowired
	private CompanyServiceImpl companyFacade;
	@Autowired
	private CustomerServiceImpl customerFacade;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	/**
	 * this method used to validate user, check in db and return facade according to parameters.
	 * @param userName 
	 * @param password
	 * @param clientType
	 * @return facade
	 * @throws CouponSystemException if credentials are invalid
	 */
	public ClientFacade login(String userName, String password, ClientType clientType) throws CouponSystemException {

		switch (clientType) {
		case ADMIN:
			if (userName.equals("admin") && password.equals("1234"))
				
				return adminFacade;
			
		case COMPANY:
			Company company = companyRepository.findBycompanyNameAndPassword(userName, password);
			if (company != null) {
				companyFacade.setCompanyId(company.getId());
				
				return companyFacade;
			}else throw new CouponSystemException("Credentials are invalid!"); 
			
			

		case CUSTOMER:
			Customer customer = customerRepository.findBycustomerNameAndPassword(userName, password);
			if(customer!=null) {
				customerFacade.setCustomerId(customer.getId());
				return customerFacade;
			}else throw new CouponSystemException("Credentials are invalid!"); 
		
		default:
			throw new CouponSystemException("Credentials are invalid!");
		}
		
	}
}
