package com.example.clientqueue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean withPriority;
    private boolean served;

    public Client() {

    }

    public Client(String name, boolean withPriority) {
        this.name = name;
        this.withPriority = withPriority;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isWithPriority() {
        return withPriority;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }
}
