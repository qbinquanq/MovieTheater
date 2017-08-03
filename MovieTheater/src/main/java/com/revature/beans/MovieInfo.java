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

@Entity
@Table
public class MovieInfo {
	@Id
	@SequenceGenerator(name = "INFOID_SEQ", sequenceName = "INFOID_SEQ")
	@GeneratedValue(generator = "INFOID_SEQ", strategy = GenerationType.AUTO)
	private int infoId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movieId")
	private Movies movie;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hallId")
	private Halls hall;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showId")
	private Showtimes showtime;
	private int onlineTot;
	private int walkTot;

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public Halls getHall() {
		return hall;
	}

	public void setHall(Halls hall) {
		this.hall = hall;
	}

	public Showtimes getShowtime() {
		return showtime;
	}

	public void setShowtime(Showtimes showtime) {
		this.showtime = showtime;
	}

	public int getOnlineTot() {
		return onlineTot;
	}

	public void setOnlineTot(int onlineTot) {
		this.onlineTot = onlineTot;
	}

	public int getWalkTot() {
		return walkTot;
	}

	public void setWalkTot(int walkTot) {
		this.walkTot = walkTot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hall == null) ? 0 : hall.hashCode());
		result = prime * result + infoId;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + onlineTot;
		result = prime * result + ((showtime == null) ? 0 : showtime.hashCode());
		result = prime * result + walkTot;
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
		MovieInfo other = (MovieInfo) obj;
		if (hall == null) {
			if (other.hall != null)
				return false;
		} else if (!hall.equals(other.hall))
			return false;
		if (infoId != other.infoId)
			return false;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (onlineTot != other.onlineTot)
			return false;
		if (showtime == null) {
			if (other.showtime != null)
				return false;
		} else if (!showtime.equals(other.showtime))
			return false;
		if (walkTot != other.walkTot)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MovieInfo [infoId=" + infoId + ", movie=" + movie + ", hall=" + hall + ", showtime=" + showtime
				+ ", onlineTot=" + onlineTot + ", walkTot=" + walkTot + "]";
	}

	public MovieInfo(int infoId, Movies movie, Halls hall, Showtimes showtime, int onlineTot, int walkTot) {
		super();
		this.infoId = infoId;
		this.movie = movie;
		this.hall = hall;
		this.showtime = showtime;
		this.onlineTot = onlineTot;
		this.walkTot = walkTot;
	}

	public MovieInfo() {
		super();
	}

}
