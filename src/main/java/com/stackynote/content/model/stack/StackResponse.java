package com.stackynote.content.model.stack;

import com.stackynote.content.model.Link;
import com.stackynote.content.model.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackResponse extends Response {
    private List<Stack> stackList;
    private String stack_code;
    private String category_code;
    private String sub_category_code;
    private String description;
    private String about;
    private ArrayList<Link> links;
    private Integer no_notes;
}
