package com.victorskg.redditclonecore.repository;

import com.victorskg.redditclonecore.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
