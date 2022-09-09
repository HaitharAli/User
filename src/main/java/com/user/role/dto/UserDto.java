package com.user.role.dto;

import java.io.Serializable;
import java.util.List;

import com.user.role.entity.AbstractEntity;
import com.user.role.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -1530311220388659890L;
	private Integer userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String userSID;
	private String password;
	private List<Role> userRoles;

}
