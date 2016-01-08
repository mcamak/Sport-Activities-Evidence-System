package cz.muni.fi.pa165.saes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class for representation of a sport activity.
 *
 * @author Tomas Effenberger
 */
@Entity
@Getter
@Setter
public class SportActivity implements Comparable<SportActivity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SportActivity)) {
            return false;
        }
        SportActivity other = (SportActivity) obj;
        if (!name.equals(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + name.hashCode();
        return hash;
    }

    @Override
    public int compareTo(SportActivity o) {
        return this.name.compareTo(o.getName());
    }
}
