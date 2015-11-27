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
public class BurnedCaloriesDTO {
	
    private Long id;
    private SportActivityDTO activity;    
    private int bodyWeight;    
    private int caloriesBurned;

    /**
     * Gets ID.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets activity.
     * 
     * @return the activity
     */
    public SportActivityDTO getActivity() {
        return activity;
    }

    /**
     * Gets body weight.
     * 
     * @return the bodyWeight
     */
    public int getBodyWeight() {
        return bodyWeight;
    }

    /**
     * Gets amount of burned calories.
     * 
     * @return the caloriesBurned
     */
    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    /**
     * Sets ID.
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets activity.
     * 
     * @param activity the activity to set
     */
    public void setActivity(SportActivityDTO activity) {
        this.activity = activity;
    }

    /**
     * Sets body weight.
     * 
     * @param bodyWeight the bodyWeight to set
     */
    public void setBodyWeight(int bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    /**
     * Sets the amount of burned calories.
     * 
     * @param caloriesBurned the caloriesBurned to set
     */
    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
