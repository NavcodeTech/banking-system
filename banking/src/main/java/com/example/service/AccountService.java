package com.example.service;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountRepository;
import com.example.dao.UserRepository;
import com.example.model.Account;
import com.example.model.User;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(Account account,User u) {
		System.out.println("create account in service........");

		if (accountRepository.findByEmail(account.getEmail()) != null) {
			throw new RuntimeException(" already exists");
		}
		else {
         account.setUser(u);
		return accountRepository.save(account);
		}
	}

	public Account findByEmail(String Email) {
		return accountRepository.findByEmail(Email);
	}

}
