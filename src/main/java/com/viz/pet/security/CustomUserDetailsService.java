package com.viz.pet.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.viz.pet.entity.Customer;
import com.viz.pet.repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	private CustomerRepository userRepository;

    public CustomUserDetailsService(CustomerRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Customer user = userRepository.findByEmail(usernameOrEmail);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getPassword(),
                    user.getPets().stream()
                            .map((pet) -> new SimpleGrantedAuthority(pet.getName()))
                            .collect(Collectors.toList()));
        }else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
