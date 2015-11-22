package cz.muni.fi.pa165.dto;

import enums.Gender;
import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author MajoCAM
 */
public class UserCreateDTO {

    @NotNull
    @Size(min = 3, max = 50)
    String name;

    @NotNull
    Gender sex;

    @Min(0)
    @Max(800)
    Integer weight;

    @Min(0)
    @Max(150)
    Integer age;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
