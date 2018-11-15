package com.stackroute.keepnote.repository;

import com.stackroute.keepnote.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAllCategoryByCategoryCreatedBy(String createdBy);
}
