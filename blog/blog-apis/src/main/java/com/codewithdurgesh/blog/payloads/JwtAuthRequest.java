package com.codewithdurgesh.blog.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;//email is my username
    private String password;
}
