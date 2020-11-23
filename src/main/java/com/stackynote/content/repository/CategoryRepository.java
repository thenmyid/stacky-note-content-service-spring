package com.stackynote.content.repository;

import com.stackynote.content.model.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query("{ category_code: ?0 }")
    List<Category> findByCategoryCode(String code);
}

