package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class WalkIn {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "infoId")
	private MovieInfo movieInfo;
	private int walkAmount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private Accounts account;

	public MovieInfo getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(MovieInfo movieInfo) {
		this.movieInfo = movieInfo;
	}

	public int getWalkAmount() {
		return walkAmount;
	}

	public void setWalkAmount(int walkAmount) {
		this.walkAmount = walkAmount;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((movieInfo == null) ? 0 : movieInfo.hashCode());
		result = prime * result + walkAmount;
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
		WalkIn other = (WalkIn) obj;
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
		if (walkAmount != other.walkAmount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WalkIn [movieInfo=" + movieInfo + ", walkAmount=" + walkAmount + ", account=" + account + "]";
	}

	public WalkIn(MovieInfo movieInfo, int walkAmount, Accounts account) {
		super();
		this.movieInfo = movieInfo;
		this.walkAmount = walkAmount;
		this.account = account;
	}

	public WalkIn() {
		super();
	}
}
