package com.victorskg.redditclonecore.repository;

import com.victorskg.redditclonecore.model.Comment;
import com.victorskg.redditclonecore.model.Post;
import com.victorskg.redditclonecore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);

}
