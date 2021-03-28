package ru.otus.books.repository;

import org.springframework.stereotype.Repository;
import ru.otus.books.exceptions.AuthorRepositoryException;
import ru.otus.books.model.Author;

import javax.persistence.*;
import java.util.List;

@Repository
public class DefaultAuthorRepository implements AuthorRepository {

    private static final String ID_FILED = "id";

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author getById(Long id) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id = :id", Author.class);
        query.setParameter(ID_FILED, id);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            throw new AuthorRepositoryException("error getting author by id " + id, ex);
        }
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author create(Author author) {
        try {
            em.persist(author);
            return author;
        } catch (PersistenceException ex) {
            throw new AuthorRepositoryException("error during author creating " + author, ex);
        }
    }

    @Override
    public int update(Long id, String firstName, String lastName) {
        try {
            Query query = em.createQuery("update Author a set a.firstName = :firstName, a.lastName = :lastName where a.id = :id");
            query.setParameter(ID_FILED, id);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            return query.executeUpdate();
        } catch (PersistenceException ex) {
            throw new AuthorRepositoryException("error during author updating ", ex);
        }
    }

    @Override
    public int deleteById(Long id) {
        try {
            Query query = em.createQuery("delete from Author a where a.id = :id");
            query.setParameter(ID_FILED, id);
            return query.executeUpdate();
        } catch (PersistenceException ex) {
            throw new AuthorRepositoryException("error during author deleting by id " + id, ex);
        }
    }
}
