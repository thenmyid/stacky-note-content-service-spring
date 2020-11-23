package com.stackynote.content.controller;

import com.stackynote.content.model.subCategory.SubCategory;
import com.stackynote.content.model.subCategory.SubCategoryConstant;
import com.stackynote.content.model.subCategory.SubCategoryResponse;
import com.stackynote.content.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SubCategoryController {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @GetMapping("/sub-category")
    public ResponseEntity<SubCategoryResponse> getSubCategory(
            @RequestParam(required = true) String action,
            @RequestParam(required = false) String code
    ) {
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        Exception BAD_REQUEST = new Exception(HttpStatus.BAD_REQUEST.toString());
        try {
            List<SubCategory> subCategories = new ArrayList<SubCategory>();
            switch(action) {
                case SubCategoryConstant.REST_ACTION_CATEGORY_BY_CODE:
                    if (code == null) throw BAD_REQUEST;
                    subCategoryRepository.findBySubCategoryCode(code).forEach(subCategories::add);
                    subCategoryResponse.setCategory_code(code);
                    break;
                case SubCategoryConstant.REST_ACTION_CATEGORY:
                    if (code == null) throw BAD_REQUEST;
                    subCategoryRepository.findByCategoryCodesContaining(code).forEach(subCategories::add);
                    subCategoryResponse.setCategory_code(code);
                    break;
                case SubCategoryConstant.REST_ACTION_ALL:
                    subCategoryRepository.findAll().forEach(subCategories::add);
                    break;
                default:
                    throw BAD_REQUEST;
            }

            Integer statusCode = subCategories.isEmpty() ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value();
            subCategoryResponse.setStatus(statusCode);
            subCategoryResponse.setSubCategoryList(subCategories);

            return new ResponseEntity<>(subCategoryResponse, HttpStatus.OK);
        } catch (Exception e) {
            if(e == BAD_REQUEST)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
