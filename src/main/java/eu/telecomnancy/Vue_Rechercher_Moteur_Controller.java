package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Vue_Rechercher_Moteur_Controller implements Observateur{

    private DirectDealing directDealing;
    private MoteurDeRecherche moteurDeRecherche;

    @FXML
    private Label titreLabel;
    
    @FXML
    private TextField rechercherTextField;

    @FXML
    private Label debutLabel;

    @FXML
    private DatePicker debutPicker;

    @FXML
    private Label finLabel;

    @FXML
    private DatePicker finPicker;

    @FXML
    private Label localLabel;

    @FXML
    private TextField localTextField;

    @FXML
    private CheckBox serviceCheck;

    @FXML
    private CheckBox materielCheck;

    @FXML
    private Button rechercherButton;
    
    public Vue_Rechercher_Moteur_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.moteurDeRecherche = this.directDealing.getMoteurDeRecherche();
        //On définit la checkbox comme cachée par défaut
        this.serviceCheck = new CheckBox();
        this.materielCheck = new CheckBox();
        this.directDealing.ajouterObservateur(this);
    }


    @FXML
    public void initialize() {
        //System.out.println("Vue_Rechercher_Moteur_Controller initialize");
        this.serviceCheck.setSelected(true);
        this.materielCheck.setSelected(true);
    }

    
    @FXML
    public void rechercherButtonClicked(ActionEvent event) {
        //System.out.println("Rechercher button clicked");
        this.moteurDeRecherche.rechercher(serviceCheck.isSelected(), materielCheck.isSelected(), debutPicker.getValue(), finPicker.getValue(), localTextField.getText(), rechercherTextField.getText());
        this.directDealing.notifierObservateursMoteur();
    }

    
    @Override
    public void reagir() {
    }
    
}
