package com.cg.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ILoginDao;
import com.cg.entity.User;
import com.cg.exceptions.LoginException;
import com.cg.util.CgConstants;

@Service
public class LoginServiceImp implements LoginService{

	@Autowired
	private ILoginDao dao;
	
	
	Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);
	
	@Override
	public User doLogin(String userId, String password)throws LoginException {
		User user = null;
		logger.debug(CgConstants.LOGIN_REQUEST_UNDER_PROCESSING);
		Optional<User> optUser = dao.findById(userId);
		if (optUser.isPresent()) {
			user = optUser.get();
			if(!user.getPassword().contentEquals(/*decryptString*/(password)))
				throw new LoginException(CgConstants.CHECK_YOUR_PASSWORD);
			logger.info(CgConstants.USER_AUTHENTICATED + userId);
			return user;
		}
		
		throw new LoginException(CgConstants.CHECK_YOUR_CREDENTIALS);
	}

	@Override
	public String encryptUser(User user) {
		return encryptString(user.getUserID())+"-" +encryptString(user.getUserName())+"-"
		      +encryptString(user.getRole());
	}
	
	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch ;
		for (int idx=0; idx < arr.length; ++idx) {
			ch = arr[idx]+3;
			sb.append((char)ch);
		}
		return sb.toString();
	}

	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch ;
		for (int idx=0; idx < arr.length; ++idx) {
			ch = arr[idx]-3;
			sb.append((char)ch);
		}
		return sb.toString();
	}

	
}
