package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Accounts {
	@Id
	@SequenceGenerator(name = "USERID_SEQ", sequenceName = "USERID_SEQ")
	@GeneratedValue(generator = "USERID_SEQ", strategy = GenerationType.AUTO)
	private int userId;
	private String uname;
	private String pword;
	private String email;
	private String fname;
	private String lname;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
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
