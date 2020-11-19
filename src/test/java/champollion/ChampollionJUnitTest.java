package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
	Date debut;
	Salle salle;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");	
		debut = new Date();
		salle = new Salle("A100", 30);
	}
	
	@Test
	public void testGetterPersonne() {
		assertEquals("untel", untel.getNom());
		assertEquals("untel@gmail.com", untel.getEmail());
	}

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testNouvelEnseignantVide() {
		assertTrue(untel.getInterventions().isEmpty());
		assertTrue(untel.getServicePrevus().isEmpty());
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
        		untel.ajouteEnseignement(java, 10, 10, 10);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}
	
	@Test
	public void testHeurePrevue() {
		untel.ajouteEnseignement(java, 10, 10, 10);
		untel.ajouteEnseignement(uml, 0, 20, 0);
		assertEquals(52,untel.heuresPrevues());
	}
	
	@Test
	public void testAjoutIntervention() {
		Intervention i = new Intervention(salle, uml, untel, debut, 2);
		untel.ajouteIntervention(i);
		assertEquals(uml,untel.getInterventions().get(untel.getInterventions().size()-1).getMatiere());
	}
	
	@Test
	public void testSalle() {
		Intervention i = new Intervention(salle, uml, untel, debut, 2);
		untel.ajouteIntervention(i);
		assertEquals(30,untel.getInterventions().get(untel.getInterventions().size()-1).getLieu().getCapacite());
		assertEquals("A100",untel.getInterventions().get(untel.getInterventions().size()-1).getLieu().getIntitule());
	}
	
	@Test
	public void testTypeIntervention() {
		Intervention i = new Intervention(salle, uml, untel, debut, 2);
		untel.ajouteIntervention(i);
		untel.getInterventions().get(untel.getInterventions().size()-1).setType(TypeIntervention.CM);
		assertEquals(TypeIntervention.CM, untel.getInterventions().get(untel.getInterventions().size()-1).getType());
	}
	
	@Test
	public void testHeurePlanifie() {
		Intervention i1 = new Intervention(salle, uml, untel, debut, 2);
		Intervention i2 = new Intervention(salle, java, untel, debut, 2);
		Intervention i3 = new Intervention(salle, uml, untel, debut, 2);
		Intervention i4 = new Intervention(salle, uml, untel, debut, 2);
		i1.setType(TypeIntervention.CM);
		i2.setType(TypeIntervention.TP);
		i3.setType(TypeIntervention.TD);
		i4.setAnnulee(true);
		untel.ajouteIntervention(i1);
		untel.ajouteIntervention(i2);
		untel.ajouteIntervention(i3);
		untel.ajouteIntervention(i4);
		assertEquals(6,untel.heuresPlanifiees());
	}

	@Test
	public void testSousServiceTrue() {
		Intervention i1 = new Intervention(salle, uml, untel, debut, 2);
		Intervention i2 = new Intervention(salle, java, untel, debut, 2);
		Intervention i3 = new Intervention(salle, uml, untel, debut, 2);
		i1.setType(TypeIntervention.CM);
		i2.setType(TypeIntervention.TP);
		i3.setType(TypeIntervention.TD);
		untel.ajouteIntervention(i1);
		untel.ajouteIntervention(i2);
		untel.ajouteIntervention(i3);
		untel.ajouteEnseignement(java, 10, 10, 10);
		untel.ajouteEnseignement(uml, 0, 20, 0);
		assertTrue(untel.enSousService());
	}
	
	@Test
	public void testSousServiceFalse() {
		Intervention i1 = new Intervention(salle, uml, untel, debut, 2);
		Intervention i2 = new Intervention(salle, java, untel, debut, 2);
		Intervention i3 = new Intervention(salle, uml, untel, debut, 2);
		i1.setType(TypeIntervention.TD);
		i2.setType(TypeIntervention.TD);
		i3.setType(TypeIntervention.TD);
		untel.ajouteIntervention(i1);
		untel.ajouteIntervention(i2);
		untel.ajouteIntervention(i3);
		untel.ajouteEnseignement(uml, 0, 4, 0);
		assertFalse(untel.enSousService());
	}
}
