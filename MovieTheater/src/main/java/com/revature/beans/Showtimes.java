package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Showtimes {
	@Id
	private int showId;
	private String showtime;

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + showId;
		result = prime * result + ((showtime == null) ? 0 : showtime.hashCode());
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
		Showtimes other = (Showtimes) obj;
		if (showId != other.showId)
			return false;
		if (showtime == null) {
			if (other.showtime != null)
				return false;
		} else if (!showtime.equals(other.showtime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Showtimes [showId=" + showId + ", showtime=" + showtime + "]";
	}

	public Showtimes(int showId, String showtime) {
		super();
		this.showId = showId;
		this.showtime = showtime;
	}

	public Showtimes() {
		super();
	}

}
