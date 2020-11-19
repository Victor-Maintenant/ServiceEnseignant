package champollion;

import java.util.LinkedList;
import java.util.List;

public class Salle {
	private String intitule;
	private int capacite;
	private List<Intervention> occupations = new LinkedList<>();
	
	public Salle(String intitule, int capacite) {
		this.intitule = intitule;
		this.capacite = capacite;
	}

	public String getIntitule() {
		return intitule;
	}

	public int getCapacite() {
		return capacite;
	}

	public List<Intervention> getOccupations() {
		return occupations;
	}

	public void addOccupations() {
		Intervention i = new Intervention()
		this.occupations.add(i);
	}
	
	
}
