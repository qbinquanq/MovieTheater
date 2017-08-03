package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Transactions {
	@Id
	@SequenceGenerator(name = "TRANSID_SEQ", sequenceName = "TRANSID_SEQ")
	@GeneratedValue(generator = "TRANSID_SEQ", strategy = GenerationType.AUTO)
	private int transId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private Accounts account;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "infoId")
	private MovieInfo movieInfo;
	private int requestRet;

	public Transactions(int transId, Accounts account, MovieInfo movieInfo, int requestRet) {
		super();
		this.transId = transId;
		this.account = account;
		this.movieInfo = movieInfo;
		this.requestRet = requestRet;
	}

	public int getRequestRet() {
		return requestRet;
	}

	public void setRequestRet(int requestRet) {
		this.requestRet = requestRet;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public MovieInfo getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((movieInfo == null) ? 0 : movieInfo.hashCode());
		result = prime * result + requestRet;
		result = prime * result + transId;
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
		Transactions other = (Transactions) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (movieInfo == null) {
			if (other.movieInfo != null)
				return false;
		} else if (!movieInfo.equals(other.movieInfo))
			return false;
		if (requestRet != other.requestRet)
			return false;
		if (transId != other.transId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transactions [transId=" + transId + ", account=" + account + ", movieInfo=" + movieInfo
				+ ", requestRet=" + requestRet + "]";
	}

	public Transactions() {
		super();
	}
}
