package eu.telecomnancy;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class Vue_Menu_Controller implements Observateur {   
    private DirectDealing directDealing;

    @FXML
    private Label labelNomPrenom;

    @FXML
    private Label labelNombreFlorains;

    public Vue_Menu_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    public void initialize() {
        reagir();
    }

    @FXML
    public void aller_messagerie(Event event){
        Main.getInstance().changeScene("Page_Messagerie");
    }

    @FXML
    public void aller_mesdeals(Event event){
        Main.getInstance().changeScene("Page_MesDeals");
    }

    @FXML
    public void aller_MesAnnonces(Event event){
        Main.getInstance().changeScene("Page_MesAnnonces");
    }

    @FXML
    public void aller_profil(Event event){
        Main.getInstance().changeScene("Page_Profil");
    }

    @FXML
    public void aller_rechercher(Event event){
        Main.getInstance().changeScene("Page_Rechercher");
    }

    @FXML
    public void aller_accueil(Event event){
        Main.getInstance().changeScene("Page_Accueil");
        directDealing.setUtilisateurCourant(null);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText("Déconnexion réussie");
        alert.setContentText("Vous avez bien été déconnecté.");

        alert.showAndWait();
    }

    @Override
    public void reagir() {
        labelNomPrenom.setText(directDealing.getUtilisateurCourant().getNom() + " " + directDealing.getUtilisateurCourant().getPrenom());
        labelNombreFlorains.setText(Integer.toString(directDealing.getUtilisateurCourant().getFlorainLibre()) + " Florains's libre(s)");
    }
}

