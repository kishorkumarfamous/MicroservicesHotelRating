package com.codewithdurgesh.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4, message= "username must be 4 character!!!!")
    private String name;
    @Email(message = "email is not  valid")
    private String email;
    @NotEmpty
    private String about;
    @NotNull
    @Size(min=3, max=10,message = "password min 3 and max 10 character")
    private String password;

}
