package com.pentalog.BT.dao;

import com.pentalog.BT.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAPerson extends JpaRepository<Person,Long> {
}
