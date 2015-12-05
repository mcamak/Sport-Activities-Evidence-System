package cz.muni.fi.pa165.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MajoCAM
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class UserDTO extends UserCreateDTO {

    Long id;
}
