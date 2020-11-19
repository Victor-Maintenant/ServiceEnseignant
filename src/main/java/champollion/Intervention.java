package champollion;

import java.util.Date;

public class Intervention {
	private UE matiere;
	private Date debut;
	private int duree;
	private Salle lieu;
	private TypeIntervention type;
	
	public TypeIntervention getType() {
		return type;
	}

	public void setType(TypeIntervention type) {
		this.type = type;
	}

	public Intervention(UE ue, Date debut, int duree) {
		this.matiere = ue;
		this.debut = debut;
		this.duree = duree;
	}

	public UE getMatiere() {
		return matiere;
	}

	public Date getDebut() {
		return debut;
	}

	public int getDuree() {
		return duree;
	}

	public Salle getLieu() {
		return lieu;
	}

	
}
