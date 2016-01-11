/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MajoCAM
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class UserLogInDTO {
    
    @NotNull
    @Size(min = 3, max = 30)
    private String username;
    
    @NotNull
    @Size(min = 6, max = 30)
    private String password;    
}
