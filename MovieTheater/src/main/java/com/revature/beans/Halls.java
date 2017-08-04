package com.revature.beans;

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
public class Halls {
	@Id
	@SequenceGenerator(name = "HALLID_SEQ", sequenceName = "hallId_pk")
	@GeneratedValue(generator = "HALLID_SEQ", strategy = GenerationType.AUTO)
	private int hallId;
	private int hCapacity;
	private double hCost;

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public int gethCapacity() {
		return hCapacity;
	}

	public void sethCapacity(int hCapacity) {
		this.hCapacity = hCapacity;
	}

	public double gethCost() {
		return hCost;
	}

	public void sethCost(double hCost) {
		this.hCost = hCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hCapacity;
		long temp;
		temp = Double.doubleToLongBits(hCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + hallId;
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
		Halls other = (Halls) obj;
		if (hCapacity != other.hCapacity)
			return false;
		if (Double.doubleToLongBits(hCost) != Double.doubleToLongBits(other.hCost))
			return false;
		if (hallId != other.hallId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Halls [hallId=" + hallId + ", hCapacity=" + hCapacity + ", hCost=" + hCost + "]";
	}

	public Halls(int hallId, int hCapacity, double hCost) {
		super();
		this.hallId = hallId;
		this.hCapacity = hCapacity;
		this.hCost = hCost;
	}

	public Halls() {
		super();
	}

}
