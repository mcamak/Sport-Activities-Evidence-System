package cz.muni.fi.pa165.dto;

/**
 *
 * @author Jan S.
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(callSuper = false, of = {"id"})
public class BurnedCaloriesDTO extends BurnedCaloriesCreateDTO {

    private Long id;
}
