package eu.telecomnancy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
public class DispoTest {

    private Annonce annonce;
    private Date debutCreneau;
    private Date finCreneau;
    private Utilisateur proprietaire;

    Calendar calendrier = Calendar.getInstance();

    @BeforeEach
    void setUp() {
        proprietaire = new Utilisateur(0, "ident", "mdp", "desc", "Dupont", "Michel");
        annonce = new Annonce(proprietaire, "titre", "description", "materiel", 100, "EUR", "localisation", new ArrayList<>(), null) {};

        calendrier.set(2024, Calendar.JANUARY, 15);
        debutCreneau = calendrier.getTime();
        calendrier.set(2024, Calendar.JANUARY, 20);
        finCreneau = calendrier.getTime();

        annonce.ajouterCreneauDispo(debutCreneau, finCreneau);
        calendrier.set(2024, Calendar.JANUARY, 22);
        debutCreneau = calendrier.getTime();
        calendrier.set(2024, Calendar.JANUARY, 24);
        finCreneau = calendrier.getTime();
        annonce.ajouterCreneauDispo(debutCreneau, finCreneau);
    }

    @Test
    void testAjouterCreneauDispo() {
        System.out.println("Créneaux disponibles TEST");
        annonce.afficherCreneauxDispo();
        assertFalse(annonce.getDisponibilites().isEmpty());
    }

    @Test
    void testEstCreneauDisponible() {
        assertTrue(annonce.estCreneauDisponible(debutCreneau, finCreneau));
    }

    @Test
    void testReserverCreneau() {
        assertTrue(annonce.reserverCreneau(debutCreneau, finCreneau));
        assertFalse(annonce.estCreneauDisponible(debutCreneau, finCreneau));
    }
}