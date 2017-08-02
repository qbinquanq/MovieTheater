package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class MovieInfo {
	@Id
	@SequenceGenerator(name = "INFOID_SEQ", sequenceName = "INFOID_SEQ")
	@GeneratedValue(generator = "INFOID_SEQ", strategy = GenerationType.AUTO)
	private int infoId;
	
}
