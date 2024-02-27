package eu.telecomnancy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Vue_MessageInfos_Controller implements Observateur{
    private DirectDealing directDealing;

    public Vue_MessageInfos_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize(){
        reagir();
    }

    @FXML
    ImageView imageView;

    @FXML
    Label description;
    
    @Override
    public void reagir() {
        imageView.setImage(directDealing.getConversationAffichee().getAnnonce().getPhoto());
        description.setText(directDealing.getConversationAffichee().getAnnonce().getDescription());
    }
    
}
