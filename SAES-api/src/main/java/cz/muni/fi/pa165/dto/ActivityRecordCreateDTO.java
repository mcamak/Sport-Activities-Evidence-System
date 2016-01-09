/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan S.
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
public class ActivityRecordCreateDTO {

    @NotNull
    @Min(0)
    private Long time;

    @Min(0)
    private Integer distance;

    @NotNull
    private SportActivityDTO activity;

    @NotNull
    private UserDTO user;

}
