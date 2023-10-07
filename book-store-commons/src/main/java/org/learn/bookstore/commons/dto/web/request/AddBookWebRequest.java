package org.learn.bookstore.commons.dto.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddBookWebRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long authorId;

    private String genre;
    private LocalDate dateOfPublication;
}
