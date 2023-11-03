package com.homeloan.main.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.homeloan.main.model.User;
import com.homeloan.main.repository.UserRepository;
import com.homeloan.main.security.UserPrincipal;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
				.orElseThrow(()-> new UsernameNotFoundException(String.format("User not found with this username or email: %s", usernameOrEmail)));
		return UserPrincipal.create(user);
	}

	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id)
		.orElseThrow(()-> new UsernameNotFoundException(String.format("User not found with id: %s", id)));
		return UserPrincipal.create(user);
	}
}
