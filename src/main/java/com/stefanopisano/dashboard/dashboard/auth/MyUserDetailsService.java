package com.stefanopisano.dashboard.dashboard.auth;

import com.stefanopisano.dashboard.dashboard.users.dao.UserRepository;
import com.stefanopisano.dashboard.dashboard.users.model.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Setter(onMethod=@__({@Autowired}))
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userByUsername = userRepository.findByUsername(username);

		userByUsername.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));

		return userByUsername.map(MyUserDetails::new).get();
	}
}
