package eu.telecomnancy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Vue_MesDealsElementInListView_Controller implements Observateur {
    private DirectDealing directDealing;
    private Deal deal;

    @FXML
    private ImageView imageDeal;

    @FXML
    private Label titreLabel;

    @FXML
    private Button buttonRendre;

    @FXML
    private Label notation;


    public Vue_MesDealsElementInListView_Controller(DirectDealing directDealing, Deal deal) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
        this.deal = deal;
    }
    
    public void initialize() {
        this.imageDeal.setImage(this.deal.getPhoto());
        this.titreLabel.setText(this.deal.getTitre());
        this.buttonRendre.setDisable(this.deal.getRendu());
        reagir();
    }

    @FXML
    public void creer_litige(ActionEvent event){
        this.directDealing.setDealAffichee(this.deal);
        Main.getInstance().changeScene("Page_Litige_Deals");
    }

    @FXML
    public void rendre(ActionEvent event) {
        this.directDealing.setDealAffichee(this.deal);
        Main.getInstance().changeScene("Page_Retour");
    }

    @Override
    public void reagir() {
        if (this.deal.getAnnonce().getProprietaire().equals(directDealing.getUtilisateurCourant())){
            buttonRendre.setDisable(true);
            if (this.deal.getRendu()){
                notation.setText("Note du Deal : " + this.deal.getNote() + "/5");
            }
        }
        else if (this.deal.getRendu()){
            notation.setText("Note du Deal : " + this.deal.getNote() + "/5");
            buttonRendre.setDisable(true);
        }else if (!this.deal.getAnnonce().getProprietaire().equals(directDealing.getUtilisateurCourant())){
            buttonRendre.setDisable(false);
        }    
    }
}   



