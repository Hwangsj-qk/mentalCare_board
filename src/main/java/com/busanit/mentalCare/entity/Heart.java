package com.busanit.mentalCare.entity;


import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.FetchType.LAZY;

;

@Entity
@Data
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "heart_id")
    private Long heartId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Heart (User user, Board board) {
        this.user = user;
        this.board = board;
    }
}
