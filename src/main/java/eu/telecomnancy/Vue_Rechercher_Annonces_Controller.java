package eu.telecomnancy;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

public class Vue_Rechercher_Annonces_Controller implements Observateur {
    
        private DirectDealing directDealing;
        private MoteurDeRecherche moteurDeRecherche;

        @FXML
        private ListView rechercheView;
    
        public Vue_Rechercher_Annonces_Controller(DirectDealing directDealing) {
            this.directDealing = directDealing;
            this.moteurDeRecherche = this.directDealing.getMoteurDeRecherche();
            this.directDealing.ajouterObservateur(this);
        }

        @FXML
        public void initialize() {
            this.rechercheView.getItems().clear();
            FXMLLoader loaderAnnoncesElement;
            for(Annonce annonce : this.moteurDeRecherche.getAnnonces()){
                if (annonce.getVisible()){
                    loaderAnnoncesElement = new FXMLLoader();
                    loaderAnnoncesElement.setLocation(getClass().getResource("Vue_Rechercher_Annonces_Element.fxml"));
                    loaderAnnoncesElement.setControllerFactory(iC->new Vue_Rechercher_Annonces_Element_Controller(directDealing, annonce));
                    try {
                        this.rechercheView.getItems().add(loaderAnnoncesElement.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    
        @Override
        public void reagir() {
            this.rechercheView.getItems().clear();
            FXMLLoader loaderAnnoncesElement;
            for(Annonce annonce : this.moteurDeRecherche.getAnnoncesFiltrees()){
                if (annonce.getVisible()){
                    loaderAnnoncesElement = new FXMLLoader();
                    loaderAnnoncesElement.setLocation(getClass().getResource("Vue_Rechercher_Annonces_Element.fxml"));
                    loaderAnnoncesElement.setControllerFactory(iC->new Vue_Rechercher_Annonces_Element_Controller(directDealing, annonce));
                    try {
                        this.rechercheView.getItems().add(loaderAnnoncesElement.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        
}
