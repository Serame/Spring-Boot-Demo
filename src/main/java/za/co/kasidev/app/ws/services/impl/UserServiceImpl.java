package za.co.kasidev.app.ws.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.kasidev.app.ws.UserRepository;
import za.co.kasidev.app.ws.io.entity.UserEntity;
import za.co.kasidev.app.ws.services.UserService;
import za.co.kasidev.app.ws.shared.dto.UserDto;


@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto CreateUser(UserDto userDto) {
		
		
		if(userRepository.findByEmail(userDto.getEmail()) != null) throw new RuntimeException("The user already exists");
		
		
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		
		userEntity.setEncryptedPassword("test");
		userEntity.setUserId("testUserID");
		
		UserEntity savedUserDetails =  userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(savedUserDetails, returnValue);	
		
		
		return returnValue;
	}
	
	

}
