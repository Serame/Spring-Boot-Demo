package za.co.kasidev.app.ws.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import za.co.kasidev.app.ws.UserRepository;
import za.co.kasidev.app.ws.io.entity.UserEntity;
import za.co.kasidev.app.ws.services.UserService;
import za.co.kasidev.app.ws.shared.dto.UserDto;
import za.co.kasidev.app.ws.shared.utils.Utils;


@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoer;
	
	@Autowired
	Utils utils;

	@Override
	public UserDto CreateUser(UserDto userDto) {
		
		
		if(userRepository.findByEmail(userDto.getEmail()) != null) throw new RuntimeException("The user already exists");
		
		
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		
		String publicUserID = utils.generatedUserId(30);
		
		userEntity.setUserId(publicUserID);
		userEntity.setEncryptedPassword(bCryptPasswordEncoer.encode(userDto.getPassword()));
		
		UserEntity savedUserDetails =  userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(savedUserDetails, returnValue);	
		
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
