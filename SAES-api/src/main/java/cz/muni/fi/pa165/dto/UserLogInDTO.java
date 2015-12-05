/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MajoCAM
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserLogInDTO {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Size(min = 6, max = 30)
    private String password;    
}
