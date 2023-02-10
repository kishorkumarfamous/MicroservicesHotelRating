package com.codewithdurgesh.blog.repositories;

import com.codewithdurgesh.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.IntrospectionException;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
