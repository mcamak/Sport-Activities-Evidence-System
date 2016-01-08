package cz.muni.fi.pa165.dto;

import enums.Gender;
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
    @Pattern(regexp="\\([A-zÀ-ÿ]{3,}\\s\\?\\)+")
    String name;

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
