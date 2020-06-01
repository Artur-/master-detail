package com.example.application.data.entity;

import javax.persistence.Entity;

import com.example.application.data.AbstractEntity;

@Entity
public class Person extends AbstractEntity {

private Integer id;
public Integer getId() {
  return id;
}
public void setId(Integer id) {
  this.id = id;
}
private String profilePicture;
public String getProfilePicture() {
  return profilePicture;
}
public void setProfilePicture(String profilePicture) {
  this.profilePicture = profilePicture;
}
private String firstName;
public String getFirstName() {
  return firstName;
}
public void setFirstName(String firstName) {
  this.firstName = firstName;
}
private String lastName;
public String getLastName() {
  return lastName;
}
public void setLastName(String lastName) {
  this.lastName = lastName;
}
private String email;
public String getEmail() {
  return email;
}
public void setEmail(String email) {
  this.email = email;
}
private String occupation;
public String getOccupation() {
  return occupation;
}
public void setOccupation(String occupation) {
  this.occupation = occupation;
}



}
