package eu.telecomnancy;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Vue_Messagerie_Controller implements Observateur {
    private DirectDealing directDealing;
    
    @FXML
    private ListView<HBox> listView;

    public Vue_Messagerie_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        FXMLLoader loaderMessagerieElementInListView;
        this.listView.getItems().clear();
        for(Conversation conversation : this.directDealing.getUtilisateurCourant().getConversations()){
            loaderMessagerieElementInListView = new FXMLLoader();
            loaderMessagerieElementInListView.setLocation(getClass().getResource("Vue_MessagerieElementInListView.fxml"));
            loaderMessagerieElementInListView.setControllerFactory(iC->new Vue_MessagerieElementInListView_Controller(directDealing, conversation));
            try {
                this.listView.getItems().add(loaderMessagerieElementInListView.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reagir() {
    }
}


