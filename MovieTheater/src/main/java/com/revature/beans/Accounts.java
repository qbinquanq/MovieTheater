package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "Accounts")
public class Accounts {
	@Id
	@SequenceGenerator(name = "ACCOUNTID_SEQ", sequenceName = "userId_pk")
	@GeneratedValue(generator = "ACCOUNTID_SEQ", strategy = GenerationType.AUTO)
	private int userId;
	@JsonIgnore
	private String uname;
	@JsonIgnore
	private String pword;
	private String email;
	private String fname;
	private String lname;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "Employee", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "reportsTo"))
	private Accounts manager;

	public Accounts(int userId, String uname, String pword, String email, String fname, String lname,
			Accounts manager) {
		super();
		this.userId = userId;
		this.uname = uname;
		this.pword = pword;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.manager = manager;
	}

	public Accounts getManager() {
		return manager;
	}

	public void setManager(Accounts manager) {
		this.manager = manager;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((pword == null) ? 0 : pword.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accounts other = (Accounts) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (pword == null) {
			if (other.pword != null)
				return false;
		} else if (!pword.equals(other.pword))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	public Accounts() {
		super();
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
				+ fname + ", lname=" + lname + ", manager=" + manager + "]";
	}

}
