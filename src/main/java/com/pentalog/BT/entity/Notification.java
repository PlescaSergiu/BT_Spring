package com.pentalog.BT.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "notifications")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "details",length = 50)
    private String details;

    @Column(name = "created_not_time")
    private Timestamp cratedTime;

    @Column(name = "sent_time")
    private Timestamp sentTime;

    public Notification() {
    }

    public Notification(User user, String details, Timestamp cratedTime, Timestamp sentTime) {
        this.user = user;
        this.details = details;
        this.cratedTime = cratedTime;
        this.sentTime = sentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getCratedTime() {
        return cratedTime;
    }

    public void setCratedTime(Timestamp cratedTime) {
        this.cratedTime = cratedTime;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }
}
