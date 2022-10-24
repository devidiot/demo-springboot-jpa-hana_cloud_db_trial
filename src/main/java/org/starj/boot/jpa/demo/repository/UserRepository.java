package org.starj.boot.jpa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.starj.boot.jpa.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
