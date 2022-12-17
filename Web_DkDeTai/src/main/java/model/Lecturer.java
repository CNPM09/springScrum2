package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the lecturer database table.
 * 
 */
@Entity
@Table(name="lecturer")
@NamedQueries({
	@NamedQuery(name="Lecturer.findAll", query="SELECT l FROM Lecturer l"),
	@NamedQuery(name="Lecturer.findLecturerByPerson", query="SELECT l FROM Lecturer l WHERE l.person = :person"),
	@NamedQuery(name="Lecturer.getLecturerByPersonId", query="SELECT l FROM Lecturer l WHERE l.person.personId = :personId")
})
public class Lecturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="is_head")
	private byte isHead;
	
	@Id
	@Column(name="lecturer_id")
	private String lecturerId;

	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;

	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	@OneToMany(mappedBy="lecturer")
	private List<Topic> listtopic;
	public Lecturer() {
	}

	public byte getIsHead() {
		return this.isHead;
	}

	public void setIsHead(byte isHead) {
		this.isHead = isHead;
	}

	public String getLecturerId() {
		return this.lecturerId;
	}

	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
	
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Topic> getTopics() {
		return this.listtopic;
	}

	public void setTopics(List<Topic> listtopic) {
		this.listtopic = listtopic;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setLecturer(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setLecturer(null);

		return topic;
	}

}