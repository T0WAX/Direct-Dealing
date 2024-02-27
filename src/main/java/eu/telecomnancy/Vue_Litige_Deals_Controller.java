package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class Vue_Litige_Deals_Controller implements Observateur {
    private DirectDealing directDealing;

    @FXML
    private TextArea textlitige;

    @FXML
    public void envoyer(ActionEvent event) {
        if(!textlitige.getText().isEmpty()){
            String message = textlitige.getText();
            Annonce annoncelitige = new Annonce(directDealing.getAdmin(), "Litige : " + directDealing.getDealAffichee().getAnnonce().getTitre(), directDealing.getDealAffichee().getAnnonce().getDescription(), directDealing.getDealAffichee().getAnnonce().getTypeAnnonce(), directDealing.getDealAffichee().getAnnonce().getCout(), directDealing.getDealAffichee().getAnnonce().getUniteCout(), directDealing.getDealAffichee().getAnnonce().getLocalisation(), directDealing.getDealAffichee().getAnnonce().getDisponibilites(), directDealing.getDealAffichee().getAnnonce().getPhoto());
            //Conversation conversation_Admin_deal = new Conversation(directDealing.getUtilisateurCourant().getDeals(), directDealing.getUtilisateurCourant(), directDealing, message);
            Conversation conversation_Admin = new Conversation(annoncelitige, directDealing.getDealAffichee().getCout(), directDealing.getDealAffichee().getDebut(), directDealing.getDealAffichee().getFin(), directDealing.getUtilisateurCourant(), directDealing, message);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Litige");
            alert.setHeaderText("Litige transmis");
            alert.setContentText("Le litige a bien été transmis à l'administrateur.");
            alert.showAndWait();
            
            Main.getInstance().changeScene("Page_MesDeals");
        }
    }

    public Vue_Litige_Deals_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
    }   
}
