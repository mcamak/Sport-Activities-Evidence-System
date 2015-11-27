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
 * @author Jan S.
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(of = {"id"})
public class BurnedCaloriesDTO {

    private Long id;

    @Min(0)
    @Max(999)
    private int bodyWeight;
    
    @Min(0)
    private int caloriesBurned;
    
    @NotNull
    private SportActivityDTO activity;
}
