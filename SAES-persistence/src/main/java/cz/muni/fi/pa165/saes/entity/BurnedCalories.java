package cz.muni.fi.pa165.saes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @author Jan S.
 */
@Entity
@Getter
@Setter
public class BurnedCalories implements Comparable<BurnedCalories> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private SportActivity activity;
    private Integer bodyWeight;
    private Integer caloriesBurned;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.activity);
        hash = 59 * hash + Objects.hashCode(this.bodyWeight);
        hash = 59 * hash + Objects.hashCode(this.caloriesBurned);
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
        if (!Objects.equals(this.activity, other.getActivity())) {
            return false;
        }
        if (!Objects.equals(this.bodyWeight, other.getBodyWeight())) {
            return false;
        }
        if (!Objects.equals(this.caloriesBurned, other.getCaloriesBurned())) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(BurnedCalories o) {
        return this.activity.compareTo(o.getActivity()) != 0 ?
                this.activity.compareTo(o.getActivity()) :
                this.bodyWeight.compareTo(o.getBodyWeight());
    }
}
