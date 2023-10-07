package org.learn.bookstore.commons.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.learn.bookstore.commons.utils.BookStoreUtils.getCurrentUTCLocalDateTime;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Entity
@Table(name = "book")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "date_of_publication")
    private LocalDate dateOfPublication;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastModifiedAt;

    @PrePersist
    private void onInsert() {
        if (createdAt == null) {
            LocalDateTime currentUTCLocalDateTime = getCurrentUTCLocalDateTime();
            createdAt = currentUTCLocalDateTime;
            lastModifiedAt = currentUTCLocalDateTime;
        }
    }

    @PreUpdate
    private void onUpdate() {
        if (lastModifiedAt == null) {
            lastModifiedAt = getCurrentUTCLocalDateTime();
        }
    }
}
