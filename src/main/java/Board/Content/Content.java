package Board.Content;

import Board.Comment.Comment;
import Board.Comment.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Content {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동 생성
    private Long content_id;

    private TagType content_tag;
    private String content_title;
    private String user_id;
    private Date content_date;
    private String content_detail;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

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
