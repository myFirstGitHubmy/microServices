package com.shared.dto;

import com.domain.ListNotes;
import com.domain.Note;

import java.util.Date;
import java.util.List;

public class BoardDto {
    public Long id;
    public String name;
    public Date create_date;
    public List<ListNotes> notes;

    public BoardDto(Long id, String name, Date create_date, List<ListNotes> notes) {
        this.id = id;
        this.name = name;
        this.create_date = create_date;
        this.notes = notes;
    }
}
