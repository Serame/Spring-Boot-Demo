package za.co.kasidev.app.ws.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import za.co.kasidev.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto CreateUser(UserDto user);
	

}
