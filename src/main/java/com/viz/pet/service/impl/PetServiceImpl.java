package com.viz.pet.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.viz.pet.dto.PetDto;
import com.viz.pet.entity.Pet;
import com.viz.pet.entity.Customer;
import com.viz.pet.repository.PetRepository;
import com.viz.pet.repository.CustomerRepository;
import com.viz.pet.service.PetService;

@Service
public class PetServiceImpl implements PetService{
	private CustomerRepository customerRepository;
    private PetRepository petRepository;
    private PasswordEncoder passwordEncoder;
    
	public PetServiceImpl(CustomerRepository userRepository, PetRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.petRepository = petRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void saveUser(PetDto userDto) {
		Customer user = new Customer();
		user.setFirstName(userDto.getFirstName() + " " + userDto.getLastName());
		user.setEmail(userDto.getEmail());
		// encrypt the password using spring security
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		Pet role = petRepository.findByName("PET_ADMIN");
		if (role == null) {
			role = checkPetExist();
		}
		user.setPets(Arrays.asList(role));
		customerRepository.save(user);
	}

	@Override
	public Customer findUserByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public List<PetDto> findAllUsers() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
	}

	private PetDto mapToUserDto(Customer user) {
		PetDto userDto = new PetDto();
		String[] str = user.getFirstName().split(" ");
		userDto.setFirstName(str[0]);
		userDto.setLastName(str[1]);
		userDto.setEmail(user.getEmail());
		return userDto;
	}

	private Pet checkPetExist() {
		Pet role = new Pet();
		role.setName("ROLE_ADMIN");
		return petRepository.save(role);
	}

}
