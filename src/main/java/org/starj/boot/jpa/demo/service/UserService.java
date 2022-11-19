package org.starj.boot.jpa.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.starj.boot.jpa.demo.NoDataFoundException;
import org.starj.boot.jpa.demo.model.User;
import org.starj.boot.jpa.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        // List<User> users = new ArrayList<>();
        return repository.findAll();
        // repository.findAll().forEach(e -> users.add(e));
        // return users;
    }

    public User findById(String uid) {
        return repository.findById(uid).orElseThrow(NoDataFoundException::new);
    }

    public void deleteById(String uid) {
        repository.deleteById(uid);
    }

    public User save(User user) {
        repository.save(user);
        return user;
    }

    public void applyById(String uid, User user) throws IllegalArgumentException, IllegalAccessException {
        User storedUser = this.findById(uid);
        storedUser.apply(user);
        repository.save(storedUser);
    }

    public void updateById(String uid, User user) throws IllegalArgumentException, IllegalAccessException {
        User storedUser = this.findById(uid);
        storedUser.update(user);
        repository.save(storedUser);
    }
}
