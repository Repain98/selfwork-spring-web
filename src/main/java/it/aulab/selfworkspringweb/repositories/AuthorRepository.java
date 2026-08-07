package it.aulab.selfworkspringweb.repositories;

import it.aulab.selfworkspringweb.models.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByName(String firstName);

    List<Author> findBySurname(String lastname);

    List<Author> findByNameAndSurname(String firstName, String lastName);

    @Query(value = "SELECT * FROM authors a WHERE a.firstname = 'Alex'", nativeQuery = true)
    List<Author> authorWithSameName();

    @Query("SELECT a FROM Author a WHERE a.name = 'Alex'")
    List<Author> authorWithSameNameNonNative();
}