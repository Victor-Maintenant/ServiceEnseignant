package champollion;

import java.util.LinkedList;
import java.util.List;

public class Enseignant extends Personne {

    private List<ServicePrevu> servicePrevus = new LinkedList<>(); 
    private List<Intervention> interventions = new LinkedList<>(); 

	
    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    public List<ServicePrevu> getServicePrevus() {
		return servicePrevus;
	}

	public List<Intervention> getInterventions() {
		return interventions;
	}

	/**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
    	int res = 0;
        for (ServicePrevu sp : this.servicePrevus) {
        	res += (sp.getVolumeCM()*1.5) + (sp.getVolumeTD()) + (sp.getVolumeTP()*0.75);
        }
        return res;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
    	int res = 0;
        for (ServicePrevu sp : this.servicePrevus) {
        	if (sp.getEnseignement().getIntitule() == ue.getIntitule()) {
            	res += (sp.getVolumeCM()*1.5) + (sp.getVolumeTD()) + (sp.getVolumeTP()*0.75);
        	}
        }
        return res;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        ServicePrevu sp = new ServicePrevu(ue,volumeCM,volumeTD,volumeTP);
        this.servicePrevus.add(sp);
    }
    
    /**
     * Ajoute un enseignement cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteIntervention(Intervention i) {
        this.interventions.add(i);
    }

    
    /**
     * Calcule le nombre total d'heures réalisées pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" réalisées pour cet enseignant, arrondi à l'entier le plus proche
     * @throws Exception 
     *
     */
    public int heuresPlanifiees() throws Exception {
    	int res = 0;
    	for (Intervention i : this.getInterventions()) {
    		if (!i.isAnnulee()) {
    			if (i.getType() == TypeIntervention.CM) {
        			res += i.getDuree()*1.5;
        		}
        		else if(i.getType() == TypeIntervention.TP) {
    				res += i.getDuree()*0.75;
    			}
        		else if(i.getType() == TypeIntervention.TD){
        			res += i.getDuree();
        		}
        		else {
        			throw new Exception("Le type d'intervention n'est pas de Type");
        		}
    		}
    	}
    	return res;
    }

    /**
     * Vérifie si le nombre d'heure prévues pour l'enseignant est supérieur
     * au nombre d'heure efffectuées pas ce dernier.
     * 
     * @return true si le nombre d'heure prévues est supérieur au nombre d'heures effectuées. Sinon false.
     * @throws Exception 
     */
    public boolean enSousService() throws Exception {
    	return this.heuresPrevues() > this.heuresPlanifiees();
    }
    
}
