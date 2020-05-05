package com.example.demo.controller;

import com.example.demo.services.ClientFacade;
/**
 * This class used for creating user session in tokensMap<token(key), clientSession(value)> of SessionManager e.g {@link SessionManager#add(ClientFacade)} 
 * when client trying to login e.g. {@link LoginController#login(com.example.demo.model.UserModel)}}.
 * The class have 2 attributes: 1.client facade - admin, company or customer.
 * 								2.lastAccessedTime - user's last time activity.
 * @author mark
 * @see SessionManager#add(ClientFacade)
 * @see SessionManager#checkIfExist(String)
 * @see LoginController#login(com.example.demo.model.UserModel)
 */
public class ClientSession {
	
	private ClientFacade clientFacade;
	private long lastAccessedTime;
	
	public ClientFacade getClientFacade() {
		return clientFacade;
	}
	public void setClientFacade(ClientFacade clientFacade) {
		this.clientFacade = clientFacade;
	}
	public long getLastAccessedTime() {
		return lastAccessedTime;
	}
	public void setLastAccessedTime(long lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	
}
