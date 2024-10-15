package gr.aueb.cf.teacherapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherInsertDTO {

    @NotNull(message = "first name can not be null.")
    @Size(min=2, message = "firstname must be at least 2")
    private String firstname;

    @NotNull(message = "last name can not be null.")
    @Size(min=2, message = "lastname must be at least 2")
    private String lastname;

    @Pattern(regexp = "\\d{9,}", message = "vat must be at least 9 char long")
    private String vat;

    @NotNull(message = "region must not be null")
    private Long regionId;

}
