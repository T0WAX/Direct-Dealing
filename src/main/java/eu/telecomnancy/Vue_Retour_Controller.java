package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Vue_Retour_Controller implements Observateur{
    private DirectDealing directDealing;
    private int note;

    @FXML
    public void unpoint(ActionEvent event) {
        this.note = 1;
    }

    @FXML
    public void deuxpoints(ActionEvent event) {
        this.note = 2;
    }

    @FXML
    public void troispoints(ActionEvent event) {
        this.note = 3;
    }

    @FXML
    public void quatrepoints(ActionEvent event) {
        this.note = 4;
    }

    @FXML
    public void cinqpoints(ActionEvent event) {
        this.note = 5;
    }

    @FXML
    public void envoyer(ActionEvent event) {
        directDealing.getDealAffichee().rendreEtEnvoyer(this.note);
        Main.getInstance().changeScene("Page_MesDeals");
    }

    public Vue_Retour_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }
    
    @Override
    public void reagir() {
    }
    
}
