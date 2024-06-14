package com.busanit.mentalCare.model;

import jakarta.persistence.*;

@Entity
public class User {
    // 공감 버튼을 중복을 위한 유저 테이블 -> 나주에 테이블 합치기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;


}
