package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
/**
 * This interface used to implement data access layer for company persistence store.
 * @author mark
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	Company findBycompanyNameAndPassword(String companyName, String password);
	
	
	@Query("select coupons from Company c where c.id =:companyId  ")
	public Collection<Coupon> getCoupons(@Param("companyId") int companyId);

	Company findByCompanyName(String companyName);
	
	Company findByEmail(String email);
	

}
