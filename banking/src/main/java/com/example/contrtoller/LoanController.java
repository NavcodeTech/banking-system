package com.example.contrtoller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

//import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.model.Loan;

import com.example.service.LoanService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/users/loan")
public class LoanController {

  @Autowired
  private LoanService loanService;

  @PostMapping("/loanrequest")
  public ResponseEntity<Loan> registerUser(@RequestBody Loan loan) {

   System.out.println("Loan Request called");

    try {

      return new ResponseEntity<>(loanService.createLoan(loan),HttpStatus.CREATED);

    } catch (RuntimeException e) {

      return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);

    }

  }

}
