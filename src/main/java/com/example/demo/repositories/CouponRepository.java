package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.Date;

//import org.junit.runners.Parameterized.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Coupon;
import com.example.demo.entities.CouponType;
/**
 * This interface used to implement data access layer for coupon persistence store.
 * @author mark
 *
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {


	
	@Modifying
	@Query("delete from Coupon c where c.company.id =:companyId")
	public void deleteBycompanyId(int companyId);

	 @Modifying 
	 @Query("delete from Coupon c where c.id = ?1 and c.company.id=?2")
	public int removeCoupon(int couponId, int companyId);
	 @Query("select c from Coupon c where c.type=?1 and c.company.id=?2")
	 ArrayList<Coupon> findByType(CouponType couponType, int companyId);
	
	public ArrayList<Coupon> findAllByCompanyId(int companyid);
	@Transactional
	@Modifying
	@Query("delete from Coupon c where c.endDate < CURRENT_DATE")
	void removeExpiredCoupon();
	
	
	@Query("select c from Coupon c where c.title=?1 and c.company.id=?2")
	Coupon findByCouponTitle(String title, int companyId);
	@Query("select c from Coupon c where c.id=?1 and c.company.id=?2")
	Coupon getCompanyCoupon(int id, int companyId);
	
	

	
	
	
}
