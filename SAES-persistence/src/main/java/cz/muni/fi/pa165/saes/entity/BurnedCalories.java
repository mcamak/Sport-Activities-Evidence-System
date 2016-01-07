package cz.muni.fi.pa165.saes.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @author Jan S.
 */
@Entity
@Table(name = "BurnedCalories")
public class BurnedCalories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SportActivity activity;
    private int bodyWeight;
    private int caloriesBurned;

    public Long getId() {
        return id;
    }

    public SportActivity getActivity() {
        return activity;
    }

    public void setActivity(SportActivity activity) {
        this.activity = activity;
    }

    public int getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(int bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.activity);
        hash = 59 * hash + this.bodyWeight;
        hash = 59 * hash + this.caloriesBurned;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BurnedCalories)) {
            return false;
        }
        final BurnedCalories other = (BurnedCalories) obj;
        if (!Objects.equals(this.activity, other.activity)) {
            return false;
        }
        if (this.bodyWeight != other.bodyWeight) {
            return false;
        }
        if (this.caloriesBurned != other.caloriesBurned) {
            return false;
        }
        return true;
    }
}
