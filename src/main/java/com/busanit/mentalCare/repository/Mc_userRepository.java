package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.entity.Mc_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Mc_userRepository extends JpaRepository<Mc_user, Long> {

    @Query("SELECT mc_u FROM Mc_user mc_u WHERE mc_u.user_id = :user_id")
    Mc_user findByUserId(String user_id);
}
