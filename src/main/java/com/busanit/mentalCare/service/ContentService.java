package com.busanit.mentalCare.service;

import com.busanit.mentalCare.repository.CommentRepository;
import com.busanit.mentalCare.repository.ContentRepository;
import com.busanit.mentalCare.dto.ContentDTO;
import jakarta.transaction.Transactional;
import com.busanit.mentalCare.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentRepository commentRepository;

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

    // 저자를 통해 게시글 찾기
    public List<ContentDTO> getContentByUser(String user_id) {
        List<Content> contentList = contentRepository.findByUser(user_id);
        return contentList.stream().map(Content::toDTO).toList();
    }

    // 포함된 글자를 통해 게시글 찾기
    public List<ContentDTO> getContentByDetailContaining(String content_detail) {
        List<Content> contentList = contentRepository.findByDetailContaining(content_detail);
        return contentList.stream().map(Content::toDTO).toList();
    }

    // 제목에 포함된 글자를 통해 게시글 찾기
    public List<ContentDTO> getContentByTitleContaining(String content_title) {
        List<Content> contentList = contentRepository.findByTitleContaining(content_title);
        return contentList.stream().map(Content::toDTO).toList();
    }

}
