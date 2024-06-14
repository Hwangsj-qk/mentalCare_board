package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.ContentHeartDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.busanit.mentalCare.model.Content;
import com.busanit.mentalCare.model.Content_heart;
import com.busanit.mentalCare.model.User;
import org.springframework.stereotype.Service;
import com.busanit.mentalCare.repository.ContentHeartRepository;
import com.busanit.mentalCare.repository.ContentRepository;
import com.busanit.mentalCare.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ContentHeartService {

    // 전달받은 데이터(content_id, user_id)를 통해 각각 user와 content를 조회하고 저장 혹은 삭제 진행
    private final ContentHeartRepository contentHeartRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;



    // 공감 삽입
    @Transactional
    public void insert(ContentHeartDTO contentHeartDTO) throws Exception {
        User user = userRepository.findById(contentHeartDTO.getUser_id()).orElse(null);
        Content content = contentRepository.findById(contentHeartDTO.getContent_id()).orElse(null);

        // 이미 공감 버튼을 눌렀으면 에러 반환
        if (contentHeartRepository.findByUserAndContent(user, content).isPresent()) {
            throw new Exception();
        }

        Content_heart contentHeart = Content_heart.builder()
                .content(content)
                .user(user)
                .build();

        contentHeartRepository.save(contentHeart);



    }

    // 공감 삭제
    @Transactional
    public void delete(ContentHeartDTO contentHeartDTO) {
        User user = userRepository.findById(contentHeartDTO.getUser_id()).orElse(null);
        Content content = contentRepository.findById(contentHeartDTO.getContent_id()).orElse(null);
        Content_heart contentHeart = contentHeartRepository.findByUserAndContent(user, content).orElse(null);

        contentHeartRepository.delete(contentHeart);

    }


}
