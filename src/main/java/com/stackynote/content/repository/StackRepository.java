package com.stackynote.content.repository;

import com.stackynote.content.model.stack.Stack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StackRepository extends MongoRepository<Stack, String> {
    @Query("{ stack_code: ?0 }")
    List<Stack> findByStackCode(String code);

    @Query("{ category_codes: ?0 }")
    List<Stack> findByCategoryCodesContaining(String code);

    @Query("{ sub_category_codes: ?0 }")
    List<Stack> findBySubCategoryCodesContaining(String code);
}
