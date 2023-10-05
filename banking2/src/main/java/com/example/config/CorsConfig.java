package com.example.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig implements Ordered {
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	    	CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)
	        configuration.addAllowedHeader("*"); // You can customize allowed headers if needed
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	}

		@Override
		public int getOrder() {
			// TODO Auto-generated method stub
			return Ordered.HIGHEST_PRECEDENCE;
		}

}
