package org.starj.boot.jpa.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.starj.boot.jpa.demo.service.UserService;
import org.starj.boot.jpa.demo.service.UserServiceTests;

@SpringBootTest
class StarjBootJpaDemoApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		new UserServiceTests().test(userService);
	}

}
