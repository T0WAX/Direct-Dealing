package eu.telecomnancy;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Vue_MessageGlobal_Controller implements Observateur {
    private DirectDealing directDealing;

    public Vue_MessageGlobal_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        this.directDealing.notifierObservateursMessage();
    }

    @FXML
    private ListView<Label> listview_messages;

    @Override
    public void reagir() {
        listview_messages.getItems().clear();
        for(Message message : directDealing.getConversationAffichee().getMessages()){
            if(message.getEmmeteur().equals(directDealing.getUtilisateurCourant())){
                Label rightAlignedLabel = new Label(message.getMessage());
                //rightAlignedLabel.setStyle("-fx-text-alignment: right;");
                rightAlignedLabel.setAlignment(Pos.CENTER_RIGHT);
                rightAlignedLabel.setMaxWidth(780);
                rightAlignedLabel.setPadding(new Insets(0, 0, 0, 300));
                rightAlignedLabel.setWrapText(true);
                listview_messages.getItems().add(rightAlignedLabel);
            }else{
                Label leftAlignedLabel = new Label(message.getMessage());
                //leftAlignedLabel.setStyle("-fx-text-alignment: left;");
                leftAlignedLabel.setAlignment(Pos.CENTER_LEFT);
                leftAlignedLabel.setMaxWidth(780);
                leftAlignedLabel.setPadding(new Insets(0, 300, 0, 0));
                leftAlignedLabel.setWrapText(true);
                listview_messages.getItems().add(leftAlignedLabel);
            }
       }
    }
    
}
