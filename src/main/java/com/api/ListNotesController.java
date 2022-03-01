package com.api;

import com.domain.ListNotes;
import com.services.ListNotesService;
import com.shared.dto.ListNotesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/list")
public class ListNotesController {
    @Autowired
    private ListNotesService listNotesService;

    public ListNotesController(ListNotesService listNotesService) {
        this.listNotesService = listNotesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListNotesDto>> getAll(){
        return new ResponseEntity<>(listNotesService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ListNotesDto> createListNotes(@RequestBody ListNotes listNotes){
        return new ResponseEntity<>(listNotesService.save(listNotes), HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<ListNotesDto> createListNotesByName(@RequestParam("name") String name){
        return new ResponseEntity<>(listNotesService.createNewListByName(name),HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ListNotesDto> getListNotesById(@PathVariable("id") Long id){
        return new ResponseEntity<>(listNotesService.getListNotesById(id), HttpStatus.OK);
    }

    @GetMapping("/updateList")
    public ResponseEntity<List<ListNotesDto>> getAllList(){
        return new ResponseEntity<>(listNotesService.updateAllListNotes(),HttpStatus.OK);
    }
}
