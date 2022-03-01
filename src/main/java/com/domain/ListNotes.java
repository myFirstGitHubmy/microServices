package com.domain;

import com.shared.dto.ListNotesDto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LIST_NOTES")
public class ListNotes {
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
    @JoinColumn(name = "listOfNotes")
    private Board board;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "listNotes")
    public List<Note> notes;

    public ListNotes(String name, Date create_date, Date last_modified_date, Board board, List<Note> notes) {
        this.name = name;
        this.create_date = create_date;
        this.last_modified_date = last_modified_date;
        this.board = board;
        this.notes = notes;
    }

    public ListNotes() {
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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public ListNotesDto toDto(){
        return new ListNotesDto(
          id,
          name,
          create_date,
          last_modified_date,
          notes
        );
    }
}
