package com.example.application.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee {

    @JsonIgnore
    private Long id;

    private String idString;
    private String firstName;
    private String lastName;
    private String email;

    public Employee(Long id, String firstName, String lastName, String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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

    @Override
    public String toString() {
        return firstName + " " + lastName + "(" + email + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * When transmitting ID to the client we need a data type that is supported
     * (long is not supported in JavaScript)
     *
     * @return String representation if {@link #id}
     */
    public String getIdString() {
        return id == null ? null : id.toString();
    }

    public void setIdString(String idString) {
        // no-op
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        } else {
            return id.intValue();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || id == null) {
            return false;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }

        if (id.equals(((Employee) obj).id)) {
            return true;
        }
        return false;
    }
}
