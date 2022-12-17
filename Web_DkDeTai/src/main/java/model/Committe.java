package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the committe database table.
 * 
 */
@Entity
@NamedQuery(name="Committe.findAll", query="SELECT c FROM Committe c")
public class Committe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="committe_id")
	private String committeId;

	@Column(name="committe_name")
	private String committeName;

	private String description;

	@Column(name="num_member")
	private int numMember;

	//bi-directional many-to-one association to LecturerCommitte
	@OneToMany(mappedBy="committe")
	private List<LecturerCommitte> lecturerCommittes;

	public Committe() {
	}

	public String getCommitteId() {
		return this.committeId;
	}

	public void setCommitteId(String committeId) {
		this.committeId = committeId;
	}

	public String getCommitteName() {
		return this.committeName;
	}

	public void setCommitteName(String committeName) {
		this.committeName = committeName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumMember() {
		return this.numMember;
	}

	public void setNumMember(int numMember) {
		this.numMember = numMember;
	}

	public List<LecturerCommitte> getLecturerCommittes() {
		return this.lecturerCommittes;
	}

	public void setLecturerCommittes(List<LecturerCommitte> lecturerCommittes) {
		this.lecturerCommittes = lecturerCommittes;
	}

	public LecturerCommitte addLecturerCommitte(LecturerCommitte lecturerCommitte) {
		getLecturerCommittes().add(lecturerCommitte);
		lecturerCommitte.setCommitte(this);

		return lecturerCommitte;
	}

	public LecturerCommitte removeLecturerCommitte(LecturerCommitte lecturerCommitte) {
		getLecturerCommittes().remove(lecturerCommitte);
		lecturerCommitte.setCommitte(null);

		return lecturerCommitte;
	}

}