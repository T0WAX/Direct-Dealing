package eu.telecomnancy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class Vue_AnnonceAnnonceur_Panneau_Controller implements Observateur {
    private Vue_AnnonceInfos_Controller ai;
    private DirectDealing directDealing;
    private File image;
    private String[] typeAnnonce = {"service", "materiel"};
    private boolean modifImage = false;
    private boolean modifDateListe = false;
    private Annonce annonce;
    private List<List<Date>> disponibilites;

    @FXML
    private Label titreLabel;
    @FXML
    private TextField titreTextField;

    @FXML
    private Label descriptionLabel;
    @FXML
    private TextField descriptionTextField;

    @FXML
    private Label coutLabel;
    @FXML
    private TextField coutTextField;

    @FXML
    private Label uniteCoutLabel;
    @FXML
    private TextField uniteCoutTextField;

    @FXML
    private Label localLabel;
    @FXML
    private TextField localTextField;


    @FXML
    private Label debutLabel;
    @FXML
    private DatePicker debutPicker;

    @FXML
    private Label finLabel;
    @FXML
    private DatePicker finPicker;

    @FXML
    private Button ajoutDispoButton;

    @FXML
    private Label dateLabel;
    @FXML
    private ListView<String> dateListView;

    @FXML
    private Label supprDispoButtonLabel;

    @FXML
    private Label typeLabel;
    @FXML
    private ChoiceBox<String> choiceAnnonce;

    @FXML
    private Button imageButton;

    @FXML
    private Button posterButton;

    @FXML
    private Button masquerButton;


    public Vue_AnnonceAnnonceur_Panneau_Controller(DirectDealing directDealing, Vue_AnnonceInfos_Controller ai) {
        this.ai = ai;
        this.directDealing = directDealing;
        this.choiceAnnonce = new ChoiceBox<String>();
        this.titreTextField = new TextField();
        this.descriptionTextField = new TextField();
        this.coutTextField = new TextField();
        this.uniteCoutTextField = new TextField();
        this.localTextField = new TextField();
        this.dateListView = new ListView<String>();
        //this.disponibilites = new ArrayList<List<Date>>();
        this.annonce = this.directDealing.getAnnonceAffichee();
        this.directDealing.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        this.choiceAnnonce.getItems().addAll(this.typeAnnonce);
        this.choiceAnnonce.setValue(this.typeAnnonce[0]);
        if (this.directDealing.getAnnonceAffichee() != null){
            this.titreTextField.setText(this.directDealing.getAnnonceAffichee().getTitre());
            this.descriptionTextField.setText(this.directDealing.getAnnonceAffichee().getDescription());
            this.coutTextField.setText(Integer.toString(this.directDealing.getAnnonceAffichee().getCout()));
            this.uniteCoutTextField.setText(this.directDealing.getAnnonceAffichee().getUniteCout());
            this.localTextField.setText(this.directDealing.getAnnonceAffichee().getLocalisation());
            this.choiceAnnonce.setValue(this.directDealing.getAnnonceAffichee().getTypeAnnonce());
            //System.out.println(this.directDealing.getAnnonceAffichee().getPhoto().getUrl());
            
            if (annonce.getVisible()){
                this.masquerButton.setText("Masquer l'annonce");
            }
            else{
                this.masquerButton.setText("Afficher l'annonce");
            }

            disponibilites = this.annonce.getDisponibilites();
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
            if (this.directDealing.getAnnonceAffichee().getPhoto() != null && this.directDealing.getAnnonceAffichee().getPhoto().getUrl() != null) {
                this.image = new File(this.directDealing.getAnnonceAffichee().getPhoto().getUrl());
            } else {
                this.image = new File("src/main/resources/eu/telecomnancy/images/vierge.png");
            }
        }
        else{
            this.masquerButton.setVisible(false);
            this.uniteCoutTextField.setText("/");
        }
        this.modifImage = false;
    }

    @FXML
    public void ajoutDispoButtonClicked(ActionEvent event) {
        //Fonction qui ajoute les disponibilités en tant que nouvel élément de this.dateListView
        //On vérifie d'abord que les deux champs de date sont remplis
        if (this.debutPicker.getValue() != null && this.finPicker.getValue() != null) {
            Date debut = Date.from(this.debutPicker.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            Date fin = Date.from(this.finPicker.getValue().atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            
            // Vérifie si la date de début est avant la date actuelle
            LocalDate aujourdHui = LocalDate.now();
            Date aujourdHuiDate = Date.from(aujourdHui.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            if (debut.before(aujourdHuiDate)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Annonce");
                alert.setHeaderText("Poster une annonce");
                alert.setContentText("La date de début ne peut pas être antérieure à la date actuelle.");
                alert.showAndWait();
                return;
            }
            
            boolean creneauAjouteOuNon = false;
            // On vérifie d'abord que le date de début est bien avant la date de fin
            if (!debut.after(fin)) {
                //On vérifie ensuite que la date de début n'est pas déjà passée
                LocalDate Lhier = LocalDate.now().minusDays(1);
                Date hier = Date.from(Lhier.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
                if (debut.after(hier)) {
                    //On vérifie ensuite que le créneau n'est pas déjà dans la liste
                    for (int i = 0; i < this.dateListView.getItems().size(); i++) {
                        //On récupère le début et la fin du créneau existant
                        String[] creneauSplit = this.dateListView.getItems().get(i).split(" ");
                        String[] debutSplit = creneauSplit[1].split("/");
                        String[] finSplit = creneauSplit[3].split("/");
                        LocalDate debutCreneau = LocalDate.of(Integer.parseInt(debutSplit[2]), Integer.parseInt(debutSplit[1]), Integer.parseInt(debutSplit[0]));
                        LocalDate finCreneau = LocalDate.of(Integer.parseInt(finSplit[2]), Integer.parseInt(finSplit[1]), Integer.parseInt(finSplit[0]));
                        Date debutCreneauExistant = Date.from(debutCreneau.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
                        Date finCreneauExistant = Date.from(finCreneau.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
                        if (!(fin.before(debutCreneauExistant) || debut.after(finCreneauExistant))) {
                            creneauAjouteOuNon = true;
                        }
                    }
                    if (!creneauAjouteOuNon) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedCreneau = "De " + sdf.format(debut) + " à " + sdf.format(fin);
                        this.dateListView.getItems().add(formattedCreneau);
                        this.debutPicker.setValue(null);
                        this.finPicker.setValue(null);
                        this.modifDateListe = true;
                    }
                }
            }
        }
    }

    @FXML
    public void supprDispoButtonClicked(ActionEvent event){
        //Fonction qui supprime les disponibilités sélectionnées de this.dateListView
        ObservableList<String> creneauxAffiches = this.dateListView.getItems();
        ObservableList<String> creneauxSelectionnes = this.dateListView.getSelectionModel().getSelectedItems();
        for (String creneau : creneauxSelectionnes) {
            creneauxAffiches.remove(creneau);
            break;
        }
        this.modifDateListe = true;
    }


    @FXML
    public void ImageChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            //System.out.println("File selected: " + selectedFile.getAbsolutePath());
            this.image = selectedFile;
            this.modifImage = true;
            this.ai.setImage(new Image("file:"+this.image.getAbsolutePath()));
        }
    }

    @FXML
    public void posterButtonClicked(ActionEvent event) {
        try {
            try {
                Integer.parseInt(this.coutTextField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de Format");
                alert.setHeaderText("Erreur de saisie du coût");
                alert.setContentText("Le coût doit être un nombre entier valide.");
                alert.showAndWait();
                return;
            }

            if (!this.uniteCoutTextField.getText().startsWith("/")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Annonce");
                alert.setHeaderText("Poster une annonce");
                alert.setContentText("L'unité de coût doit commencer par un slash \"/\".\n Exemple : /heures /trajet /livraison");
                alert.showAndWait();
                throw new Exception("L'unité de coût doit commencer par un slash \"/\".");
            }

            //System.out.println("Vue_AnnonceAnnonceur_Panneau_Controller.posterButtonClicked()");
            if (this.titreTextField.getText().isEmpty() || this.descriptionTextField.getText().isEmpty() || this.coutTextField.getText().isEmpty() || this.uniteCoutTextField.getText().isEmpty() || this.localTextField.getText().isEmpty() || this.dateListView.getItems().isEmpty() || this.image == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Annonce");
                alert.setHeaderText("Poster une annonce");
                alert.setContentText("Veuillez remplir tous les champs.");

                alert.showAndWait();
                throw new Exception("Veuillez remplir tous les champs et ajouter une photo.");
            }
            int cout = Integer.parseInt(this.coutTextField.getText());
            Image photo;
            if (this.modifImage){
                //System.out.println("Vue_AnnonceAnnonceur_Panneau_Controller.posterButtonClicked(): modifImages");
                photo = new Image("file:"+this.image.getAbsolutePath());
            } else {
                photo = this.directDealing.getAnnonceAffichee().getPhoto();
            }
            if (this.modifDateListe){
                //System.out.println("Vue_AnnonceAnnonceur_Panneau_Controller.posterButtonClicked(): modifDateListe");
                this.disponibilites = new ArrayList<List<Date>>();
                for (String creneau : this.dateListView.getItems()) {
                    String[] creneauSplit = creneau.split(" ");
                    String[] debutSplit = creneauSplit[1].split("/");
                    String[] finSplit = creneauSplit[3].split("/");
                    LocalDate debut = LocalDate.of(Integer.parseInt(debutSplit[2]), Integer.parseInt(debutSplit[1]), Integer.parseInt(debutSplit[0]));
                    LocalDate fin = LocalDate.of(Integer.parseInt(finSplit[2]), Integer.parseInt(finSplit[1]), Integer.parseInt(finSplit[0]));
                    Date debutDate = Date.from(debut.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
                    Date finDate = Date.from(fin.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
                    List<Date> creneauDate = new ArrayList<Date>();
                    creneauDate.add(debutDate);
                    creneauDate.add(finDate);
                    this.disponibilites.add(creneauDate);
                }
            }
            if (this.directDealing.getAnnonceAffichee() != null){
                this.directDealing.getUtilisateurCourant().modifierAnnonce(this.directDealing.getAnnonceAffichee(), this.titreTextField.getText(), this.descriptionTextField.getText(), this.choiceAnnonce.getValue(), cout, this.uniteCoutTextField.getText(), this.localTextField.getText(), this.disponibilites, photo);
                Main.getInstance().changeScene("Page_MesAnnonces");
            }
            else{
                this.directDealing.getUtilisateurCourant().posterAnnonce(this.titreTextField.getText(), this.descriptionTextField.getText(), this.choiceAnnonce.getValue(), cout, this.uniteCoutTextField.getText(), this.localTextField.getText(), this.disponibilites, photo);
                this.directDealing.getMoteurDeRecherche().ajouterAnnonce(this.directDealing.getUtilisateurCourant().getAnnonces().get(this.directDealing.getUtilisateurCourant().getAnnonces().size()-1));
                Main.getInstance().changeScene("Page_MesAnnonces");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    } 

    @FXML
    public void masquerButtonClicked(ActionEvent event) {
        for (Annonce annonce : this.directDealing.getUtilisateurCourant().getAnnonces()){
            if (annonce.equals(this.directDealing.getAnnonceAffichee())){
                if (annonce.getVisible()){
                    annonce.setVisible(false);
                }
                else{
                    annonce.setVisible(true);
                }
                //System.out.println("Annonce visible : " + annonce.getVisible());
                break;
            }
        }
        Main.getInstance().changeScene("Page_MesAnnonces");
    }
    
    @Override
    public void reagir() {

    }
}