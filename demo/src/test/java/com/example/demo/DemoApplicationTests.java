package com.example.demo;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.jinx.Login.LoginEntity;
import com.example.demo.jinx.Login.LoginMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	private static final Log logger = LogFactory.getLog(DemoApplicationTests.class);

	@Autowired
    private LoginMapper loginMapper;
    @Test
    public void findByCategoryType() throws Exception {
    	LoginEntity entity = new LoginEntity();
    	entity.setUserName("xiaohua");
    	logger.info("I am coming");
    	LoginEntity entity1=loginMapper.getUser(entity);
        System.out.println(entity1.getCount());
    }
	
	@Test
	public void contextLoads() {
	}

}
