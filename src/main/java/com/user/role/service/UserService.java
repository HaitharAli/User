package com.user.role.service;

import java.util.List;
import java.util.Optional;

import com.user.role.dto.UserDto;
import com.user.role.dto.UserInputDto;

public interface UserService {

	List<UserDto> getAllUsers();

	UserDto addUser(UserInputDto userInputDto, String loginUserId);

	Optional<UserDto> getUserById(Long id);

	UserDto updateUser(UserDto userDetails);

	Boolean deleteUser(UserDto userDetails);

	List<UserDto> getAllUsersPaginated(int pageNo, int pageSize);

	UserDto getUserDetailsByUserId(String userId);

}
