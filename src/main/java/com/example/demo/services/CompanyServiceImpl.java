package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ClientType;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponException;
import com.example.demo.exceptions.MySQLException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UniqueValueException;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.CouponRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.tasks.DailyCouponExpirationTask;
/**
 * This class service used for operations as company.
 * @author mark
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService, ClientFacade {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CouponRepository couponRepository;

	public CompanyServiceImpl() {

	}

	private int companyId;
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * this method used to create the new coupon in db
	 * @param coupon - to create
	 * @param companyId - that creates the new coupon
	 * @return void
	 * @throws UniqueValueException if coupon title already exists
	 * @throws MySQLException if sql error occurred
	 * @throws CouponException if dates are invalid
	 * @throws NotFoundException if company not found
	 */
	@Transactional
	@Override
	public void createCoupon(Coupon coupon, int companyId) throws UniqueValueException, MySQLException, CouponException, NotFoundException {
		Company company = companyRepository.findById(companyId).get();
		if (company == null) {
			throw new NotFoundException("No company found with this id: " + companyId);
		}
		coupon.setCompany(company);
		Coupon c = couponRepository.findByCouponTitle(coupon.getTitle(), companyId);
		if (c != null) {
			throw new UniqueValueException(coupon.getTitle() + " title is already exist ");
		}
		if(coupon.getEndDate().before(coupon.getStartDate())) {
			throw new CouponException("start date must be before end date!");
		} 
		Date now = new Date(System.currentTimeMillis());
	
		if(coupon.getStartDate().before(now)) {
			throw new CouponException("start date must be after of current");
		}

		couponRepository.save(coupon);
	}
	/**
	 * this method used to remove coupon from db
	 * @param couponId 
	 * @param companyId - company that coupon belongs to
	 * @return void
	 * @throws MySQLException if sql error occurred
	 */
	@Transactional
	@Override
	public void removeCoupon(int couponId, int companyId) throws MySQLException {
		int rowsAffected = couponRepository.removeCoupon(couponId, companyId);
		if (rowsAffected == 0) {
			throw new MySQLException("The coupon that you are triyng to delete does not belong to you");

		}
		
	}
	/**
	 * this method used to update coupon in db
	 * @param coupon - to update
	 * @param companyId - company that coupon belongs to
	 * @return void
	 * @throws MySQLException if sql error occurred
	 */
	@Override
	public void updateCoupon(Coupon coupon, int companyId) throws MySQLException {

		Coupon c = getCoupon(coupon.getId(), companyId);

		if (c != null) {
			c.setEndDate(coupon.getEndDate());
			c.setPrice(coupon.getPrice());
			couponRepository.save(c);
		}

	}
	/**
	 * this method used to get coupon by type
	 * @param couponType - get all coupons by this type
	 * @param companyId - company that coupons belongs to
	 * @return coupons
	 */
	@Override
	public ArrayList<Coupon> getCouponByType(CouponType couponType, int companyId) {
		ArrayList<Coupon> coupons = couponRepository.findByType(couponType, companyId);
		
		return coupons;
	}
	/**
	 * this method used to get all coupons of certain company from db
	 * @param companyId - company that coupons belongs to
	 * @return all coupons
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons(int companyid) {

		return couponRepository.findAllByCompanyId(companyid);
	}
	/**
	 * this method used to get coupon from db
	 * @param id - coupon to get
	 * @param companyId - coupon belongs to
	 * @return coupon
	 */
	@Override
	public Coupon getCoupon(int id, int companyId) {

		return couponRepository.getCompanyCoupon(id, companyId);
	}
	/**
	 * this method used to remove expired coupon
	 * @see DailyCouponExpirationTask
	 */
	@Override
	public void removeExpiredCoupon() {
		couponRepository.removeExpiredCoupon();

	}
	/**
	 * this method used to get company details from db
	 * @param companyId - to get company
	 * @return company
	 */
	@Override
	public Optional<Company> getCompanyDetails(int companyId) {
		return companyRepository.findById(companyId);
		
	}
	/**
	 * this method used to get created coupon 
	 * @param title 
	 * @param id 
	 * @return coupon
	 */
	public Coupon getCreatedCoupon(String title, int id) {
		return couponRepository.findByCouponTitle(title, id);
		
	}

}
