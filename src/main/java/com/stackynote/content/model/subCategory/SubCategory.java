package com.stackynote.content.model.subCategory;

import com.stackynote.content.model.Link;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sub_category")
public class SubCategory {
    @Id
    private String id;

    private List category_codes;
    private String sub_category_code;
    private String sub_category_name;
    private String description;
    private String about;
    private ArrayList<Link> links;
    private String image;
}

