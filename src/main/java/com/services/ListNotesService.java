package com.services;

import com.domain.ListNotes;
import com.domain.Note;
import com.repositories.ListNotesRepo;
import com.repositories.NoteRepo;
import com.shared.dto.ListNotesDto;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListNotesService {
    @Autowired
    private ListNotesRepo listNotesRepo;

    @Autowired
    private NoteRepo noteRepo;

    public ListNotesDto createNewListByName(String name){
        ListNotes newObject = new ListNotes();
        newObject.setCreate_date(new DateTime().toDate());
        newObject.setLast_modified_date(new DateTime().toDate());
        newObject.setName(name);
        newObject.setNotes(null);
        listNotesRepo.save(newObject);
        return newObject.toDto();
    }

    public ListNotesDto createNewList(ListNotes listNotes){
            ListNotes newObject = new ListNotes();
                newObject.setCreate_date(new DateTime().toDate());
                newObject.setLast_modified_date(new DateTime().toDate());
                newObject.setName(listNotes.getName());
                newObject.setNotes(null);
            listNotesRepo.save(newObject);
        return newObject.toDto();
    }

    public ListNotesDto save(ListNotes listNotes){
        List<Note> listNote = noteRepo.listNotes(listNotes.getId());
            ListNotes newObject = new ListNotes();
                newObject.setCreate_date(new DateTime().toDate());
                newObject.setLast_modified_date(new DateTime().toDate());
                newObject.setName(listNotes.getName());
                newObject.setNotes(listNote);
                listNotesRepo.save(newObject);
        return newObject.toDto();
    }

    public ListNotesDto updateListOfNotes(Long id){
        List<Note> listNote = noteRepo.listNotes(id);
        ListNotes uObject = listNotesRepo.getOne(id);
        uObject.setLast_modified_date(new DateTime().toDate());
        uObject.setNotes(listNote);
        listNotesRepo.save(uObject);
        return uObject.toDto();
    }

    public List<ListNotesDto> updateAllListNotes(){
        List<ListNotes> listNote = listNotesRepo.findAll();
        for (ListNotes pair: listNote){
            pair.setNotes(noteRepo.listNotes(pair.getId()));
            pair.setLast_modified_date(new DateTime().toDate());
            listNotesRepo.save(pair);
        }

        return listNote.stream().map(ListNotes::toDto).collect(Collectors.toList());
    }

    public List<ListNotesDto> getAll(){
        return listNotesRepo.findAll().stream().map(ListNotes::toDto).collect(Collectors.toList());
    }

    public ListNotesDto getListNotesById(Long id){
        return listNotesRepo.getOne(id).toDto();
    }
}
