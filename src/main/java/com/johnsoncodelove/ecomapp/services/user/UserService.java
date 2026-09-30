package com.johnsoncodelove.ecomapp.services.user;

import com.johnsoncodelove.ecomapp.dto.UserRequest;
import com.johnsoncodelove.ecomapp.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserResponse> getAllUsers();

    public Optional<UserResponse> getUser(Long id);

    public UserResponse addUser(UserRequest userRequest);

    public UserResponse updateUser(Long id, UserRequest userRequest);
}
