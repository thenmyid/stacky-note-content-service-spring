package com.stackynote.content.controller;

import com.stackynote.content.model.note.Note;
import com.stackynote.content.model.note.NoteConstant;
import com.stackynote.content.model.note.NoteParams;
import com.stackynote.content.model.note.NoteResponse;
import com.stackynote.content.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/note")
    public ResponseEntity<NoteResponse> getNote(
            @RequestParam(required = true) String action,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String type
    ) {
        NoteResponse noteResponse = new NoteResponse();
        Exception BAD_REQUEST = new Exception(HttpStatus.BAD_REQUEST.toString());
        try {
            List<Note> notes = new ArrayList<Note>();
            switch(action) {
                case NoteConstant.REST_ACTION_STACK:
                    if (code == null) throw BAD_REQUEST;
                    if (type == null) {
                        noteRepository.findByStackCodesContaining(code).forEach(notes::add);
                    } else {
                        noteRepository.findByStackCodesContaining(code, type).forEach(notes::add);
                        noteResponse.setType(type);
                    }
                    System.out.println(notes.size());
                    noteResponse.setStack_code(code);
                    break;
                case NoteConstant.REST_ACTION_SUMMARY:
                    if (type == null) {
                        noteRepository.findAll().forEach(notes::add);
                    } else {
                        noteRepository.findByType(type).forEach(notes::add);
                        noteResponse.setType(type);
                    }
                    break;
                default:
                    throw BAD_REQUEST;
            }

            Integer statusCode = notes.isEmpty() ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value();

            noteResponse.setStatus(statusCode);
            noteResponse.setNoteList(notes);

            return new ResponseEntity<>(noteResponse, HttpStatus.OK);
        } catch (Exception e) {
            if(e == BAD_REQUEST)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/note")
    public ResponseEntity createNote(
            @RequestBody NoteParams noteParams
            ) {
        Exception BAD_REQUEST = new Exception(HttpStatus.BAD_REQUEST.toString());
        try {
            Note note = new Note();
            note.setType(noteParams.getType());
            note.setTitle(noteParams.getTitle());
            note.setNote_code(noteParams.getTitle());
            note.setDescription(noteParams.getDescription());
            note.setStack_codes(noteParams.getStacks());
            note.setLink(noteParams.getLink());
            note.setImage(noteParams.getImage());
            Date createdOn = new Date();
            note.setCreated_on(createdOn);
            note.setAuthor(noteParams.getAuthor());
            noteRepository.insert(note);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            if(e == BAD_REQUEST)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
