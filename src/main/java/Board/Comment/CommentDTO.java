package Board.Comment;

import Board.Content.Content;
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
    private String comment_detail;
    private Date comment_date;


    public Comment toEntity(Content content) {
        return Comment.builder()
                .comment_id(comment_id)
                .user_id(user_id)
                .comment_detail(comment_detail)
                .comment_date(comment_date)
                .build();
    }
}
