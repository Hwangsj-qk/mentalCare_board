package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Content;
import com.busanit.mentalCare.model.Content_heart;
import com.busanit.mentalCare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentHeartRepository extends JpaRepository<Content_heart, Long> {
    Optional<Content_heart> findByUserAndContent(User user, Content content);
}
