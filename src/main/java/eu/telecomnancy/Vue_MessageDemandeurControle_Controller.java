package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Vue_MessageDemandeurControle_Controller implements Observateur{
    private DirectDealing directDealing;

    @FXML
    public void envoyer(ActionEvent event) {
        if(!message.getText().isEmpty()){
            directDealing.sendMessage(message.getText().toString());
            directDealing.notifierObservateursMessage();
            message.clear();
        }
    }
    
    @FXML
    private TextArea message;

    public Vue_MessageDemandeurControle_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }
    
    @Override
    public void reagir() {
    }

    

}
