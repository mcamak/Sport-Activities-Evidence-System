/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class BurnedCaloriesCreateDTO {
    
    @NotNull
    private SportActivityDTO activity;  
    
    @Min(0)
    @Max(800)
    private int bodyWeight;   
    
    @NotNull
    @Min(0)    
    private int caloriesBurned;
}
