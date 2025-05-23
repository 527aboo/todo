package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(auth -> 
    			auth.requestMatchers("login", "users/register/**", "/css/**", "/js/**").permitAll()
    			.anyRequest().authenticated()
    		)
    		.formLogin(form -> 
    			form.loginPage("/login")
    			.loginProcessingUrl("/login")
    			.defaultSuccessUrl("/todos/todos", true)
    			.failureUrl("/login?error=true")
    			.permitAll()
    		)
    		.logout(logout -> 
    			logout
    			.logoutUrl("/logout")
    			.logoutSuccessUrl("/logout?.logout=true")
    			.invalidateHttpSession(true)
    			.deleteCookies("JSESSIONID")
    			.permitAll()
    		);
    	
    	return http.build();
    }

}
