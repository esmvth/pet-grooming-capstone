package com.viz.pet.service;

import java.util.List;

import com.viz.pet.dto.PetDto;
import com.viz.pet.entity.Customer;

public interface PetService {
	void saveUser(PetDto userDto);
	Customer findUserByEmail(String email);
	List<PetDto> findAllUsers();

}
