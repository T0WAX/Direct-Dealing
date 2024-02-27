package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



public class Vue_Demarrage_Controller implements Observateur {
    private DirectDealing directDealing;
    
    @FXML
    private Button connexionButton;

    @FXML
    private Label connexionLabel;

    @FXML
    private Label inscriptionLabel1;

    @FXML
    private Label inscriptionLabel2;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label mdpLabel;

    @FXML
    private PasswordField mdpTextField;

    @FXML
    private Label titreLabel;


    public Vue_Demarrage_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
        this.connexionButton = new Button();
        this.connexionLabel = new Label();
        this.inscriptionLabel1 = new Label();
        this.inscriptionLabel2 = new Label();
        this.loginLabel = new Label();
        this.loginTextField = new TextField();
        this.mdpLabel = new Label();
        this.mdpTextField = new PasswordField();
        this.titreLabel = new Label();
    }
    
    @FXML
    public void connexion(ActionEvent event) {
        if(directDealing.login(loginTextField.getText().toString(), mdpTextField.getText().toString())){
            Main.getInstance().changeScene("Page_Rechercher");
        } else {
            // Fenêtre d'erreur de connexion
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Connexion");
            alert.setHeaderText("Erreur de connexion");
            alert.setContentText("L'identifiant et/ou le mot de passe sont incorrects.");

            alert.showAndWait();
        }
    }

    @FXML
    public void inscription(MouseEvent event) {
        Main.getInstance().changeScene("Page_Inscription");
    }

    @Override
    public void reagir() {
    }
}
