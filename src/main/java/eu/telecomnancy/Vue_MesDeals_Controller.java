package eu.telecomnancy;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class Vue_MesDeals_Controller implements Observateur {
    private DirectDealing directDealing;
    
    @FXML
    private ListView<HBox> listViewMesDeals;

    public void initialize() {
        FXMLLoader loaderMesDealsElementInListView;
        this.listViewMesDeals.getItems().clear();
        for(Deal deal : this.directDealing.getUtilisateurCourant().getDeals()){
            loaderMesDealsElementInListView = new FXMLLoader();
            loaderMesDealsElementInListView.setLocation(getClass().getResource("Vue_MesDealsElementInListView.fxml"));
            loaderMesDealsElementInListView.setControllerFactory(iC->new Vue_MesDealsElementInListView_Controller(directDealing, deal));  
            try {
                this.listViewMesDeals.getItems().add(loaderMesDealsElementInListView.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Vue_MesDeals_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
        }


    @Override
    public void reagir() {
        // Méthode de réaction
    }
}


