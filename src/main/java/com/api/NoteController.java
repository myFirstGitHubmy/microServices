package com.api;

import com.domain.Note;
import com.services.ListNotesService;
import com.services.NoteService;
import com.shared.dto.NoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNote(@PathVariable("id")Long id){
        return new ResponseEntity<>(noteService.getNote(id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<NoteDto> createNote(@RequestBody Note note){
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<NoteDto> createNoteByName(@RequestParam("name") String name){
        return new ResponseEntity<>(noteService.createNoteByName(name), HttpStatus.OK);
    }

    @GetMapping("/newName")
    public ResponseEntity<NoteDto> createNoteByName(@RequestParam("name") String name, @RequestParam("id") Long id){
        return new ResponseEntity<>(noteService.createNoteByNameById(name, id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoteDto>> getAll(){
        List<NoteDto> listNote = noteService.getAllNotes();
        return new ResponseEntity<>(listNote, HttpStatus.OK);
    }
}
