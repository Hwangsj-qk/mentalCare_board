package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.entity.Board;
import com.busanit.mentalCare.entity.Comment;
import com.busanit.mentalCare.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long commentId;
    private String commentContent;
    private LocalDateTime commentTime;
    private String userNickname;
    private Long boardId;



    public Comment toEntity(Board board, User user) {
        return Comment.builder()
                .commentId(commentId)
                .commentContent(commentContent)
                .commentTime(commentTime)
                .user(user)
                .board(board)
                .build();
    }

}
