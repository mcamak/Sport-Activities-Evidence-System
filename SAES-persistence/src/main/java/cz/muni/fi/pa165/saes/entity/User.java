package cz.muni.fi.pa165.saes.entity;

import cz.muni.fi.pa165.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 *
 * @author Barbora B. Entity of user - name, age, weight and sex
 */
@Entity
@Getter
@Setter
@Table(name = "users") // user is a reserved word, have to change the table name
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender sex;

    private Integer age;
    private Integer weight;
    private boolean admin;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.sex);
        hash = 97 * hash + Objects.hashCode(this.age);
        hash = 97 * hash + Objects.hashCode(this.weight);
        hash = 97 * hash + Objects.hashCode(this.admin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.getUsername())) {
            return false;
        }
        if (this.sex != other.getSex()) {
            return false;
        }
        if (!Objects.equals(this.age, other.getAge())) {
            return false;
        }
        if (!Objects.equals(this.weight, other.getWeight())) {
            return false;
        }
        if (this.admin != other.isAdmin()) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(User o) {
        return this.username.compareTo(o.getUsername());
    }
}
