package com.busanit.mentalCare.entity;

import com.busanit.mentalCare.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long comment_id;

    //@ManyToOne
    // @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "comment_content")
    private String comment_content;

    @Column(name = "comment_time")
    private String comment_time;

    // 다 대 1 관계
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

//    // 대댓글에 사용할 삭제 유무 확인 (true = 삭제)
//    @ColumnDefault("FALSE")
//    @Column(nullable = false)
//    private Boolean isDeleted;
//
//    // comment에 parent 추가해야 할 상황일 듯...?
//    // 부모 댓글 -> null일 경우 최상위 댓글
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "parent_id")
//    private Comment parentComment;

//    // 자식 댓글 -> 대댓글
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<Comment> childrenComment = new ArrayList<>();

    // 엔티티 -> DTO 변환 메서드 (user에 대해서 선생님께 여쭤보기)
    public CommentDTO toDTO() {
        // 댓글에 게시글 ID가 없는 경우
        Long boardId = 0L;
        if(board != null) {
            boardId = board.getBoard_id();
        }

        return new CommentDTO(comment_id, comment_content, comment_time,user_id, boardId);

    }

    // DTO -> 엔티티로 변환
//    public static Comment createComment(CommentDTO dto) {
//        Comment comment = new Comment();
//
//        comment.setComment_id(dto.getComment_id());
//        comment.setUser_id(dto.getUser_id());
//
//        Board board = new Board();
//
//        return comment;
//    }



}