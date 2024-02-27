package eu.telecomnancy;

import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class Vue_MessageAnnonceurTitre_Controller implements Observateur{
    private DirectDealing directDealing;

    @FXML
    private Label titre_annonce;

    @FXML
    private Label nom_prenom_demandeur;

    public Vue_MessageAnnonceurTitre_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize(){
        this.nom_prenom_demandeur.setText(directDealing.getConversationAffichee().getDemmandeur().getPrenom() + " " + directDealing.getConversationAffichee().getDemmandeur().getNom());
        this.titre_annonce.setText(directDealing.getConversationAffichee().getAnnonce().getTitre());
    }

    @Override
    public void reagir() {
    }
}
