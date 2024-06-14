package com.busanit.mentalCare.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentHeartDTO {
    private Long user_id;
    private Long content_id;

    // 공감을 누른 user_id와 content_id 전달
    public ContentHeartDTO(Long user_id, Long content_id) {
        this.user_id = user_id;
        this.content_id = content_id;
    }
}
