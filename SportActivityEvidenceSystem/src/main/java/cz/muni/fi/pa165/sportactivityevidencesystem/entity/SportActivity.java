package cz.muni.fi.pa165.sportactivityevidencesystem.entity;

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
public class SportActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

}
