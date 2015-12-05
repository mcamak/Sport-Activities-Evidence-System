/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import java.util.HashSet;
import java.util.Set;
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
    private Long timeSeconds;

    @Min(0)
    private int distance;

    @NotNull
    private SportActivityDTO activity;

    private final Set<UserDTO> users = new HashSet<>();

}
