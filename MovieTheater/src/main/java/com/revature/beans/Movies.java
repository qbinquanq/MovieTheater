package com.revature.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "MOVIES")
public class Movies{
	@Id
	@SequenceGenerator(name = "MOVIEID_SEQ", sequenceName = "movieId_pk")
	@GeneratedValue(generator = "MOVIEID_SEQ", strategy = GenerationType.AUTO)
	private int movieId;
	private String mTitle;
	private Date releaseDate;
	@Column(name="MGENRE")
	private String genre;
	private int mLength;

	public Movies() {
		super();
	}

	public Movies(int movieId, String mTitle, Date releaseDate, String genre, int mLength) {
		super();
		this.movieId = movieId;
		this.mTitle = mTitle;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.mLength = mLength;
	}

	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", mTitle=" + mTitle + ", releaseDate=" + releaseDate + ", genre=" + genre
				+ ", mLength=" + mLength + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + mLength;
		result = prime * result + ((mTitle == null) ? 0 : mTitle.hashCode());
		result = prime * result + movieId;
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
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
		Movies other = (Movies) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (mLength != other.mLength)
			return false;
		if (mTitle == null) {
			if (other.mTitle != null)
				return false;
		} else if (!mTitle.equals(other.mTitle))
			return false;
		if (movieId != other.movieId)
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		return true;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getmLength() {
		return mLength;
	}

	public void setmLength(int mLength) {
		this.mLength = mLength;
	}
}
