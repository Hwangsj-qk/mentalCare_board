package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.service.BoardService;
import com.busanit.mentalCare.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // Insert (게시글 생성)
    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO board) {
        BoardDTO createdBoard = boardService.createBoard(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }


    // select (게시글 검색) - 커스텀 메서드
    @GetMapping("user_id/{user_id}")
    public List<BoardDTO> getContentByUserId(@PathVariable Long user_id) {
        return boardService.getBoardByUserId(user_id);
    }

    @GetMapping("board_content/{board_content}")
    public List<BoardDTO> getBoardByContentContaining(@PathVariable String board_content) {
        return boardService.getBoardByContentContaining(board_content);
    }

    @GetMapping("board_title/{board_title}")
    public List<BoardDTO> getBoardByTitleContaining(@PathVariable String board_title) {
        return boardService.getBoardByTitleContaining(board_title);
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }


    // update (게시글 수정)
    @PutMapping("/{board_id}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long board_id, @RequestBody BoardDTO updateBoard) {
        BoardDTO board = boardService.updateBoard(board_id, updateBoard);

        if(board == null) {
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok().build();
    }

    // DELETE (게시글 삭제)
    @DeleteMapping("/{board_id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long board_id) {
        if(!boardService.DeleteBoard(board_id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }





//    // 공감에 대한 기능 (공감 삽입/ 공감 취소)
//    @GetMapping("/{board_heart}")
//    public List<BoardDTO> updateHeartCount(@PathVariable String board_id, boolean b ) {
//        return boardService.updateCountJPQL(board_id, b);
//    }

}
