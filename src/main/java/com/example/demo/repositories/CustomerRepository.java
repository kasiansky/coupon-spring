package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
import com.example.demo.entities.Customer;
/**
 * This interface used to implement data access layer for customer persistence store.
 * @author mark
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional
	 @Modifying
	    @Query("UPDATE Customer c SET c.customerName = :customerName, c.password = :password"
	    		+ " WHERE c.id =:customerId")
	     int updateCustomer(@Param("customerId") int customerId, @Param("customerName") String customerName,
	    		@Param("password") String password);

//	ArrayList<Customer> findAllByNameAndPassword(String name, String password);

	Customer findBycustomerNameAndPassword(String customerName, String password);
	@Transactional
	@Query("select c from Coupon c JOIN c.customers cust where cust.id =:customerId AND c.id=:couponId")
	public Coupon getCustomerCoupon(@Param("couponId") int couponId, @Param("customerId")int customerId);

	@Transactional
	@Query("select c.coupons from Customer c where c.id= ?1")
	ArrayList<Coupon> getAllPurchasedCoupons(int customerId);

	@Transactional
	@Query("select c from Coupon c Join c.customers customer where c.type =:type and customer.id =:customerId")
	ArrayList<Coupon> getAllPurchasedCouponsByType(@Param("customerId")int customerId, @Param("type")CouponType type);

	@Transactional
	@Query("select c from Coupon c Join c.customers customer where customer.id =:customerId and c.price <=:price")
	ArrayList<Coupon> getAllPurchasedCouponsByPrice(@Param("customerId")int customerId, @Param("price")double price);

	Customer findByCustomerName(String customerName);

	Customer findByEmail(String email);

	
	
}
