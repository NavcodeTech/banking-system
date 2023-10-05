package com.example.contrtoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.CustomUserDetails;
import com.example.config.UserDetailsServiceImpl;
import com.example.dao.UserRepository;
import com.example.exception.UserAlreadyException;
import com.example.model.User;
import com.example.service.JwtService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:4200")

@RequestMapping("/api/users")
public class UserController {
	@RequestMapping("/hi")
	public String hi() {
		return "hello";
	}
	
	 
	@Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
    	System.out.println("register called");
        try {
            //User createdUser = userService.createUser(user);
//            return new ResponseEntity<>.status(HttpStatus.CREATED).body("User registered successfully");
        	
        	userService.welcomeMail(user.getName(),user.getEmail());
            return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
        } catch (UserAlreadyException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @Autowired BCryptPasswordEncoder passwordEncoder;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User u) throws Exception {
    	    String uname=u.getUsername();
    	    System.out.println(uname+" "+u.getPassword());
    	    User user=userService.findByUsername(uname);
        	if(user!=null)
        	{
        		if(passwordEncoder.matches(u.getPassword(), user.getPassword()))
        		{
        			Authentication authentication = authenticationManager
        	    	        .authenticate(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword()));

        	    	    SecurityContextHolder.getContext().setAuthentication(authentication);

        	    	    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        	    	    List<String> roles = userDetails.getAuthorities().stream()
        	    	        .map(item -> item.getAuthority())
        	    	        .collect(Collectors.toList());
        	    	    
        	    	    String token = jwtService.generateToken(u.getUsername());
        	    	    String message="{\"status\":\"success\",\"message\":\""+token+"\"}";
                return ResponseEntity.ok().body(message);
                		
        		}
        		else
        		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"status\":\"error\",\"message\":\"Wrong password\"}");
        			
        	}
            else
            {
            	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"status\":\"error\",\"message\":\"Username not found\"}");
            }
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username)
    {
    	System.out.println("get user-home"+username);
    	User u=userService.findByUsername(username);
    	if(u!=null)
    	{
    		return ResponseEntity.ok(u);
    	}
    	else
    		return ResponseEntity.notFound().build();
    }
    @Autowired UserRepository userRepo;
    
    @GetMapping("/send_otp")
    public ResponseEntity<String> submitEmail(@RequestHeader(value="myemail") String email)
    {
    	User u=userRepo.findByEmail(email);
    	System.out.println("backend"+email);
    	System.out.println("backend"+u.getName());
    	if(u!=null)
    	{
    		String ch=userService.sendSimpleMail(email);
    		if(ch.equals("success"))
    		{
    			System.out.println("checking");
    			return ResponseEntity.ok().body("{\"status\":\"success\",\"message\":\"Otp Sent Successful\"}");
    		}
    		else
    			return (ResponseEntity<String>) ResponseEntity.badRequest();
    			
    	}
    	else
    		return ResponseEntity.notFound().build();
    }
    @GetMapping("/verify_otp")
    public ResponseEntity<String> verifyOtp(@RequestHeader(value="myotp") String otp)
    {
    	System.out.println("otp-backend:"+otp);
    	boolean chk=userService.verifyOtp(otp);
    	if(chk)
    	{
    		return ResponseEntity.ok().body("{\"status\":\"success\",\"message\":\"Otp Verified Successful\"}");
    	}
    	else
    		return (ResponseEntity<String>) ResponseEntity.badRequest();
    }
    	
    @GetMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestHeader(value="password") String password)
    {
    	userService.changePassword(password);
    	
    	return ResponseEntity.ok().body("{\"status\":\"success\",\"message\":\"Password Changed Successful\"}");
    }
    
}
