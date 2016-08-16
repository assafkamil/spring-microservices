package com.geekoosh.edu.cloud.vacations.users.services;

import com.geekoosh.edu.cloud.vacations.users.model.Order;
import com.geekoosh.edu.cloud.vacations.users.model.Preference;
import com.geekoosh.edu.cloud.vacations.users.model.User;
import com.geekoosh.edu.cloud.vacations.users.repository.OrderRepository;
import com.geekoosh.edu.cloud.vacations.users.repository.PreferenceRepository;
import com.geekoosh.edu.cloud.vacations.users.repository.UserRepository;
import com.geekoosh.edu.cloud.vacations.users.sdk.CreateUserRequest;
import com.geekoosh.edu.cloud.vacations.users.sdk.UserExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public User userById(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = false, rollbackFor = UserExistsException.class)
    public User newUser(CreateUserRequest createUserRequest) throws UserExistsException {
        try {
            User newUser = modelMapper.map(createUserRequest, User.class);
            userRepository.save(newUser);

            return newUser;
        } catch(DataIntegrityViolationException e) {
            throw new UserExistsException(createUserRequest.getUsername(), e);
        }
    }

    public List<User> getUsers(Pageable pageable) {
        Slice<User> users = userRepository.findAll(pageable);
        return users.getContent();
    }

    @Transactional(readOnly = false)
    public Order createOrder(Long userId, String details) {
        User user = userRepository.findOne(userId);

        Order order = new Order();
        order.setUser(user);
        order.setDetails(details);
        order.setOrderDate(new Date());
        orderRepository.save(order);

        return order;
    }

    @Transactional(readOnly = false)
    public void setUserPreference(Long userId, Long preferenceId) {
        User user = userRepository.findOne(userId);
        Preference preference = preferenceRepository.findOne(preferenceId);

        user.getPreferences().add(preference);

        userRepository.save(user);
    }

    @Transactional
    public List<Preference> getUserPreferences(Long userId) {
        User user = userRepository.findOne(userId);
        int size = user.getPreferences().size();
        return user.getPreferences();
    }

    @Transactional
    public List<Order> getUserOrders(Long userId) {
        User user = userRepository.findOne(userId);
        int size = user.getOrders().size();
        return user.getOrders();
    }
}
