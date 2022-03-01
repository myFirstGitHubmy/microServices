package com.services;

import com.domain.ListNotes;
import com.domain.Note;
import com.repositories.ListNotesRepo;
import com.repositories.NoteRepo;
import com.shared.dto.NoteDto;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    @Autowired
    private NoteRepo noteBoard;

    @Autowired
    private ListNotesRepo listNotesRepo;

    public NoteDto createNote(Note note){
        Note newNote = new Note();
            newNote.setName(note.getName());
            newNote.setListNotes(null);
            newNote.setCreate_date(new DateTime().toDate());
            newNote.setLast_modified_date(new DateTime().toDate());
            noteBoard.save(newNote);
        return newNote.toDto();
    }

    public NoteDto save(Note note){
        Note newNote = new Note();
        newNote.setName(note.getName());
        newNote.setListNotes(note.getListNotes());
        newNote.setCreate_date(new DateTime().toDate());
        newNote.setLast_modified_date(new DateTime().toDate());
        noteBoard.save(newNote);
        return newNote.toDto();
    }

    public NoteDto createNoteByName(String name){
        Note newNote = new Note();
        newNote.setName(name);
        newNote.setListNotes(null);
        newNote.setCreate_date(new DateTime().toDate());
        newNote.setLast_modified_date(new DateTime().toDate());
        noteBoard.save(newNote);
        return newNote.toDto();
    }

    public NoteDto createNoteByNameById(String name, Long id){
        ListNotes getList = listNotesRepo.getOne(id);
        Note newNote = new Note();
        newNote.setName(name);
        newNote.setListNotes(getList);
        newNote.setCreate_date(new DateTime().toDate());
        newNote.setLast_modified_date(new DateTime().toDate());
        noteBoard.save(newNote);
        return newNote.toDto();
    }

    public NoteDto updateNote(Note note){
        Note uNote = noteBoard.getOne(note.getId());
        uNote.setName(note.getName());
        uNote.setListNotes(note.getListNotes());
        uNote.setLast_modified_date(new DateTime().toDate());
        noteBoard.save(uNote);
        return uNote.toDto();
    }

    public void remove(Long id){
        noteBoard.deleteById(id);
    }

    public List<NoteDto> getAllNotes(){
        return noteBoard.findAll().stream().map(Note::toDto).collect(Collectors.toList());
    }

    public NoteDto getNote(Long id){
        return noteBoard.getOne(id).toDto();
    }
}
