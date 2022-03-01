package com.shared.dto;

import com.domain.ListNotes;

import java.util.Date;

public class NoteDto {
    public Long id;
    public String name;
    public Date create_date;
    public Date last_modified_date;
    public ListNotes listNotes;

    public NoteDto(Long id, String name, Date create_date, Date last_modified_date, ListNotes listNotes) {
        this.id = id;
        this.name = name;
        this.create_date = create_date;
        this.last_modified_date = last_modified_date;
        this.listNotes = listNotes;
    }
}
