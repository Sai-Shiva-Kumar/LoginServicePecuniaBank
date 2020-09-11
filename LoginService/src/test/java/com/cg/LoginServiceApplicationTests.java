package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.entity.User;
import com.cg.exceptions.LoginException;
import com.cg.service.LoginServiceImp;

@SpringBootTest
class LoginServiceApplicationTests {
    @Autowired
    private LoginServiceImp service;
    @Test
    public void ValidDetails() throws Exception{
    	User logResult=new User();
    	User logDetails=new User();
    	logResult.setUserID("sais11604@gmail.com");
    	logResult.setPassword("saishiva2");
    	logResult.setRole("admin");
    	logResult.setUserName("saishiva");
    	logDetails= service.doLogin("sais11604@gmail.com", "saishiva2");
    	assertEquals(logResult.toString(),logDetails.toString());
    	    }
    
	    @Test
	public void InValidDetails() throws Exception
	{			
		assertThrows(LoginException.class,()->service.doLogin("sais11604@gmail.com","saishiva"));
	}
}
