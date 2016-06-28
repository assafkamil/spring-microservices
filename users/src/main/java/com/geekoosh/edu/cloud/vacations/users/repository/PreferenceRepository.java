package com.geekoosh.edu.cloud.vacations.users.repository;


import com.geekoosh.edu.cloud.vacations.users.model.Order;
import com.geekoosh.edu.cloud.vacations.users.model.Preference;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PreferenceRepository extends PagingAndSortingRepository<Preference, Long> {
}
