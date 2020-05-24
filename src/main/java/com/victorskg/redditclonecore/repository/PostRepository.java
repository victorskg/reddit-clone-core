package com.victorskg.redditclonecore.repository;

import com.victorskg.redditclonecore.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
