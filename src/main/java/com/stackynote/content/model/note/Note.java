package com.stackynote.content.model.note;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "note")
public class Note {
    @Id
    private String id;

    private String note_code;
    private String type;
    private String title;
    private String description;
    private String link;
    private List stack_codes;
    private String image;
    @CreatedDate
    private Date created_on;
    private String author;
}

