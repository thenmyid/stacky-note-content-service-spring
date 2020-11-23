package com.stackynote.content.repository;
import com.stackynote.content.model.note.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    @Query("{ stack_codes: ?0 }")
    List<Note> findByStackCodesContaining(String code);

    @Query("{ stack_codes: ?0, type: ?1 }")
    List<Note> findByStackCodesContaining(String code, String type);

    @Query("{ type: ?0 }")
    List<Note> findByType(String type);

}
