package com.stackynote.content.controller;

import com.stackynote.content.model.category.CategoryConstant;
import com.stackynote.content.model.category.Category;
import com.stackynote.content.model.category.CategoryResponse;
import com.stackynote.content.model.note.Note;
import com.stackynote.content.model.note.NoteConstant;
import com.stackynote.content.model.note.NoteResponse;
import com.stackynote.content.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<CategoryResponse> getCategory(
            @RequestParam(required = true) String action,
            @RequestParam(required = false) String code
    ) {
        CategoryResponse categoryResponse = new CategoryResponse();
        Exception BAD_REQUEST = new Exception(HttpStatus.BAD_REQUEST.toString());
        try {
            List<Category> categories = new ArrayList<Category>();
            switch(action) {
                case CategoryConstant.REST_ACTION_CATEGORY_BY_CODE:
                    if (code == null) throw BAD_REQUEST;
                    categoryRepository.findByCategoryCode(code).forEach(categories::add);
                    categoryResponse.setCategory_code(code);
                    break;
                case CategoryConstant.REST_ACTION_ALL:
                    categoryRepository.findAll().forEach(categories::add);
                    break;
                default:
                    throw BAD_REQUEST;
            }

            Integer statusCode = categories.isEmpty() ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value();
            categoryResponse.setStatus(statusCode);
            categoryResponse.setCategoryList(categories);

            return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
        } catch (Exception e) {
            if(e == BAD_REQUEST)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
