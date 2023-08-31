package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Loan {

 private String fullName;
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int loanId;
 private long phone;
 private String residentialAddress;
 private String loanType;
 private int loanAmount;
 private String loanPurpose;
 private String occupation;
 private int anualIncome;
// private String residenceImage;
// private String panImage;
// private String incomeImage;
// private String signImage;
 private String declaration;

}


