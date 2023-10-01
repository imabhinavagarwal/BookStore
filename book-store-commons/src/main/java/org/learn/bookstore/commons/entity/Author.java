package org.learn.bookstore.commons.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.learn.bookstore.commons.utils.BookStoreUtils.getCurrentUTCLocalDateTime;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Entity
@Table(name = "author")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pen_name")
    private String penName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_demise")
    private LocalDate dateOfDemise;

    @Column(name = "nationality")
    private String nationality;

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
