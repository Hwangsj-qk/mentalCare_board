package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.entity.ChildrenComment;
import com.busanit.mentalCare.entity.Comment;
import com.busanit.mentalCare.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenCommentDTO {
    private Long childrenId;
    private String childrenContent;
    private LocalDateTime childrenTime;
    private String userNickname;
    private Long commentId;



    public ChildrenComment toEntity(Comment comment, User user) {
        return ChildrenComment.builder()
                .childrenId(childrenId)
                .childrenContent(childrenContent)
                .childrenTime(childrenTime)
                .user(user)
                .comment(comment)
                .build();
    }
}
