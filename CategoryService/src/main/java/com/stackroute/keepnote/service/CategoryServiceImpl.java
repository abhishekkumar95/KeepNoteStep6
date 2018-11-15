package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) throws CategoryNotCreatedException {
        category.setCategoryCreationDate(new Date());
        Category category1 = categoryRepository.insert(category);
        if (category1 == null) {
            throw new CategoryNotCreatedException("Unable to create new Category");
        }
        return category1;
    }

    @Override
    public boolean deleteCategory(String categoryId) throws CategoryDoesNoteExistsException {
        boolean status = false;
        Category fetchedCategory = categoryRepository.findById(categoryId).get();
        if (fetchedCategory == null) {
            throw new CategoryDoesNoteExistsException("Category with given name does not exists");

        } else {
            categoryRepository.delete(fetchedCategory);
            status = true;
        }
        return status;
    }

    @Override
    public Category updateCategory(Category category, String categoryId) {

        Category fetchedCategory = categoryRepository.findById(categoryId).get();
        //  fetchedCategory.setCategoryId(fetchedCategory.getCategoryId());
        fetchedCategory.setCategoryName(category.getCategoryName());
        fetchedCategory.setCategoryDescription(category.getCategoryDescription());
        fetchedCategory.setCategoryCreatedBy(category.getCategoryCreatedBy());
        fetchedCategory.setCategoryCreationDate(new Date());
        categoryRepository.save(fetchedCategory);

        return fetchedCategory;
    }

    @Override
    public Category getCategoryById(String categoryId) throws CategoryNotFoundException {

        try {
            Category fetchedCategory = categoryRepository.findById(categoryId).get();

            return fetchedCategory;
        } catch (NoSuchElementException e) {
            throw new CategoryNotFoundException("Category does not exists");
        }


    }

    @Override
    public List<Category> getAllCategoryByUserId(String userId) {

        return categoryRepository.findAllCategoryByCategoryCreatedBy(userId);

    }
}
