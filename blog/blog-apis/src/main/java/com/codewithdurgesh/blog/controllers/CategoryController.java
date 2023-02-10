package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;

    //create


    @PostMapping("/")
    public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }



    //updated

    @PutMapping("/{catId}")
        public ResponseEntity<CategoryDto>updateCategory( @Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
            CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",true), HttpStatus.OK);
    }

    //get

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }


    //getCategories

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getCategories(){
    List<CategoryDto> categories= (List<CategoryDto>) this.categoryService.getCategories();
    return ResponseEntity.ok(categories);
    }

}
