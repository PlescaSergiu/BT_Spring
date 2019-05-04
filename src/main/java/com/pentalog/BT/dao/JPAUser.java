package com.pentalog.BT.dao;


import com.pentalog.BT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUser extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
