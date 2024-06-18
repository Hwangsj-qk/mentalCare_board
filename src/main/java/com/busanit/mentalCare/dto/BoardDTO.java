package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.entity.Board;
import com.busanit.mentalCare.entity.Comment;
import com.busanit.mentalCare.entity.User;
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
    private Long boardId;
    private TagType boardTag;
    private String boardTitle;
    private String boardTime;
    private String boardContent;
    private String userNickname;
    private int boardLikeCount;
    private List<CommentDTO> comments;


    // DTO -> Entity (엔티티에 @Builder 적용, 빌더 패턴 적용)
    public Board toEntity(User user) {
        // DTO -> 엔티티 필드로 매핑
        Board board = Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardTag(boardTag)
                .user(user)
                .boardTime(boardTime)
                .boardLikeCount(boardLikeCount)
                .build();

        // DTO (댓글 리스트) -> 엔티티 (댓글 리스트)
        if(comments != null) {
            List<Comment> commentList = comments.stream().map(commentDTO ->
                    commentDTO.toEntity(board, user)).toList();
            board.setComments(commentList);
        }
        return board;
    }
}
