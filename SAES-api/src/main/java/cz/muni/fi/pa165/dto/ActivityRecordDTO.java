/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.dto;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Barborka
 */
public class ActivityRecordDTO {
    
    private Long id;

    private Long timeSeconds;

    private int distance;

    @ManyToOne
    private SportActivityDTO activity;

    @OneToMany
    private final Set<UserDTO> users = new HashSet<>();

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

    public SportActivityDTO getActivity() {
        return activity;
    }

    public void setActivity(SportActivityDTO activity) {
        this.activity = activity;
    }

    public Set<UserDTO> getUsers() {
        return Collections.unmodifiableSet(users);
    }

    public void addUser(UserDTO user) {
        this.users.add(user);
    }

    public void removeUser(UserDTO user) {
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
        if (!(obj instanceof ActivityRecordDTO)) {
            return false;
        }
        final ActivityRecordDTO other = (ActivityRecordDTO) obj;
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
