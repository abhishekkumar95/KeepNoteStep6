package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping()
    public ResponseEntity createCategory(@RequestBody Category category) {
        ResponseEntity responseEntity = null;
        try {
            Category category1 = categoryService.createCategory(category);
            responseEntity = new ResponseEntity(category1, HttpStatus.CREATED);

        } catch (CategoryNotCreatedException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable() String categoryId) {
        ResponseEntity responseEntity = null;
        try {
            categoryService.deleteCategory(categoryId);
            responseEntity = new ResponseEntity("Deleted Successfully", HttpStatus.OK);
        } catch (CategoryDoesNoteExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable String categoryId, @RequestBody Category category) {
        ResponseEntity responseEntity = null;
        Category updateCategory = categoryService.updateCategory(category, categoryId);
        if (updateCategory != null) {
            responseEntity = new ResponseEntity(updateCategory, HttpStatus.OK);

        } else {
            responseEntity = new ResponseEntity("Unable to update please try again", HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable String categoryId) {
        ResponseEntity responseEntity = null;
        try {

            Category fetchedCategory = categoryService.getCategoryById(categoryId);
            responseEntity = new ResponseEntity(fetchedCategory, HttpStatus.OK);

        } catch (CategoryNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity getAllCategoryByUserId(@PathVariable String userId) {
        ResponseEntity responseEntity = null;
        List<Category> allCategory = categoryService.getAllCategoryByUserId(userId);
        if (allCategory != null) {
            responseEntity = new ResponseEntity(allCategory, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity("Error in loading the content", HttpStatus.CONFLICT);
        }

        return responseEntity;
    }


}
