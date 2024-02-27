package eu.telecomnancy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Tests {
    private static Tests instance;
    static Calendar calendrier = Calendar.getInstance();
    private Date debutCreneau;
    private Date finCreneau;
    private Utilisateur proprietaire = new Utilisateur(0, "ident", "mdp", "desc", "Dupont", "Michel");
    private Annonce annonce = new Annonce(proprietaire, "titre", "description", "materiel", 100, "EUR", "localisation", new ArrayList<>(), null);
    
    public static void main(String[] args) {
        Tests testInstance = new Tests();
        calendrier.clear();
        testInstance.testAjouterCreneauDispo();
        testInstance.testReserverCreneau();
        testInstance.testRetirerCreneauDispo();
        testInstance.testLibererCreneau();
    }
    private Tests() {}
    public static Tests getInstance() {
        if (instance == null) {
            System.out.println("[TESTS]");
            instance = new Tests();
        }
        return instance;
    }

    

    void testOutput(boolean test) {
        if (test) {
            System.out.println("Test réussi");
        } else {
            System.out.println("Test échoué");
        }
    }

    void testAjouterCreneauDispo() {
        System.out.println("[testAjouterCreneauDispo()]");
        annonce.afficherCreneauxDispo();
        calendrier.set(2024, Calendar.JANUARY, 15);
        Date debut1 = calendrier.getTime();

        calendrier.set(2024, Calendar.JANUARY, 20);
        Date fin1 = calendrier.getTime();
        annonce.ajouterCreneauDispo(debut1, fin1);

        calendrier.set(2024, Calendar.JANUARY, 22);
        Date debut2 = calendrier.getTime();

        calendrier.set(2024, Calendar.JANUARY, 24);
        Date fin2 = calendrier.getTime();
        annonce.ajouterCreneauDispo(debut2, fin2);

        boolean testResult = annonce.getDisponibilites().size() == 2;

        annonce.afficherCreneauxDispo();
        testOutput(testResult);
    }

    void testReserverCreneau() {
        System.out.println("[testReserverCreneau()]");

        calendrier.set(2024, Calendar.JANUARY, 17);
        Date debutReserve = calendrier.getTime();

        calendrier.set(2024, Calendar.JANUARY, 19);
        Date finReserve = calendrier.getTime();

        boolean resultat = annonce.reserverCreneau(debutReserve, finReserve);

        annonce.afficherCreneauxDispo();
        testOutput(resultat && annonce.getDisponibilites().size() == 3);
    }

    void testRetirerCreneauDispo() {
        System.out.println("[testRetirerCreneauDispo()]");

        calendrier.set(2024, Calendar.JANUARY, 22);
        Date debutCreneauARetirer = calendrier.getTime();

        calendrier.set(2024, Calendar.JANUARY, 24);
        Date finCreneauARetirer = calendrier.getTime();

        annonce.retirerCreneauDispo(debutCreneauARetirer, finCreneauARetirer);
        annonce.afficherCreneauxDispo();
        
        testOutput(annonce.getDisponibilites().size() == 2);
    }
    
    void testLibererCreneau() {
        System.out.println("[testLibererCreneau()]");

        // Ajouter un créneau disponible
        calendrier.set(2024, Calendar.FEBRUARY, 21);
        Date debutCreneauLibere = calendrier.getTime();

        calendrier.set(2024, Calendar.FEBRUARY, 27);
        Date finCreneauLibere = calendrier.getTime();

        // Libération du créneau
        boolean resultatLiberation = annonce.libererCreneau(debutCreneauLibere, finCreneauLibere);
        System.out.println("Libération effectuée: " + resultatLiberation);

        // Affichage des créneaux disponibles après la libération
        annonce.afficherCreneauxDispo();

        // Vérifier si le créneau est de nouveau disponible
        boolean testResult = resultatLiberation && annonce.estCreneauDisponible(debutCreneauLibere, finCreneauLibere);
        testOutput(testResult);
    }
}
