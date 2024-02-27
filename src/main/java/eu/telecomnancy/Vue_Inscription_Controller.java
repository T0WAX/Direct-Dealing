package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Vue_Inscription_Controller implements Observateur {
    private DirectDealing directDealing;

    @FXML
    private Button connexionButton;
    
    @FXML
    private Label descriptionLabel;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button inscriptionButton;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label mdp2Label;

    @FXML
    private PasswordField mdp2PasswordField;

    @FXML
    private Label mdpLabel;

    @FXML
    private PasswordField mdpPasswordField;

    @FXML
    private Label nomLabel;

    @FXML
    private TextField nomTextField;

    @FXML
    private Label prenomLabel;

    @FXML
    private TextField prenomTextField;

    @FXML
    private Label titreLabel;

    public Vue_Inscription_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void connexionButtonClicked(ActionEvent event) {
        Main.getInstance().changeScene("Page_Accueil");
    }

    @FXML
    public void inscriptionButtonClicked(ActionEvent event) {
        if(!mdp2PasswordField.getText().toString().equals(mdpPasswordField.getText().toString())){
            mdpPasswordField.setText("");
            mdp2PasswordField.setText("");
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Inscription");
            alert.setHeaderText("Erreur d'inscription");
            alert.setContentText("Les deux mots de passe sont différents.");

            alert.showAndWait();
        } else {
            if(directDealing.signin(loginTextField.getText().toString(), mdpPasswordField.getText().toString(), mdp2PasswordField.getText().toString(), descriptionTextArea.getText().toString(), nomTextField.getText().toString(), prenomTextField.getText().toString())){
                Main.getInstance().changeScene("Page_Rechercher");
            }
        }
    }
    @Override
    public void reagir() {
    }
}