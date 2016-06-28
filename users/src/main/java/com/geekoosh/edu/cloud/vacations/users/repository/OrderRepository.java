package com.geekoosh.edu.cloud.vacations.users.repository;


import com.geekoosh.edu.cloud.vacations.users.model.Order;
import com.geekoosh.edu.cloud.vacations.users.model.User;
import com.geekoosh.edu.cloud.vacations.users.model.UserData;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}
