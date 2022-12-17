package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the lecturer_committe database table.
 * 
 */
@Embeddable
public class LecturerCommittePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="lecturer_id")
	private String lecturerId;

	@Column(name="committe_id", insertable=false, updatable=false)
	private String committeId;

	public LecturerCommittePK() {
	}
	public String getLecturerId() {
		return this.lecturerId;
	}
	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}
	public String getCommitteId() {
		return this.committeId;
	}
	public void setCommitteId(String committeId) {
		this.committeId = committeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LecturerCommittePK)) {
			return false;
		}
		LecturerCommittePK castOther = (LecturerCommittePK)other;
		return 
			this.lecturerId.equals(castOther.lecturerId)
			&& this.committeId.equals(castOther.committeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lecturerId.hashCode();
		hash = hash * prime + this.committeId.hashCode();
		
		return hash;
	}
}