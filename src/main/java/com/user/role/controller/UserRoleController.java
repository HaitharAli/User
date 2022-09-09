package com.user.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.role.dto.PaginationRequest;
import com.user.role.dto.UserDto;
import com.user.role.dto.UserInputDto;
import com.user.role.exception.UserNotFoundException;
import com.user.role.service.UserService;

@RestController
public class UserRoleController {

	Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	@Autowired
	private UserService userService;

	@GetMapping("")
	ResponseEntity<String> hello() {
		logger.info("UserRoleController::getAllUsers - Start");
		return new ResponseEntity<String>("hello", HttpStatus.OK);

	}

	@GetMapping("/getAllUsers")
	ResponseEntity<List<UserDto>> getAllUsers() {
		logger.info("UserRoleController::getAllUsers - Start");
		List<UserDto> userDetails = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userDetails, HttpStatus.OK);

	}

	@GetMapping("/getAllUsersPaginated")
	ResponseEntity<List<UserDto>> getAllUsersPaginated(@RequestBody PaginationRequest paginationRequest) {
		logger.info("UserRoleController::getAllUsersPaginated - Start");
		List<UserDto> userDetails = userService.getAllUsersPaginated(paginationRequest.getPageNo(),
				paginationRequest.getPageSize());
		return new ResponseEntity<List<UserDto>>(userDetails, HttpStatus.OK);

	}

	@GetMapping("/getById/{id}")
	ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws UserNotFoundException {
		logger.info("UserRoleController::getById - Start");
		UserDto userDetails = userService.getUserById(id)
				.orElseThrow(() -> new UserNotFoundException("user id not found: " + id));
		return new ResponseEntity<UserDto>(userDetails, HttpStatus.OK);

	}
	
	@GetMapping("/getByUserId/{userId}")
	ResponseEntity<UserDto> getUserDetailsByUserId(@PathVariable String userId) throws UserNotFoundException {
		logger.info("UserRoleController::getById - Start");
		logger.info("UserRoleController::getById-userId "+userId);
		UserDto userDetails = userService.getUserDetailsByUserId(userId);
		return new ResponseEntity<UserDto>(userDetails, HttpStatus.OK);

	}

	@PostMapping("/addUser")
	ResponseEntity<UserDto> addUser(@RequestBody @Valid UserInputDto userInputDto, @RequestParam String loginUserId) {
		logger.info("UserRoleController::addUser - Start");
		System.out.println("input email " + userInputDto.getLastName());
		UserDto savedUser = userService.addUser(userInputDto, loginUserId);
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
	}

	@PutMapping("/updateUser/{id}")
	ResponseEntity<UserDto> updateUserById(@PathVariable Long id, @RequestBody @Valid UserInputDto userInputDto)
			throws UserNotFoundException {
		logger.info("UserRoleController::updateUserById - Start");
		UserDto userDetails = userService.getUserById(id)
				.orElseThrow(() -> new UserNotFoundException("user id not found: " + id));

		userDetails.setFirstName(userInputDto.getFirstName());
		userDetails.setLastName(userInputDto.getLastName());
		userDetails.setEmail(userInputDto.getEmail());
		userDetails.setGender(userInputDto.getGender());
		userDetails.setUserRoles(userInputDto.getRoles());

		UserDto updatedUserDto = userService.updateUser(userDetails);

		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);

	}

	@DeleteMapping("/deleteUser/{id}")
	ResponseEntity<Map<String, Boolean>> deleteByUserId(@PathVariable Long id) throws UserNotFoundException {
		logger.info("UserRoleController::deleteByUserId - Start");
		UserDto userDetails = userService.getUserById(id)
				.orElseThrow(() -> new UserNotFoundException("user id not found: " + id));

		Boolean updatedUserDto = userService.deleteUser(userDetails);
		Map<String, Boolean> response = new HashMap();
		response.put("deleted User success", true);

		System.out.println("Delete user Details");
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);

	}

	@PatchMapping("/updateUserName/{id}")
	ResponseEntity<UserDto> updateUserName(@PathVariable Long id, @RequestBody UserInputDto userInputDto)
			throws UserNotFoundException {
		logger.info("UserRoleController::updateUserName - Start");
		UserDto userDetails = userService.getUserById(id)
				.orElseThrow(() -> new UserNotFoundException("user id not found: " + id));

		userDetails.setFirstName(userInputDto.getFirstName());
		userDetails.setLastName(userInputDto.getLastName());

		UserDto updatedUserDto = userService.updateUser(userDetails);

		System.out.println("Update user Details");
		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);

	}

}
