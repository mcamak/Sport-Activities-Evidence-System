package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * @author MajoCAM
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    @Pattern(regexp = "[A-z0-9]{3,}")
    String username;

    @NotNull
    @Size(min = 3, max = 50)
    @Pattern(regexp = ".{3,}")
    String password;

    @NotNull
    Gender sex;

    @NotNull
    @Min(0)
    @Max(800)
    Integer weight;

    @NotNull
    @Min(0)
    @Max(150)
    Integer age;
}
