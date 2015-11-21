package cz.muni.fi.pa165.dto;

import enums.Gender;
import java.util.Objects;

/**
 * @author MajoCAM
 */
public class UserCreateDTO {
    
    String name;
    Gender sex;
    int weight;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.sex);
        hash = 59 * hash + this.weight;
        hash = 59 * hash + this.age;
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
        final UserCreateDTO other = (UserCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        return true;
    }
}
