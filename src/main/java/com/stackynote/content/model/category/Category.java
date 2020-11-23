package com.stackynote.content.model.category;

import com.stackynote.content.model.Link;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class Category {
    @Id
    private String id;

    private String category_code;
    private String category_name;
    private String description;
    private String about;
    private ArrayList<Link> links;
}

