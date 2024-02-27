package eu.telecomnancy;

import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class Vue_MessageDemandeurTitre_Controller implements Observateur{
    private DirectDealing directDealing;

    @FXML
    private Label titre_annonce;

    @FXML
    private Label nom_prenom_annonceur;

    public Vue_MessageDemandeurTitre_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }
    
    @FXML
    public void initialize(){
        this.nom_prenom_annonceur.setText(directDealing.getConversationAffichee().getAnnonce().getProprietaire().getPrenom() + " " + directDealing.getConversationAffichee().getAnnonce().getProprietaire().getNom());
        this.titre_annonce.setText(directDealing.getConversationAffichee().getAnnonce().getTitre());
    }

    @Override
    public void reagir() {
    }
}

