package cz.muni.fi.pa165.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MajoCAM
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
public class BurnedCaloriesCreateDTO {

    @NotNull
    @Min(0)
    @Max(999)
    private Integer bodyWeight;

    @NotNull
    @Min(0)
    private Integer caloriesBurned;

    @NotNull
    private SportActivityDTO activity;
}
