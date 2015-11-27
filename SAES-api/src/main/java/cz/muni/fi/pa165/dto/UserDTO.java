package cz.muni.fi.pa165.dto;

import enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MajoCAM
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserDTO {
    
    Long id;    
    String name;    
    Gender sex;    
    Integer weight;    
    Integer age;
}
