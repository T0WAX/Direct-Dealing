package eu.telecomnancy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vue_Profil_Controller implements Observateur{
    private DirectDealing directDealing;

    @FXML
    private ImageView profilImage;

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label florainLabel;


    
    public Vue_Profil_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        this.profilImage.setImage(new Image("file:src/main/resources/eu/telecomnancy/images/risitas-flute.gif"));
        nomLabel.setText("Votre nom : " + this.directDealing.getUtilisateurCourant().getNom());
        prenomLabel.setText("Votre prénom : " + this.directDealing.getUtilisateurCourant().getPrenom());
        descriptionLabel.setText("Description de votre profil : " + this.directDealing.getUtilisateurCourant().getDescription());
        florainLabel.setText("Vous possédez sur ce compte " + Integer.toString(this.directDealing.getUtilisateurCourant().getFlorain()) + " Florain(s)"
        + " dont " + Integer.toString(this.directDealing.getUtilisateurCourant().getFlorainReservee()) + " Florain(s) reservée pour des Deals qui vous auraient été acceptés."
        );
    }
    
    @Override
    public void reagir() {
    }
}