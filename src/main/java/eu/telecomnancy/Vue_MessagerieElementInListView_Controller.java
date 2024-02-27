package eu.telecomnancy;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Vue_MessagerieElementInListView_Controller implements Observateur {
    private DirectDealing directDealing;
    private Conversation conversation;
    
    @FXML
    private ImageView imageConversation;

    @FXML
    private Label annonce1;

    @FXML
    private Label contact1;

    @FXML
    public void ouvrir_message(Event event) {
        directDealing.setConversationAffichee(conversation);
        if(conversation.getAnnonce().getProprietaire().equals(directDealing.getUtilisateurCourant())){
            Main.getInstance().changeScene("Page_Message_Annonceur"); //celui qui propose contre rémunération
        }else{
             Main.getInstance().changeScene("Page_Message_Demandeur");
        }
    }

    public Vue_MessagerieElementInListView_Controller(DirectDealing directDealing, Conversation conversation) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
        this.conversation = conversation;
    }

    @FXML
    public void initialize() {
        contact1.setText(conversation.getInterlocutorOf(directDealing.getUtilisateurCourant()).getNom() + " " + conversation.getInterlocutorOf(directDealing.getUtilisateurCourant()).getPrenom());
        annonce1.setText(conversation.getAnnonce().getTitre());
        imageConversation.setImage(conversation.getAnnonce().getPhoto());
    }
    
    @Override
    public void reagir() {
    }
}


