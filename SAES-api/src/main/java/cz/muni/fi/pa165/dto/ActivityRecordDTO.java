package cz.muni.fi.pa165.dto;

/**
 *
 * @author Barborka
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(callSuper = false, of = {"id"})
public class ActivityRecordDTO extends ActivityRecordCreateDTO {

    private Long id;
    private Integer burnedCalories;
}
