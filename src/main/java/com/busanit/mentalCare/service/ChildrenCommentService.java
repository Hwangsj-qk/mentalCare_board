package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.ChildrenCommentDTO;
import com.busanit.mentalCare.entity.ChildrenComment;
import com.busanit.mentalCare.entity.Comment;
import com.busanit.mentalCare.entity.User;
import com.busanit.mentalCare.repository.BoardRepository;
import com.busanit.mentalCare.repository.ChildrenCommentRepository;
import com.busanit.mentalCare.repository.CommentRepository;
import com.busanit.mentalCare.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildrenCommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChildrenCommentRepository childrenRepository;



    // 엔티티 -> DTO로 변환하여 전달
    public List<ChildrenCommentDTO> getAllChildren() {
        List<ChildrenComment> children = childrenRepository.findAll();
        return children.stream().map(ChildrenComment::toDTO).toList();
    }

    @Transactional
    public List<ChildrenCommentDTO> getChildrenByCommentId(Long commentId) {
        List<ChildrenComment> childrenList = childrenRepository.findByCommentCommentId(commentId);
        return childrenList.stream().map(ChildrenComment::toDTO).toList();
    }

    @Transactional
    public ChildrenCommentDTO createChildren(ChildrenCommentDTO dto) {
        User user = userRepository.findByUserNickname(dto.getUserNickname());
        Comment comment = commentRepository.findById(dto.getCommentId()).orElse(null);
        if(comment == null) {
            throw new RuntimeException("댓글이 존재하지 않는 답글");
        }

        ChildrenComment children = dto.toEntity(comment, user);
        int boardCommentCount = children.getComment().getBoard().getBoardCommentCount();
        children.getComment().getBoard().setBoardCommentCount(boardCommentCount + 1);
        ChildrenComment saved = childrenRepository.save(children);
        return saved.toDTO();
    }

    @Transactional
    public ChildrenCommentDTO updateChildren(Long childrenId, ChildrenCommentDTO updateChildren) {
        ChildrenComment children = childrenRepository.findById(childrenId).orElse(null);

        if(children != null) {
            if(updateChildren.getChildrenContent() != null) {
                children.setChildrenContent(updateChildren.getChildrenContent());
            }

            ChildrenComment saved = childrenRepository.save(children);
            return saved.toDTO();
        } else {
            return null;
        }
    }

    @Transactional
    public ChildrenCommentDTO deleteChildren(Long childrenId) {
        ChildrenComment children = childrenRepository.findById(childrenId).orElse(null);
        if(children != null) {
            childrenRepository.delete(children);
            int boardCommentCount = children.getComment().getBoard().getBoardCommentCount();
            children.getComment().getBoard().setBoardCommentCount(boardCommentCount - 1);
            return children.toDTO();
        } else {
            return null;
        }
    }

}
