package champollion;

public class ServicePrevu {
	
	private int volumeCM;
	private int volumeTD;
	private int volumeTP;
	private UE enseignement;

	public ServicePrevu(UE ue, int volumeCM, int volumeTD, int volumeTP) {
		this.volumeCM = volumeCM;
		this.volumeTD = volumeTD;
		this.volumeTP = volumeTP;
		this.enseignement = ue;
	}

	public int getVolumeCM() {
		return volumeCM;
	}

	public int getVolumeTD() {
		return volumeTD;
	}

	public int getVolumeTP() {
		return volumeTP;
	}

	public UE getEnseignement() {
		return enseignement;
	}
	
	
}
