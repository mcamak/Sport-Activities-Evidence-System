/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @Min(0)
    @Max(999)
    private int bodyWeight;

    @NotNull
    @Min(0)
    private int caloriesBurned;

    @NotNull
    private SportActivityDTO activity;
}
