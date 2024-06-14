package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    // 커스텀 메서드 - 글쓴이를 통해 게시글 찾기
    List<Content> findByUser(String user_id);

    // 커스텀 메서드 - 게시글 내용에 포함된 글자에서 게시글 찾기
    List<Content> findByDetailContaining(String content_detail);

    // 커스텀 메서드 - 게시글 제목에 포함된 글자에서 게시글 찾기
    List<Content> findByTitleContaining(String content_title);






}
