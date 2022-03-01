package com.api;

import com.domain.Board;
import com.services.BoardService;
import com.shared.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable("id") Long id){
        Board boardObj = boardService.getBoard(id);
        return new ResponseEntity<>(boardObj,HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Board>> getAll(){
        return new ResponseEntity<>(boardService.getAllBoards(), HttpStatus.OK);
    }

    @GetMapping("/init")
    public ResponseEntity<BoardDto> init(){
        return new ResponseEntity<>(boardService.createBoard(), HttpStatus.OK);
    }
}
