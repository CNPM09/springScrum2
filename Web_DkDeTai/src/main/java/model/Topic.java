package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the topic database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t"),
	@NamedQuery(name="Topic.getTopicByConditionSelect", query="SELECT t FROM Topic t WHERE t.isSelected = :is_selected AND t.status = 1 AND t.isDeleted = 0"),
	@NamedQuery(name="Topic.findSelectedTopic", query="SELECT t FROM Topic t where t.isSelected = :isSelected"),
	@NamedQuery(name="Topic.findTopicBylecturer", query="SELECT t FROM Topic t where t.lecturer = :lecturer"),
	@NamedQuery(name="Topic.findTopicByLecturerAndStatus", query="SELECT t FROM Topic t where t.lecturer = :lecturer and t.status = 0"),
	@NamedQuery(name="Topic.findSpecifiedTopic", query="SELECT t FROM Topic t where t.lecturer = :lecturer and t.isSelected = :isSelected and t.status = 1"),
	@NamedQuery(name="Topic.findByStatusAndIsDeleted", query="SELECT t FROM Topic t WHERE t.status = :status AND t.isDeleted = :isDeleted")
})
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="is_full")
	private byte isFull;

	@Column(name="is_selected")
	private byte isSelected;

	@ManyToOne
	@JoinColumn(name="lecturer_id")
	private Lecturer lecturer;

	@Column(name="max_mo_member")
	private int maxMoMember;
	
	@Column(name="is_deleted")
	private byte isDeleted;


	private byte status;

	@Column(name="student_id")
	private String studentId;

	@Id
	@Column(name="topic_id")
	private String topicId;

	@Column(name="topic_name")
	private String topicName;

	@ManyToOne
	@JoinColumn(name="major_id")
	private Major major;
	
	@ManyToOne
	@JoinColumn(name="registration_period_id")
	private RegistrationPeriod registrationperiod;
	
	public Topic() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsFull() {
		return this.isFull;
	}

	public void setIsFull(byte isFull) {
		this.isFull = isFull;
	}

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
	}


	public int getMaxMoMember() {
		return this.maxMoMember;
	}

	public void setMaxMoMember(int maxMoMember) {
		this.maxMoMember = maxMoMember;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Lecturer getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	public byte getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(byte isSelected) {
		this.isSelected = isSelected;
	}

	public RegistrationPeriod getRegistrationperiod() {
		return this.registrationperiod;
	}

	public void setRegistrationperiod(RegistrationPeriod registrationperiod) {
		this.registrationperiod = registrationperiod;
	}
	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}


}