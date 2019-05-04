package com.pentalog.BT.dao;

import com.pentalog.BT.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPANotification extends JpaRepository<Notification, Long> {
}
