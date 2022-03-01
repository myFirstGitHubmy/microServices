package com.domain;

import ch.qos.logback.core.pattern.color.BoldRedCompositeConverter;
import com.shared.dto.NoteDto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NOTE")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date create_date;

    @Column(nullable = false)
    private Date last_modified_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notes")
    public ListNotes listNotes;

    public Note() {
    }

    public Note(String name, Date create_date, Date last_modified_date, ListNotes listNotes) {
        this.name = name;
        this.create_date = create_date;
        this.last_modified_date = last_modified_date;
        this.listNotes = listNotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    public ListNotes getListNotes() {
        return listNotes;
    }

    public void setListNotes(ListNotes listNotes) {
        this.listNotes = listNotes;
    }

    public NoteDto toDto(){
        return new NoteDto(
                id,
                name,
                create_date,
                last_modified_date,
                listNotes
        );
    }
}
