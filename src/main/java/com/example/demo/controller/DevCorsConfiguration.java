package com.example.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * This class configuration enables CORS requests from any origin to the api/ endpoint in the application.
 * @author mark
 *
 */
@Configuration
@Profile("development")
public class DevCorsConfiguration implements WebMvcConfigurer {
		/**
		 * This method used for allow connection and authorization between client and server
		 */
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
	        .exposedHeaders("Authorization");
	    }
}
