package it.aulab.selfworkspringweb;

import it.aulab.selfworkspringweb.models.Author;
import it.aulab.selfworkspringweb.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SelfworkSpringWebApplicationTests {

    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void load() {
        Author a1 = new Author();
        a1.setName("Alex");
        a1.setSurname("Rossi");
        a1.setEmail("RossiA@test.it");
        authorRepository.save(a1);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void findByName() {
        assertThat(authorRepository.findByName("Alex")).extracting("name").containsOnly("Alex");
    }

    @Test
    void sameNameAuthor() {
        assertThat(authorRepository.authorWithSameName()).extracting("name").containsOnly("Alex");
    }

    @Test
    void sameNameAuthorNonNative() {
        assertThat(authorRepository.authorWithSameNameNonNative()).extracting("name").containsOnly("Alex");
    }
}