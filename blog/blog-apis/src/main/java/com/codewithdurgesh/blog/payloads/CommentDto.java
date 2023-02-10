package com.codewithdurgesh.blog.payloads;

import com.codewithdurgesh.blog.entities.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int id;
    private String content;


}
