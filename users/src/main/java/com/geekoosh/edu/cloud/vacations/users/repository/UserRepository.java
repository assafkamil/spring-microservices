package com.geekoosh.edu.cloud.vacations.users.repository;


import com.geekoosh.edu.cloud.vacations.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository  extends PagingAndSortingRepository<User, Long> {
}
