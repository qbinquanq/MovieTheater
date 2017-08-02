package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Accounts")
public class Accounts {
	@Id
    @Column
   private int userId;
    private String uname;
   private String pword;
   private String email;
   private String fname;
   private String lname;
public Accounts() {
	super();
	// TODO Auto-generated constructor stub
}
public Accounts(int userId, String uname, String pword, String email, String fname, String lname) {
	super();
	this.userId = userId;
	this.uname = uname;
	this.pword = pword;
	this.email = email;
	this.fname = fname;
	this.lname = lname;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getPword() {
	return pword;
}
public void setPword(String pword) {
	this.pword = pword;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
@Override
public String toString() {
	return "Accounts [userId=" + userId + ", uname=" + uname + ", pword=" + pword + ", email=" + email + ", fname="
			+ fname + ", lname=" + lname + "]";
}
   
   
   
}
