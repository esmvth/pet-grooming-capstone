package com.viz.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.viz.pet.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{
	Pet findByName(String name);
}
