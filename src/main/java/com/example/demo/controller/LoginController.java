package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ClientType;
import com.example.demo.exceptions.CouponSystemException;
import com.example.demo.model.Token;
import com.example.demo.model.UserModel;
import com.example.demo.services.ClientFacade;
import com.example.demo.services.LoginManager;

/**
 * This class used for talking with client side to login.
 * 
 * @author mark
 *
 */
@RestController
public class LoginController {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private SessionManager sessionManager;

	/**
	 * This method checks authentication of user.
	 * 
	 * @param userModel - name, password and type to authenticate
	 * @return token and status.OK if user exist and saved successfully to tokensMap of session manager
	 * @throws CouponSystemException if authentication failed
	 */
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody UserModel userModel) {
		ClientFacade clientFacade = null;
		Token token = new Token();

		try {
			clientFacade = loginManager.login(userModel.getUserName(), userModel.getPassword(),
					ClientType.valueOf(userModel.getType()));
			String tokenString = sessionManager.add(clientFacade);
			token.setToken(tokenString);
			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

		}
	}

}
