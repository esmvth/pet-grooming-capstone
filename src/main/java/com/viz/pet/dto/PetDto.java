package com.viz.pet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
	private Long id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	@NotEmpty(message = "Password should be empty")
	private String password;
	@Pattern(regexp="(^$|[0-9]{10})")
	private Long phonenumber;
}
