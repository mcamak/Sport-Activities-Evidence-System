package cz.muni.fi.pa165.saes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @author Marian Camak
 */
@Entity
@Getter
@Setter
public class ActivityRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private SportActivity activity;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Long timeSeconds;
    private Integer distance;
    private Integer burnedCalories;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + ((activity == null) ? 0 : activity.hashCode());
        hash = 41 * hash + ((user == null) ? 0 : user.hashCode());
        hash = 41 * hash + Objects.hashCode(this.timeSeconds);
        hash = 41 * hash + Objects.hashCode(this.distance);
        hash = 41 * hash + Objects.hashCode(this.burnedCalories);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ActivityRecord)) {
            return false;
        }
        final ActivityRecord other = (ActivityRecord) obj;
        if (!Objects.equals(this.activity, other.getActivity())) {
            return false;
        }
        if (!Objects.equals(this.user, other.getUser())) {
            return false;
        }
        if (!Objects.equals(this.timeSeconds, other.getTimeSeconds())) {
            return false;
        }
        if (!Objects.equals(this.distance, other.getDistance())) {
            return false;
        }
        if (!Objects.equals(this.burnedCalories, other.getBurnedCalories())) {
            return false;
        }
        return true;
    }
}
