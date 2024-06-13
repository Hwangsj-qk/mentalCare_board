package Board.Comment;

import Board.Content.Content;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    private Long content_id;
    private String user_id;
    private String comment_detail;
    private Date comment_date;

    // 1 대 다 관계
    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    // 엔티티 -> DTO 변환 메서드
    public CommentDTO toDTO() {
        // 댓글에 게시글 ID가 없는 경우
        Long contentId = 0L;
        if(content != null) {
            contentId = content.getContent_id();
        }
        return new CommentDTO(comment_id, user_id, content_id, comment_detail, comment_date);
    }

    public static Comment createComment(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setComment_id(dto.getComment_id());
        comment.setUser_id(dto.getUser_id());

        Content content = new Content();
        content.setUser_id(dto.getUser_id());

        return comment;
    }

}
