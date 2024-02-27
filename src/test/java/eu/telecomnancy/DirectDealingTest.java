package eu.telecomnancy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
public class DirectDealingTest {
    @Test
    void testCreateDirectDealing() {
        DirectDealing directDealing = new DirectDealing();
        assertNotNull(directDealing);
    }

    @Test
    void testSignin(){
        DirectDealing directDealing = new DirectDealing();
        String idf = "victor2";
        String motDePasse = "1234";
        String motDePasseConfirm = "1234";
        String description = "Je suis un fermier de 54 ans.";
        String nom = "Bernard";
        String prenom = "Fabrice";

        Boolean signInConfirmed = directDealing.signin(idf,motDePasse,motDePasseConfirm,description,nom,prenom );
        assertEquals(true, signInConfirmed);

        Utilisateur userCreated = directDealing.getMapUtilisateurs().get(idf);

        assertEquals(nom, userCreated.getNom());
        assertEquals(prenom, userCreated.getPrenom());
        assertEquals(motDePasse, userCreated.getMotDePasse());
        assertEquals(description, userCreated.getDescription());
    }

    @Test
    void testLogin(){
        DirectDealing directDealing = new DirectDealing();
        String idf = "victor33";
        String motDePasse = "1234";
        String motDePasseConfirm = "1234";
        String description = "Je suis un fermier de 54 ans.";
        String nom = "Bernard";
        String prenom = "Fabrice";

        directDealing.signin(idf,motDePasse,motDePasseConfirm,description,nom,prenom );
        Boolean LogInConfirmed = directDealing.login(idf, motDePasse);
        assertEquals(true, LogInConfirmed);
    }
}
