package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lecturer_committe database table.
 * 
 */
@Entity
@Table(name="lecturer_committe")
@NamedQuery(name="LecturerCommitte.findAll", query="SELECT l FROM LecturerCommitte l")
public class LecturerCommitte implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LecturerCommittePK id;

	//bi-directional many-to-one association to Committe
	@Id
	@ManyToOne
	@JoinColumn(name="committe_id")
	private Committe committe;

	public LecturerCommitte() {
	}

	public LecturerCommittePK getId() {
		return this.id;
	}

	public void setId(LecturerCommittePK id) {
		this.id = id;
	}

	public Committe getCommitte() {
		return this.committe;
	}

	public void setCommitte(Committe committe) {
		this.committe = committe;
	}

}