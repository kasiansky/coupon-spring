package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.exceptions.CouponSystemException;
import com.example.demo.services.ClientFacade;
/**
 * This class used for managing sessions and tokens.
 * 
 * @author mark
 *
 */
@Component
public class SessionManager {
	@Resource
	private HashMap<String, ClientSession> tokensMap;
	/**
	 * This method creating the new client session, generates the new token 
	 * and saves client session and token in tokensMap, where token is the key and session is the value. 
	 * @param clientFacade - admin, company or customer
	 * @return token
	 */
	public String add(ClientFacade clientFacade) {

		String token = UUID.randomUUID().toString();
		ClientSession clientSession = new ClientSession();
		clientSession.setLastAccessedTime(System.currentTimeMillis());
		clientSession.setClientFacade(clientFacade);
		tokensMap.put(token, clientSession);
		return token;
	}
	/**
	 * This method checks if user is authorised.
	 * @param token - as a key in tokensMap to get facade from session
	 * @return facade - if token exist and session time is valid
	 * @throws CouponSystemException if session time out
	 */
	public ClientFacade checkIfExist(String token) throws CouponSystemException {	
		ClientSession session = tokensMap.get(token);
		long currentTime = System.currentTimeMillis();
		long l = 1800000;
		if(currentTime - session.getLastAccessedTime() < l) {
			session.setLastAccessedTime(currentTime);
			return session.getClientFacade();
		}else {
			tokensMap.remove(token);
			throw new CouponSystemException("Session time out");
		}
	}
}