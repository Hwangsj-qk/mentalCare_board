package Board.Comment;

import Board.Content.Content;
import Board.Content.ContentRepository;
import Board.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    // 엔티티 -> DTO로 변환하여 전달
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(Comment::toDTO).toList();
    }

    public CommentDTO getCommentById(Long comment_id) {
        Comment comment = commentRepository.findById(comment_id).orElse(null);
        return comment.toDTO();
    }

    @Transactional
    public CommentDTO createComment(CommentDTO dto) {
        Content content = contentRepository.findById(dto.getContent_id()).orElse(null);
        if(content == null) {
            throw new RuntimeException("존재하지 않은 Content");
        }

        Comment comment = dto.toEntity(content);

        Comment saved = commentRepository.save(comment);
        return saved.toDTO();
    }

    @Transactional
    public CommentDTO updateComment(Long comment_id, CommentDTO updateComment) {
        Comment comment = commentRepository.findById(comment_id).orElse(null);

        if(comment != null) {
            if(updateComment.getComment_detail() != null) {
                comment.setComment_detail(updateComment.getComment_detail());
            }

            Comment saved = commentRepository.save(comment);
            return saved.toDTO();
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean deleteComment(Long comment_id) {
        Comment comment = commentRepository.findById(comment_id).orElse(null);
        if(comment != null) {
            commentRepository.delete(comment);
            return true;
        } else {
            return false;
        }
    }


}
