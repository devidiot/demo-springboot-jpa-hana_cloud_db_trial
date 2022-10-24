package org.starj.boot.jpa.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.starj.boot.jpa.demo.model.User;

public class UserServiceTests {

    public void test(UserService userService) {
        // User user = new User("uid", "tester", "1234", "Tester", "LastName",
        // "20201231", true);
        userService.save(User.builder()
                .username("tester")
                .firstName("firstName")
                .lastName("lastName")
                .build());

        List<User> users = userService.findAll();
        User user = users.get(0);

        System.out.println(String.format("############### tested : uid=%s ", user.getUid()));

        assertTrue(user != null);
    }
}
