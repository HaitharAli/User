package com.user.role.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.user.role.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserInputDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 759455642163390428L;

	@NotBlank(message ="firstName shouldn't be null/Empty")
	private String firstName;
	private String lastName;
	@NotBlank(message ="gender shouldn't be null/Empty")
	private String gender;
	@Email(message ="invalid email address")
	private String email;
	private String userSID;
	private String password;
	private List<Role> roles;
	
}
