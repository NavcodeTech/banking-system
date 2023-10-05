package com.example.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.dao.AccountRepository;
import com.example.dao.UserRepository;
import com.example.exception.UserAlreadyException;
import com.example.model.Account;
import com.example.model.User;
import com.example.response.LoginMessage;


@Service
public class UserService {
	
	HashMap<String,String> omap=new HashMap<String,String>();
    
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private AccountRepository accountRepository;
	
//	 @Autowired
//	    private PasswordEncoder passwordEncoder;

    public User createUser(User user) throws UserAlreadyException {
    	System.out.println("create user called in service...............");
        // Implement registration logic
    	if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyException("Username already exists");
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") private String sender;
	
	public String sendSimpleMail(String email)
	{
		Random random=new Random(1000);
		int otp=random.nextInt(9999);
		System.out.println(otp);
		
		try {
			SimpleMailMessage mailMessage= new SimpleMailMessage();
			String body="Please enter this Otp to verify your Mail for smart contact:"+otp;
			mailMessage.setFrom(sender);
			mailMessage.setSubject("Email verification - PCunia Banking");
			omap.put("myotp",String.valueOf(otp));
			omap.put("myemail",email);
			
			mailMessage.setText(body);
			mailMessage.setTo(email);
			
			javaMailSender.send(mailMessage);
			return "success";
		}
		catch(Exception e)
		{
			
			return "failure";
		}
		
	}

	public boolean verifyOtp(String otp) {
		System.out.println("System otp:"+omap.get("myotp"));
		if(otp.equals(omap.get("myotp")))
		{
			return true;
		}
		else
		return false;
	}

	public void changePassword(String password) {
		User u=userRepository.findByEmail(omap.get("myemail"));
		u.setPassword(passwordEncoder.encode(password));
		u.setConfirmPassword(passwordEncoder.encode(password));
		System.out.println("user:"+u.getEmail()+"passw:"+password);
		userRepository.save(u);
		omap.clear();
	}

	public void welcomeMail(String name, String email) {
		// TODO Auto-generated method stub
		try {
			SimpleMailMessage mailMessage= new SimpleMailMessage();
			String body="Dear "+name+",Welcome to one of the most trusted bank of modern india \nYou "
					+ "will get extensive "
					+ "financial service through engaging with us.";
			mailMessage.setFrom(sender);
			mailMessage.setSubject("Welcome to Pcunia Banking");
			mailMessage.setText(body);
			mailMessage.setTo(email);
			javaMailSender.send(mailMessage);
			
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
    

}