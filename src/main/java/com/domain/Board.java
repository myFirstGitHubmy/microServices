package com.domain;

import com.shared.dto.BoardDto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date create_date;

    @Column(nullable = false)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board")
    public List<ListNotes> listOfNotes;

    public Board(String name, Date create_date, List<ListNotes> notes) {
        this.name = name;
        this.create_date = create_date;
        this.listOfNotes = notes;
    }

    public Board() {
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

    public List<ListNotes> getNotes() {
        return listOfNotes;
    }

    public void setNotes(List<ListNotes> notes) {
        this.listOfNotes = notes;
    }

    public BoardDto toDto(){
        return new BoardDto(
                id,
                name,
                create_date,
                listOfNotes
        );
    }
}
