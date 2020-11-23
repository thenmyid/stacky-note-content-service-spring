package com.stackynote.content.model.note;

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
public class NoteResponse extends Response {
    private List<Note> noteList;
    private String stack_code;
    private String type;
}