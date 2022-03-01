package com.services;

import com.domain.Board;
import com.repositories.BoardRepo;
import com.shared.dto.BoardDto;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepo boardRepo;

    public Board save(Board board){
        return boardRepo.save(board);
    }

    public void remove(Long id){
        boardRepo.deleteById(id);
    }

    public Board getBoard(Long id){
        return boardRepo.getOne(id);
    }

    public List<Board> getAllBoards(){
        return boardRepo.findAll();
    }

    public BoardDto createBoard(){
        Board newBoard = new Board();
            newBoard.setName("Board_new");
            newBoard.setCreate_date(new DateTime().toDate());
            newBoard.setNotes(null);
        boardRepo.save(newBoard);
        return newBoard.toDto();
    }
}
