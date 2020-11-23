package com.stackynote.content.model.subCategory;

import com.stackynote.content.model.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryResponse extends Response {
    private List<SubCategory> subCategoryList;
    private String category_code;
    private String sub_category_code;
}