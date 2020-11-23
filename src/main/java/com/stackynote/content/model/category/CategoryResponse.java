package com.stackynote.content.model.category;

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
public class CategoryResponse extends Response {
    private List<Category> categoryList;
    private String category_code;
}