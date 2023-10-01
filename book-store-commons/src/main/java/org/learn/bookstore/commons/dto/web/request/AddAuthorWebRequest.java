package org.learn.bookstore.commons.dto.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
public class AddAuthorWebRequest {

    @NotBlank
    private String firstName;

    private String lastName;
    private String penName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDemise;
    private String nationality;
}
