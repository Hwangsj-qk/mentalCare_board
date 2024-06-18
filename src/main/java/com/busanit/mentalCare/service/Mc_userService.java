package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.Mc_userDto;
import com.busanit.mentalCare.entity.Mc_user;
import com.busanit.mentalCare.repository.Mc_userRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// 스프링 시큐리티의 클래스를 상속받지 않는 UserService
@Service
public class Mc_userService {

    @Autowired
    private Mc_userRepository userRepository;



    // Entity -> DTO로 변환하여 전달
    public List<Mc_userDto> getAllUsers() {
        List<Mc_user> users = userRepository.findAll();
        return users.stream().map(Mc_user::toDto).toList();
    }

    // 조회 (일부 기사)
    public Mc_userDto getUserById(String user_id) {
        Mc_user user = userRepository.findByUserId(user_id);
        return user.toDto();
    }

    // 생성
    @Transactional
    public Mc_userDto createUser(@RequestBody Mc_userDto dto) {
        // Article ID가 존재하지 않는경우
        Mc_user user = userRepository.findByUserId(dto.getUser_id());
        if(user == null) {
            throw new RuntimeException("존재하지 않는 유저");
        }

        // DTO -> 엔티티 변환 (양자 택일)
        // 1. 생성 메서드 사용 DTO -> 엔티티 반환
        //Comment comment = Comment.createComment(dto);
        // 2. toEntity 사용 변환


        Mc_user saved = userRepository.save(user);
        return saved.toDto();
    }

    // 업데이트
    @Transactional
    public Mc_userDto updateUser(String user_id, Mc_user updateUser) {
        Mc_user user = userRepository.findByUserId(user_id);

        if(user != null) {
            if(updateUser.getUser_id() != null) {
                user.setUser_id(updateUser.getUser_id());
            }
            if(updateUser.getUser_pw() != null) {
                user.setUser_pw(updateUser.getUser_pw());
            }
            // 댓글의 게시글까지 변경하고 싶은 경우(로직 추가)
            Mc_user saved = userRepository.save(user);
            return saved.toDto();
        } else {
            return null;
        }
    }

    // 삭제
    @Transactional
    public Boolean deleteUser(String user_id) {
        Mc_user user = userRepository.findByUserId(user_id);
        if(user != null) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }
}