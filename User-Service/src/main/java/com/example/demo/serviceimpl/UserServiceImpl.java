package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Entity.User;
import com.example.demo.payload.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.serviceinf.UserService;

@Service
public class UserServiceImpl implements UserService{
	private final UserRepository repo;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
 
    public UserDTO createUser(UserDTO dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
        }
        // Convert DTO -> Entity
        User user = modelMapper.map(dto, User.class);
        
        // 🔒 Hash the password before saving
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        
        // Save user
        User saved = repo.save(user);
        
        // Convert back Entity -> DTO
        return modelMapper.map(saved, UserDTO.class);
    }


    @Override
    public UserDTO getUserById(Long id) {
        User user = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (!user.getEmail().equals(dto.getEmail()) && repo.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use");
        }
        modelMapper.map(dto, user);
        User updated = repo.save(user);
        return modelMapper.map(updated, UserDTO.class);
    }
    

    @Override
    public void deleteUser(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        repo.deleteById(id);
    }

    @Override
    public UserDTO getByEmail(String email) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return modelMapper.map(user, UserDTO.class);
    }
}
