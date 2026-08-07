package it.aulab.selfworkspringweb.repositories;

import it.aulab.selfworkspringweb.models.Comment;
import it.aulab.selfworkspringweb.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByEmail(String email);

    List<Comment> findByPost(Post post);

    List<Comment> findByPostId(Long postId);

    @Query(value = "SELECT * FROM comments c WHERE c.email = 'commentatore@test.it'", nativeQuery = true)
    List<Comment> commentWithSameEmail();

    @Query("SELECT c FROM Comment c WHERE c.email = 'commentatore@test.it'")
    List<Comment> commentWithSameEmailNonNative();
}