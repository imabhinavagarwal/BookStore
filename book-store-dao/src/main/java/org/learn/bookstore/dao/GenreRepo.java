package org.learn.bookstore.dao;

import org.learn.bookstore.commons.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {
}
