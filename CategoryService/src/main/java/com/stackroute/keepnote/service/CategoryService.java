package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

import java.util.List;
import java.util.NoSuchElementException;

public interface CategoryService {

    Category createCategory(Category category) throws CategoryNotCreatedException;

    boolean deleteCategory(String categoryId) throws CategoryDoesNoteExistsException;

    Category updateCategory(Category category, String categoryId);

    Category getCategoryById(String categoryId) throws CategoryNotFoundException;

    List<Category> getAllCategoryByUserId(String userId);

}
