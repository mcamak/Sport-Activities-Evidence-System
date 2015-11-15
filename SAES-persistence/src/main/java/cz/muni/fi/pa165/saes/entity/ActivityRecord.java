package cz.muni.fi.pa165.saes.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Marian Camak
 */
@Entity
public class ActivityRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timeSeconds;

    private int distance;

    @ManyToOne
    private SportActivity activity;

    @OneToMany
    private final Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Long getTimeSeconds() {
        return timeSeconds;
    }

    public void setTimeSeconds(Long timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public SportActivity getActivity() {
        return activity;
    }

    public void setActivity(SportActivity activity) {
        this.activity = activity;
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.timeSeconds);
        hash = 41 * hash + this.distance;
        hash = 41 * hash + Objects.hashCode(this.activity);
        hash = 41 * hash + Objects.hashCode(this.users);
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
        if (!Objects.equals(this.timeSeconds, other.getTimeSeconds())) {
            return false;
        }
        if (this.distance != other.getDistance()) {
            return false;
        }
        if (!Objects.equals(this.activity, other.getActivity())) {
            return false;
        }
        if (!Objects.equals(this.users, other.getUsers())) {
            return false;
        }
        return true;
    }
}
