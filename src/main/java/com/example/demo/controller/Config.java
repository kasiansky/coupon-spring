package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.scheduling.config.Task;

import com.example.demo.services.CompanyServiceImpl;
import com.example.demo.tasks.DailyCouponExpirationTask;

/**
 * This class declares one or more @Bean methods and may be processed by 
 * the Spring container to generate bean definitions and service requests for those beans at runtime.
 * @author mark
 *
 */
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
@Configuration
public class Config {
	@Bean
	public Map<String, ClientSession> tokensMap(){
		return new HashMap<String, ClientSession>();
	}
	@Autowired
	DailyCouponExpirationTask task;
	
	
	
	
}
