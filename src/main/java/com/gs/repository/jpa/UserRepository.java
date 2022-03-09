package com.gs.repository.jpa;

import com.gs.model.entity.jpa.db1.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUserName(String username);

    User findByMobile(String mobile);

    User findByEmail(String email);

    User findByUserNameAndPassword(String username, String password);
}
