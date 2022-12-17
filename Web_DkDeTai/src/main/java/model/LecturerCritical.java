package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lecturer_critical database table.
 * 
 */
@Entity
@Table(name="lecturer_critical")
@NamedQuery(name="LecturerCritical.findAll", query="SELECT l FROM LecturerCritical l")
public class LecturerCritical implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lecturer_id")
	private String lecturerId;

	@Column(name="person_id")
	private String personId;

	public LecturerCritical() {
	}

	public String getLecturerId() {
		return this.lecturerId;
	}

	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}