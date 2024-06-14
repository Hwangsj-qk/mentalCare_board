package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.CommentDTO;
import com.busanit.mentalCare.dto.ContentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "content")
public class Content {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동 생성
    private Long content_id;

    @Column(name = "content_tag")
    private TagType content_tag;

    @Column(name = "content_title")
    private String content_title;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "content_date")
    private Date content_date;

    @Column(name = "content_detail")
    private String content_detail;

    // 1 대 다 관계 (content - comment) -> 양방향 관계가 아니면 굳이 필요 없음
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    // 해당 필드는 내일 DB에 추가하기
    @ColumnDefault("0")
    @Column(name = "viewCount", nullable = false)
    private Integer viewCount;

    public ContentDTO toDTO() {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if(comments != null) {
            commentDTOList = comments.stream().map(Comment::toDTO).toList();
        }
        return new ContentDTO(content_id, content_tag, content_title, user_id, content_date, content_detail, commentDTOList);
    }

    // 엔티티 -> DTO 변환 메서드
    public static Content createContent(ContentDTO dto) {
        Content content = new Content();
        content.setContent_id(dto.getContent_id());
        content.setContent_title(dto.getContent_title());
        content.setContent_detail(dto.getContent_detail());
        content.setContent_tag(dto.getContent_tag());
        content.setUser_id(dto.getUser_id());
        content.setContent_date(dto.getContent_date());
        return content;
    }


}
