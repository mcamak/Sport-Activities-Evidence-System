package cz.muni.fi.pa165.dto;

/**
 * @author MajoCAM
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(callSuper = false, of = {"id"})
public class UserDTO extends UserCreateDTO {

    Long id;
}
