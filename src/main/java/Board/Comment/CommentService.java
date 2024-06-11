package Board.Comment;

import Board.Content.Content;
import Board.Content.ContentDTO;
import Board.Content.ContentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ContentRepository contentRepository;

    public List<ContentDTO> getAllComments() {
        List<Content> contents = contentRepository.findAll();

        return contents.stream().map(Content::toDTO).toList();
    }

    // 조회(일부 게시글) -> 이 기능이 필요한 지 생각해보기
    public ContentDTO getContentById(Long content_id) {
        Content content = contentRepository.findById(content_id).orElse(null);
        // DTO로 변환
        if(content != null) {
            return content.toDTO();
        } else {
            return null;
        }
    }

    // 게시글 생성
    @Transactional
    public ContentDTO createContent(@RequestBody ContentDTO dto) {
        Content saved = contentRepository.save(dto.toEntity());
        return saved.toDTO();
    }

    // 게시글 수정 (제목과 게시글 내용만 변경 가능)
    @Transactional
    public ContentDTO updateContent(Long content_id, ContentDTO updateContent) {
        Content content = contentRepository.findById(content_id).orElse(null);
        if (content != null) {
            // 게시글 제목 변경
            if (content.getContent_title() != null) {
                content.setContent_title(updateContent.getContent_title());
            }
            // 게시글 내용 변경
            if (content.getContent_detail() != null) {
                content.setContent_detail(updateContent.getContent_detail());
            }
            // 글 작성자는 바꿀 수 없도록 함
            return contentRepository.save(content).toDTO();
        } else {
            return null;
        }
    }

    // 게시글 삭제
    @Transactional
    public Boolean DeleteContent(Long content_id) {
        Content content = contentRepository.findById(content_id).orElse(null);
        if(content != null) {
            contentRepository.delete(content);
            return true;
        } else {
            return false;
        }
    }
}
