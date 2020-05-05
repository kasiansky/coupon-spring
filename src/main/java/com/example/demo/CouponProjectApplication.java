package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.CompanyServiceImpl;

@SpringBootApplication
@RestController
public class CouponProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponProjectApplication.class, args);
	}
	
}
