package Board.user;

import Board.Comment.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;


    // user 테이블에 사용자가 좋아요를 누른 댓글들을 저장하는 likedComments
    // 좋아요 중복 방지를 위한 user와 comment 엔티티 간 다대다 관계 설정
    @ManyToMany(fetch = FetchType.EAGER)
    // 다대다 관계를 위한 연결 테이블 user_liked_comments : user_id와 comment_id를 가지고 있음
    @JoinTable(
            name = "user_liked_comments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")

    )
    // 설정한 해당 필드를 JSON 직렬화 대상에서 제외함으로써 무한한 순환 참조 문제 방지
    @JsonIgnore
    private Set<Comment> user_likedComments = new HashSet<>();
}
