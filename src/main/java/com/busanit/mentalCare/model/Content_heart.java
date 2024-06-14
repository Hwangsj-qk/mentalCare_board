package com.busanit.mentalCare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// 공감에 대한 정보를 저장하는 엔티티
public class Content_heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "heart_id")
    private Long heart_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @Builder
    public Content_heart(User user, Content content) {
        this.user = user;
        this.content =content;
    }

}
