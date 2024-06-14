package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.Content;
import com.busanit.mentalCare.model.TagType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

// DTO -> 데이터 전송객체
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    private Long content_id;
    private TagType content_tag;
    private String content_title;
    private String user_id;
    private Date content_date;
    private String content_detail;
    private List<CommentDTO> comments;

    // DTO -> Entity (엔티티에 @Builder 적용, 빌더 패턴 적용)
    public Content toEntity() {
        // DTO -> 엔티티 필드로 매핑
        Content content = Content.builder()
                .content_title(content_title)
                .content_detail(content_detail)
                .content_tag(content_tag)
                .user_id(user_id)
                .content_date(content_date)
                .content_detail(content_detail)
                .build();

        // DTO (댓글 리스트) -> 엔티티 (댓글 리스트)
        if(comments != null) {
            List<Comment> commentList = comments.stream().map(commentDTO ->
                    commentDTO.toEntity(content)).toList();
            content.setComments(commentList);
        }
        return content;
    }
}
