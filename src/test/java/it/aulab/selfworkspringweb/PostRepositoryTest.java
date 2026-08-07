package it.aulab.selfworkspringweb;

import it.aulab.selfworkspringweb.models.Author;
import it.aulab.selfworkspringweb.models.Post;
import it.aulab.selfworkspringweb.repositories.AuthorRepository;
import it.aulab.selfworkspringweb.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PostRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PostRepository postRepository;

    Author author;
    Post post;

    @BeforeEach
    void load() {
        author = new Author();
        author.setName("Luca");
        author.setSurname("Bianchi");
        author.setEmail("bianchiL@test.it");
        authorRepository.save(author);

        post = new Post();
        post.setTitle("Primo post");
        post.setBody("Questo è il corpo del primo post");
        post.setPublishDate("20250101");
        post.setAuthor(author);
        postRepository.save(post);
    }

    @Test
    void findByTitle() {
        assertThat(postRepository.findByTitle("Primo post")).extracting("title").containsOnly("Primo post");
    }

    @Test
    void findByAuthor() {
        assertThat(postRepository.findByAuthor(author)).extracting("title").containsOnly("Primo post");
    }

    @Test
    void findByAuthorName() {
        assertThat(postRepository.findByAuthorName("Luca")).extracting("title").containsOnly("Primo post");
    }

    @Test
    void postWithSameTitle() {
        assertThat(postRepository.postWithSameTitle()).extracting("title").containsOnly("Primo post");
    }

    @Test
    void postWithSameTitleNonNative() {
        assertThat(postRepository.postWithSameTitleNonNative()).extracting("title").containsOnly("Primo post");
    }
}