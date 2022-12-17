package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the head_lecturer database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="HeadLecturer.findAll", query="SELECT h FROM HeadLecturer h"),
	@NamedQuery(name="HeadLecturer.findHeadLecturerByPerson", query="SELECT h FROM HeadLecturer h WHERE h.person = :person"),
	@NamedQuery(name="HeadLecture.getHeadLecturerByPersonId", query="SELECT h FROM HeadLecturer h WHERE h.person.personId = :personId")
})
public class HeadLecturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="headlecturer_id")
	private String headlecturerId;

	@Column(name="major_id")
	private String majorId;

	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	public HeadLecturer() {
	}

	public String getHeadlecturerId() {
		return this.headlecturerId;
	}

	public void setHeadlecturerId(String headlecturerId) {
		this.headlecturerId = headlecturerId;
	}

	public String getMajorId() {
		return this.majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


}