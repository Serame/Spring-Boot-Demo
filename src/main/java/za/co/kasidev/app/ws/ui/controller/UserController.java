package za.co.kasidev.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.kasidev.app.ws.services.UserService;
import za.co.kasidev.app.ws.shared.dto.UserDto;
import za.co.kasidev.app.ws.ui.model.request.UserDetailsRequestModel;
import za.co.kasidev.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping
	public String getUser()
	{
		return "get user method was called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest userRest = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		
		UserDto createdUser = userService.CreateUser(userDto);		
		BeanUtils.copyProperties(createdUser, userRest);
		
		userRest.setMessage("If you getting this then your request went through.  Danko!!!");
		
		return userRest;
	}
	
	@PutMapping
	public String updateUser()
	{
		return "Upate user was called";
	}
	
	@DeleteMapping
	public String deleteUser()
	{
		return "Delete user was called";
	}
	
	

}
