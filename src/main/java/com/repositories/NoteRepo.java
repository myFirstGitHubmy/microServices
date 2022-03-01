package com.repositories;

import com.domain.Note;
import com.shared.dto.NoteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
    @Query("select n from Note n where listNotes = :id")
    public List<Note> listNotes(@Param("id") Long id);
}
