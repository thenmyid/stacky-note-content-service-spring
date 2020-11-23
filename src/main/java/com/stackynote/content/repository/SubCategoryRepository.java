package com.stackynote.content.repository;

import com.stackynote.content.model.subCategory.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SubCategoryRepository extends MongoRepository<SubCategory, String> {
    @Query("{ sub_category_code: ?0 }")
    List<SubCategory> findBySubCategoryCode(String code);

    @Query("{ category_codes: ?0 }")
    List<SubCategory> findByCategoryCodesContaining(String code);
}

