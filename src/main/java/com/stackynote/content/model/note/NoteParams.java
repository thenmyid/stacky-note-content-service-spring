package com.stackynote.content.model.note;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoteParams {
    private String title;
    private String type;
    private String description;
    private String link;
    private ArrayList stacks;
    private String image;
    private String author;
}