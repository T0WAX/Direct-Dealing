package eu.telecomnancy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Vue_AnnonceInfos_Controller implements Observateur {
    private DirectDealing directDealing;
    private Annonce annonce;

    @FXML
    private VBox voirVBox;

    @FXML
    private ImageView image;

    @FXML
    private Label titreLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label coutLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label localLabel;

    @FXML
    private ListView<String> dateListView;

    public Vue_AnnonceInfos_Controller(DirectDealing directDealing, Annonce annonce) {
        this.directDealing = directDealing;
        this.annonce = annonce;
        if (this.directDealing != null) {
            this.directDealing.ajouterObservateur(this);
        }
    }

    @FXML
    public void initialize() {
        if(this.annonce == null) {
            //System.out.println("Vue_AnnonceInfos_Controller: initialize: this.annonce == null");
            this.titreLabel.setText("Titre de l'annonce");
            this.descriptionLabel.setText("Description de l'annonce");
            this.coutLabel.setText("Coût de l'annonce");
            this.nomLabel.setText("Nom de l'annonceur");
            this.image.setImage(new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/vierge.png")));
            this.localLabel.setText("Localisation de l'annonce");
        }
        else {
            if (this.annonce.getProprietaire() != this.directDealing.getUtilisateurCourant()) {
                this.voirVBox.setPrefWidth(500);
            }
            this.titreLabel.setText(this.annonce.getTitre());
            this.descriptionLabel.setText(this.annonce.getDescription());
            this.coutLabel.setText(Integer.toString(this.annonce.getCout()) + " Florains" + this.annonce.getUniteCout());
            this.nomLabel.setText(this.annonce.getProprietaire().getPrenom() + " " + this.annonce.getProprietaire().getNom());
            this.image.setImage(this.annonce.getPhoto());
            this.localLabel.setText(this.annonce.getLocalisation());

            List<List<Date>> disponibilites = this.annonce.getDisponibilites();
            ObservableList<String> creneauxAffiches = FXCollections.observableArrayList();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (List<Date> creneau : disponibilites) {
                if (creneau.size() == 2) {
                    Date startDate = creneau.get(0);
                    Date endDate = creneau.get(1);
                    String formattedCreneau = "De " + sdf.format(startDate) + " à " + sdf.format(endDate);
                    creneauxAffiches.add(formattedCreneau);
                }
            }
            this.dateListView.setItems(creneauxAffiches);
        }
    }

    public void setImage(Image image){
        this.image.setImage(image);
    }

    @Override
    public void reagir() {
        this.titreLabel.setText(this.annonce.getTitre());
        this.descriptionLabel.setText(this.annonce.getDescription());
        this.coutLabel.setText(Integer.toString(this.annonce.getCout()));
        this.nomLabel.setText(this.annonce.getProprietaire().getNom());
        this.image.setImage(this.annonce.getPhoto());
        this.localLabel.setText(this.annonce.getLocalisation());
    }
}