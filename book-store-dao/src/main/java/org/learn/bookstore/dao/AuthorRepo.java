package org.learn.bookstore.dao;

import org.learn.bookstore.commons.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
