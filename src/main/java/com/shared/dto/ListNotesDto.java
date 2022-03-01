package com.shared.dto;

import com.domain.ListNotes;
import com.domain.Note;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListNotesDto {
    public Long id;
    public String name;
    public Date createDate;
    public Date lastModifiedDate;
    public List<Long> notes;

    public ListNotesDto(Long id, String name, Date createDate, Date lastModifiedDate, List<Note> notes) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.notes = notes.stream().map(Note::getId).collect(Collectors.toList());
    }
}
