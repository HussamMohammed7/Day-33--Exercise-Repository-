package com.example.usermangement.Service;


import com.example.usermangement.Api.ApiException;
import com.example.usermangement.Model.User;
import com.example.usermangement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {

        userRepository.save(user);

    }

    public void updateUser(Integer id, User user) {
        User updatedUser = userRepository.findUserById(id);
        if (updatedUser == null) {
            throw new ApiException("user not found");
        }

        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRole(user.getRole());
        updatedUser.setAge(user.getAge());
        updatedUser.setUsername(user.getUsername());

        userRepository.save(updatedUser);
    }

    public void removeUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException("user not found");
        }

        userRepository.deleteById(id);
    }

    public User getUserByEmail (String email) {

        if ( userRepository.findUserByEmail(email)==null) {
            throw new ApiException("user not found");
        }
        return userRepository.findUserByEmail(email);

    }

    public boolean checkUserNameAndPassword (String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("user not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new ApiException("wrong password");
        }
        return true;
    }

    public List<User>  getUsersByRole (String role) {

        if (userRepository.getRole(role).isEmpty()){
           throw new ApiException("No users with the given role");
        }

        return userRepository.getRole(role);

    }

    public List<User>  getUsersByAge (int age) {

        List users = userRepository.getAgeOrAbove(age);

        if (users.isEmpty()) {
            throw new ApiException("No users with the given age");
        }
        return  users;

    }




}
