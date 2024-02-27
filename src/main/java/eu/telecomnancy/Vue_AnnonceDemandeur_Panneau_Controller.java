package eu.telecomnancy;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Vue_AnnonceDemandeur_Panneau_Controller implements Observateur {
    private DirectDealing directDealing;

    @FXML
    private Label propositionLabel;
    @FXML
    private TextArea propositionTextArea;

    @FXML
    private Label coutLabel;
    @FXML
    private TextField coutTextField;

    @FXML
    private Label debutLabel;
    @FXML
    private DatePicker debutPicker;

    @FXML
    private Label finLabel;
    @FXML
    private DatePicker finPicker;

    @FXML
    private Button envoyerButton;

    public Vue_AnnonceDemandeur_Panneau_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void envoyerButtonClicked(ActionEvent event) {
        if(!this.propositionTextArea.getText().isEmpty() || !this.coutTextField.getText().isEmpty() || this.debutPicker.getValue() != null && this.finPicker.getValue() != null) {
            Date debut = Date.from(this.debutPicker.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            Date fin = Date.from(this.finPicker.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            if (debut.compareTo(fin) < 0) {
                try {
                    Integer.parseInt(this.coutTextField.getText());
                } catch(NumberFormatException e) {
                    this.coutTextField.setText("0");
                }
                if(debut.compareTo(fin) < 0 && Integer.parseInt(this.coutTextField.getText()) <= this.directDealing.getUtilisateurCourant().getFlorainLibre()) {
                    this.directDealing.getUtilisateurCourant().makeAnOffer(this.directDealing.getAnnonceAffichee(), this.propositionTextArea.getText(), Integer.parseInt(this.coutTextField.getText()), debut, fin);
                    this.propositionTextArea.setText("");
                    this.coutTextField.setText("");
                    this.debutPicker.setValue(null);
                    this.finPicker.setValue(null);
                    Main.getInstance().changeScene("Page_Messagerie");
                }
            }
        }
    }
    
    @Override
    public void reagir() {
    }
}