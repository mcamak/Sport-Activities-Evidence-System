package cz.muni.fi.pa165.saes;

import enums.Gender;
import java.util.HashSet;
import java.util.Set;

/**
 * @author MajoCAM
 */
public class UserFilter {
    
    private Integer minAge;
    private Integer maxAge;
    private Integer minWeight;
    private Integer maxWeight;
    private final Set<Gender> genders = new HashSet<>();

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        if(minAge < 0 || minAge > 150) {
            throw new IllegalArgumentException("MinAge must be in range of 0-150. ");
        }
        if(maxAge != null && maxAge < minAge) {
            throw new IllegalArgumentException("MinAge must be less than maxAge. ");
        }
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        if(maxAge < 0 || maxAge > 150) {
            throw new IllegalArgumentException("MaxAge must be in range of 0-150. ");
        }
        if(minAge != null && minAge > maxAge) {
            throw new IllegalArgumentException("MaxAge must be higher than minAge. ");
        }
        this.maxAge = maxAge;
    }

    public Integer getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        if(minWeight < 0 || minWeight > 800) {
            throw new IllegalArgumentException("MaxWeight must be in range of 0-800. ");
        }
        if(maxWeight != null && maxWeight > minWeight) {
            throw new IllegalArgumentException("MaxWeight must be higher than minWeight. ");
        }
        this.minWeight = minWeight;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        if(maxWeight < 0 || maxWeight > 800) {
            throw new IllegalArgumentException("MaxWeight must be in range of 0-800. ");
        }
        if(minWeight != null && minWeight > maxWeight) {
            throw new IllegalArgumentException("MaxWeight must be higher than minWeight. ");
        }
        this.maxWeight = maxWeight;
    }

    public Set<Gender> getGenders() {
        return genders;
    }

    public void addGender(Gender gender) {
        this.genders.add(gender);
    }
    
    public void removeGender(Gender gender) {
        this.genders.remove(gender);
    }
}
