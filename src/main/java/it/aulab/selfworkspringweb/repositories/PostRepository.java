package it.aulab.selfworkspringweb.repositories;

import it.aulab.selfworkspringweb.models.Author;
import it.aulab.selfworkspringweb.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PostRepository extends ListCrudRepository<Post, Long> {
    List<Post> findByTitle(String title);

    List<Post> findByAuthor(Author author);

    List<Post> findByAuthorName(String name);

    @Query(value = "SELECT * FROM posts p WHERE p.title = 'Primo post'", nativeQuery = true)
    List<Post> postWithSameTitle();

    @Query("SELECT p FROM Post p WHERE p.title = 'Primo post'")
    List<Post> postWithSameTitleNonNative();
}