package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AccId;
	private String name;
	private String dob;
	private String accType;
	private String address;
	
	private String email;
	private long phone;
	private String fatherName;
	private String motherName;
	private long panCard;
	private long aadhaarCard;
	private String nominee;
	private String nomineeRelation;
	private String occupation;
	private long annualIncome;
	
	
//	public String aadhaarImage ; 
//	
//	private String residenceImage;
//	
//	private String panImage;
//	
//	private String profile;
	@JsonIgnore

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	
}
