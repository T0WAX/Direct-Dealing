package eu.telecomnancy;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Vue_Mes_Annonces_Controller implements Observateur {
    private DirectDealing directDealing;

    @FXML 
    private Label titreLabel; 

    @FXML 
    private Button creationButton;

    @FXML
    private ListView<HBox> annonceView;

    public Vue_Mes_Annonces_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        FXMLLoader loaderMes_AnnoncesElement;
        this.annonceView.getItems().clear();
        for(Annonce annonce : this.directDealing.getUtilisateurCourant().getAnnonces()){
            loaderMes_AnnoncesElement = new FXMLLoader();
            loaderMes_AnnoncesElement.setLocation(getClass().getResource("Vue_MesAnnoncesElement.fxml"));
            loaderMes_AnnoncesElement.setControllerFactory(iC->new Vue_Mes_AnnoncesElement_Controller(directDealing, annonce));
            try {
                this.annonceView.getItems().add(loaderMes_AnnoncesElement.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reagir() {
    }

    @FXML
    public void creerButtonClicked() {
        this.directDealing.setAnnonceAffichee(null);
        Main.getInstance().changeScene("Page_Creation_Annonce");
    }
}