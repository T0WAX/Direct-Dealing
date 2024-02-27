package eu.telecomnancy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Vue_Rechercher_Annonces_Element_Controller implements Observateur{
    private DirectDealing directDealing;
    private Annonce annonce;
    
    @FXML
    private ImageView imageAnnonce;

    @FXML
    private Label titreLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label debutLabel;

    @FXML
    private Label finLabel;

    @FXML
    private Label localisationLabel;

    @FXML
    private Button voirButton;

    public Vue_Rechercher_Annonces_Element_Controller(DirectDealing directDealing, Annonce annonce) {
        this.directDealing = directDealing;
        this.annonce = annonce;
    }
    
     @FXML
    public void initialize() {
        this.imageAnnonce.setImage(this.annonce.getPhoto());
        this.titreLabel.setText(this.annonce.getTitre());
        this.typeLabel.setText(this.annonce.getTypeAnnonce());
        // On récupère la date de début la plus petite parmi les disponibilités de l'annonce et on l'affiche dans le label sous la forme "du 01/01/2020"
        LocalDate debutPlusPetit = null;
        for (List<Date> disponibilite : this.annonce.getDisponibilites()) {
            LocalDate debut = disponibilite.get(0).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            if (debutPlusPetit == null || debut.isBefore(debutPlusPetit)) {
                debutPlusPetit = debut;
            }
        }
        if (debutPlusPetit != null) {
            this.debutLabel.setText("du " + debutPlusPetit.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        
        // On récupère la date de fin la plus grande parmi les disponibilités de l'annonce et on l'affiche dans le label sous la forme "au 01/01/2020"
        LocalDate finPlusGrande = null;
        for (List<Date> disponibilite : this.annonce.getDisponibilites()) {
            LocalDate fin = disponibilite.get(1).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            if (finPlusGrande == null || fin.isAfter(finPlusGrande)) {
                finPlusGrande = fin;
            }
        }
        if (finPlusGrande != null) {
            this.finLabel.setText("au " + finPlusGrande.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        
        this.localisationLabel.setText(this.annonce.getLocalisation());
    }

    @FXML
    public void voirButtonClicked(ActionEvent event) {
        this.directDealing.setAnnonceAffichee(this.annonce);
        for (Annonce annonce : this.directDealing.getUtilisateurCourant().getAnnonces()){
            if (this.annonce.equals(annonce)){
                Main.getInstance().changeScene("Page_ModifierAnnonce");
                return;
            }
        }
        Main.getInstance().changeScene("Page_VoirAnnonce");
    }


    @Override
    public void reagir() {
    }
} 