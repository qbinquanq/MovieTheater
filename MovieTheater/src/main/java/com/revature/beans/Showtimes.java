package com.revature.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Showtimes {
	@Id
	@SequenceGenerator(name = "SHOWTIMEID_SEQ", sequenceName = "showId_pk")
	@GeneratedValue(generator = "SHOWTIMEID_SEQ", strategy = GenerationType.AUTO)
	private int showId;
	private Date showtime;

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
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

	public Showtimes(int showId, Date showtime) {
		super();
		this.showId = showId;
		this.showtime = showtime;
	}

	public Showtimes() {
		super();
	}
}
