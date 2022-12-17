package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the major database table.
 * 
 */
@Entity
@NamedQuery(name="Major.findAll", query="SELECT m FROM Major m")
public class Major implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;
	
	@Id
	@Column(name="major_id")
	private String majorId;

	@Column(name="major_name")
	private String majorName;

	public Major() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMajorId() {
		return this.majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

}