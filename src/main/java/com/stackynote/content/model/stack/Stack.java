package com.stackynote.content.model.stack;

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
@Document(collection = "stack")
public class Stack {
    @Id
    private String id;

    private List category_codes;
    private List sub_category_codes;
    private String stack_code;
    private String stack_name;
    private String description;
    private String about;
    private ArrayList<Link> links;
    private Integer no_notes;
    private String image;
}

