package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Vue_Mes_AnnoncesElement_Controller implements Observateur {
    private DirectDealing directDealing;
    private Annonce annonce;

    @FXML
    private HBox infosHBox;
    
    @FXML
    private ImageView imageAnnonce;
    
    @FXML
    private Label titreLabel;

    @FXML
    private Button modifierButton;

    @FXML
    private Button litigeButton;

    public Vue_Mes_AnnoncesElement_Controller(DirectDealing directDealing, Annonce annonce) {
        this.directDealing = directDealing;
        this.annonce = annonce;
        this.directDealing.ajouterObservateur(this);
    }
    
    @FXML
    public void initialize() {
        if (!this.annonce.getVisible()) {
            //On change le background de la cellule
            this.infosHBox.setStyle("-fx-background-color: #cacaca76;");
            this.titreLabel.setStyle("-fx-text-fill: #ffffff;");
            this.imageAnnonce.setOpacity(0.5);
        }
        this.imageAnnonce.setImage(this.annonce.getPhoto());
        this.titreLabel.setText(this.annonce.getTitre());
    }

    @FXML
    public void modifierButtonClicked(ActionEvent event) {
        this.directDealing.setAnnonceAffichee(this.annonce);
        Main.getInstance().changeScene("Page_ModifierAnnonce");
    }


    @Override
    public void reagir() {
    }
} 