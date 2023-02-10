package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto  user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUSerById(Integer userId);
    List<UserDto>getAllUsers();
    void deleteUser(Integer userId);
}
