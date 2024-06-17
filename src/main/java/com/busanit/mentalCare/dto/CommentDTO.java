package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.entity.Board;
import com.busanit.mentalCare.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long comment_id;
    private String comment_content;
    private String comment_date;
    private Long user_id;
    private Long board_id;



    public Comment toEntity(Board board) {
        return Comment.builder()
                .comment_id(comment_id)
                .comment_content(comment_content)
                .comment_time(comment_date)
                .user_id(user_id)
                .board(board)
                .build();
    }

}
