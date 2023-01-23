package com.dh.userservice.model;

public class User {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private SubscriptionDTO subscription;

    public User(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public User(String id, String firstName, String lastName, String email,SubscriptionDTO subscription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subscription = subscription;
    }



    public SubscriptionDTO getSubscriptionDTO() {
        return subscription;
    }

    public void setSubscription(SubscriptionDTO subscription) {
        this.subscription = subscription;
    }

    public User() {
    }

    public User(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
