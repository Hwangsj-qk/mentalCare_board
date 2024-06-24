package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.entity.ChildrenComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenCommentRepository extends JpaRepository<ChildrenComment, Long> {
}
