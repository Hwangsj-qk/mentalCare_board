package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long comment_id;
    private String user_id;
    private Long content_id;

    private String comment_detail;
    private Date comment_date;




    public Comment toEntity(Content content) {
        return Comment.builder()
                .comment_id(comment_id)
                .comment_detail(comment_detail)
                .user_id(user_id)
                .content_id(content_id)
                .comment_date(comment_date)
                .build();
    }
}
