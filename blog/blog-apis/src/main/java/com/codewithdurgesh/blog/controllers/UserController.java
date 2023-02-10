package com.codewithdurgesh.blog.controllers;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //GET
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUser(){
        return ResponseEntity.ok( this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok( this.userService.getUSerById(userId));
    }


    //POST
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }


    //PUT
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
        UserDto updateUser = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }

    //ADMIN
        //DELETE
        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/{userId}")
        public ResponseEntity<ApiResponse> deleteUser (@PathVariable Integer userId){
            this.userService.deleteUser(userId);
            return new ResponseEntity(new ApiResponse("user deleted successful",true),HttpStatus.OK);




    }
}
