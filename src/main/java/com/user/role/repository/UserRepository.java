package com.user.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.role.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserSID(String userSID);
}
