package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Vue_MessageAnnonceurControle_Controller implements Observateur {
    private DirectDealing directDealing;

    public Vue_MessageAnnonceurControle_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize(){
        this.directDealing.notifierObservateursMessage();
    }

    @FXML
    private Button refuser;

    @FXML
    private Button accepter;

    @FXML
    public void accepter(ActionEvent event) {
        directDealing.accepterOffre();
        directDealing.notifierObservateursMessage();
    }

    @FXML
    public void refuser(ActionEvent event) {
        directDealing.refuserOffre();
        directDealing.notifierObservateursMessage();
    }
    
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


    @Override
    public void reagir() {
        if(directDealing.getConversationAffichee().isDecisionTakenByAnnoncer()){
            refuser.setDisable(true);
            accepter.setDisable(true);
        }else{
            refuser.setDisable(false);
            accepter.setDisable(false);
        }
    }
}
