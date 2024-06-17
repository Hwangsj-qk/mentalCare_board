package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.entity.Board;
import com.busanit.mentalCare.entity.Comment;
import com.busanit.mentalCare.entity.TagType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// DTO -> 데이터 전송객체
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long board_id;
    private TagType board_tag;
    private String board_title;
    private Long user_id;
    private String board_date;
    private String board_content;
    private List<CommentDTO> comments;


    // DTO -> Entity (엔티티에 @Builder 적용, 빌더 패턴 적용)
    public Board toEntity() {
        // DTO -> 엔티티 필드로 매핑
        Board board = Board.builder()
                .board_title(board_title)
                .board_content(board_content)
                .board_tag(board_tag)
                .user_id(user_id)
                .board_date(board_date)
                .build();

        // DTO (댓글 리스트) -> 엔티티 (댓글 리스트)
        if(comments != null) {
            List<Comment> commentList = comments.stream().map(commentDTO ->
                    commentDTO.toEntity(board)).toList();
            board.setComments(commentList);
        }
        return board;
    }
}
