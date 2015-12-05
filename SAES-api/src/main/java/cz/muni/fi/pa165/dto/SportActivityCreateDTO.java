package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jan S.
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
public class SportActivityCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;
}
