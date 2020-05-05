package com.example.demo.tasks;

import java.sql.Date;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Coupon;
import com.example.demo.services.CompanyServiceImpl;

/**
 * This class used for creating coupon thread task that deletes expired coupons.
 * 
 * @author mark
 *
 */
@EnableScheduling
@Component
public class DailyCouponExpirationTask implements Runnable {
	@Autowired
	private CompanyServiceImpl companyServiceImpl;

	public DailyCouponExpirationTask() {

	}
/**
 * this method running task
 * @return void
 * @see CompanyServiceImpl#removeExpiredCoupon()
 */
//	starts every 1 minute
//	@Scheduled(cron = "0 0/1 * 1/1 * ? ") 
//	starts every 24 hours
	@Scheduled(cron = "0 0 12 1/1 * ?")
	public void run() {

		System.out.println("task start");

		companyServiceImpl.removeExpiredCoupon();

		System.out.println("task end");

	}

}
