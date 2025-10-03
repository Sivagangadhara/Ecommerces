package com.example.demo.serviceinf;

import java.util.List;

import com.example.demo.payload.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDto);
    void deleteUser(Long id);
    UserDTO getByEmail(String email);
}
