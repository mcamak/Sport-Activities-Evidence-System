/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;

/**
 *
 * @author Jan S.
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(callSuper = false, of = {"id"})
public class BurnedCaloriesDTO extends BurnedCaloriesCreateDTO {

    private Long id;
}
