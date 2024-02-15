package za.co.kasidev.app.ws.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import za.co.kasidev.app.ws.services.UserService;


@Configuration
@EnableWebSecurity
public class WebSecutiry {
	
	private final UserService userDetailService;
	private final BCryptPasswordEncoder bCryptPassworEncoder;
	
	
	
	public WebSecutiry(UserService userDetailService, BCryptPasswordEncoder bCryptPassworEncoder) {
		this.userDetailService = userDetailService;
		this.bCryptPassworEncoder = bCryptPassworEncoder;
	}



	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		
		AuthenticationManagerBuilder authenticationManagerBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);
		
		authenticationManagerBuilder
		.userDetailsService( userDetailService)
		.passwordEncoder(bCryptPassworEncoder);
		
		http.csrf((csrf) -> {
			try {
				csrf.disable().authorizeHttpRequests(
						(authz) -> authz.requestMatchers(HttpMethod.POST, "/users")
						.permitAll()
						.anyRequest().authenticated());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return http.build();
				
		
		
	}

}
