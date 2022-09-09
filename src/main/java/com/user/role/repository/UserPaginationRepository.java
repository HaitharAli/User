package com.user.role.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.user.role.entity.User;

@Repository
public interface UserPaginationRepository extends PagingAndSortingRepository<User, Long> {

}
