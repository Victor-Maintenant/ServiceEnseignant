package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
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
		Date debut = new Date();
		Salle salle = new Salle("A100", 30);
		Intervention i = new Intervention(salle, uml, untel, debut, 2);
		untel.ajouteIntervention(i);
		assertEquals(uml,untel.getInterventions().get(untel.getInterventions().size()-1).getMatiere());
	}
	
	@Test
	public void testSalle() {
		Date debut = new Date();
		Salle salle = new Salle("A100", 30);
		Intervention i = new Intervention(salle, uml, untel, debut, 2);
		untel.ajouteIntervention(i);
		assertEquals(30,untel.getInterventions().get(untel.getInterventions().size()-1).getLieu().getCapacite());
		assertEquals("A100",untel.getInterventions().get(untel.getInterventions().size()-1).getLieu().getIntitule());
	}
	
	@Test
	public void testTypeIntervention() {
		Date debut = new Date();
		Salle salle = new Salle("A100", 30);
		Intervention i = new Intervention(salle, uml, untel, debut, 2);
		untel.ajouteIntervention(i);
		untel.getInterventions().get(untel.getInterventions().size()-1).setType(TypeIntervention.CM);
		assertEquals(TypeIntervention.CM, untel.getInterventions().get(untel.getInterventions().size()-1).getType());
	}
}
