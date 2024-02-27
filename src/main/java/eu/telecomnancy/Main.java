package eu.telecomnancy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private static Main instance; //fournit un accès global à la fonction Main pour les autres classes
    
    private Stage primaryStage;
    private DirectDealing directDealing;
    private BorderPane panneau;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.directDealing = new DirectDealing(); // Initialisation de l'application
        instance = this;
        panneau = new BorderPane();

        Page_Accueil(panneau, directDealing);

        primaryStage.setTitle("DirectDealing");
        Scene scene = new Scene(panneau, 1300, 800);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void Page_Accueil(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderDemarrage = new FXMLLoader();
            loaderDemarrage.setLocation(getClass().getResource("Vue_Demarrage.fxml"));
            loaderDemarrage.setControllerFactory(iC->new Vue_Demarrage_Controller(directDealing));
            panneau.setCenter(loaderDemarrage.load());
            panneau.setTop(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_Inscription(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderInscription = new FXMLLoader();
            loaderInscription.setLocation(getClass().getResource("Vue_Inscription.fxml"));
            loaderInscription.setControllerFactory(iC->new Vue_Inscription_Controller(directDealing));
            panneau.setCenter(loaderInscription.load());
            panneau.setTop(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_Messagerie(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderMessagerie = new FXMLLoader();
            loaderMessagerie.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderMessagerie.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderMessagerie.load());
            loaderMessagerie = new FXMLLoader();
            loaderMessagerie.setLocation(getClass().getResource("Vue_Messagerie.fxml"));
            loaderMessagerie.setControllerFactory(iC->new Vue_Messagerie_Controller(directDealing));
            panneau.setCenter(loaderMessagerie.load());
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_Litige_Deals(BorderPane panneau, DirectDealing directDealing) { 
        try {
            System.out.println("Page_Litige_Deals");
            FXMLLoader loaderMenu = new FXMLLoader();
            loaderMenu.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderMenu.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderMenu.load());

            FXMLLoader loaderLitige = new FXMLLoader();
            loaderLitige.setLocation(getClass().getResource("Vue_Litige_Deals.fxml"));
            loaderLitige.setControllerFactory(iC->new Vue_Litige_Deals_Controller(directDealing));
            panneau.setCenter(loaderLitige.load());
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void Page_Message_Annonceur(BorderPane panneau, DirectDealing directDealing) { 
        try {

            FXMLLoader loaderMessagerie = new FXMLLoader();
            loaderMessagerie.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderMessagerie.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderMessagerie.load());

            BorderPane panneau2 = new BorderPane();
            panneau2.setPadding(new javafx.geometry.Insets(10, 20, 10, 10));

            FXMLLoader loadertest2= new FXMLLoader();
            loadertest2.setLocation(getClass().getResource("Vue_MessageAnnonceurTitre.fxml"));
            loadertest2.setControllerFactory(iC->new Vue_MessageAnnonceurTitre_Controller(directDealing));
            panneau2.setTop(loadertest2.load());

            FXMLLoader loadertest1= new FXMLLoader();
            loadertest1.setLocation(getClass().getResource("Vue_MessageAnnonceurControle.fxml"));
            loadertest1.setControllerFactory(iC->new Vue_MessageAnnonceurControle_Controller(directDealing));
            panneau2.setBottom(loadertest1.load());

            FXMLLoader loadertest3= new FXMLLoader();
            loadertest3.setLocation(getClass().getResource("Vue_MessageInfos.fxml"));
            loadertest3.setControllerFactory(iC->new Vue_MessageInfos_Controller(directDealing));
            panneau2.setLeft(loadertest3.load());

            FXMLLoader loadertest4= new FXMLLoader();
            loadertest4.setLocation(getClass().getResource("Vue_MessageGlobal.fxml"));
            loadertest4.setControllerFactory(iC->new Vue_MessageGlobal_Controller(directDealing));
            panneau2.setCenter(loadertest4.load());
        
            panneau.setCenter(panneau2);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void Page_Message_Demandeur(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderMessagerie = new FXMLLoader();
            loaderMessagerie.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderMessagerie.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderMessagerie.load());

            BorderPane panneau2 = new BorderPane();
            panneau2.setPadding(new javafx.geometry.Insets(10, 20, 10, 10));

            FXMLLoader loadertest2= new FXMLLoader();
            loadertest2.setLocation(getClass().getResource("Vue_MessageDemandeurTitre.fxml"));
            loadertest2.setControllerFactory(iC->new Vue_MessageDemandeurTitre_Controller(directDealing));
            panneau2.setTop(loadertest2.load());

            
            FXMLLoader loadertest1= new FXMLLoader();
            loadertest1.setLocation(getClass().getResource("Vue_MessageDemandeurControle.fxml"));
            loadertest1.setControllerFactory(iC->new Vue_MessageDemandeurControle_Controller(directDealing));
            panneau2.setBottom(loadertest1.load());

            FXMLLoader loadertest3= new FXMLLoader();
            loadertest3.setLocation(getClass().getResource("Vue_MessageInfos.fxml"));
            loadertest3.setControllerFactory(iC->new Vue_MessageInfos_Controller(directDealing));
            panneau2.setLeft(loadertest3.load());

            FXMLLoader loadertest4= new FXMLLoader();
            loadertest4.setLocation(getClass().getResource("Vue_MessageGlobal.fxml"));
            loadertest4.setControllerFactory(iC->new Vue_MessageGlobal_Controller(directDealing));
            panneau2.setCenter(loadertest4.load());
            
        
            panneau.setCenter(panneau2);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_Retour(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderMenu = new FXMLLoader();
            loaderMenu.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderMenu.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderMenu.load());

            FXMLLoader loaderRetour = new FXMLLoader();
            loaderRetour.setLocation(getClass().getResource("Vue_Retour.fxml"));
            loaderRetour.setControllerFactory(iC->new Vue_Retour_Controller(directDealing));
            panneau.setCenter(loaderRetour.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void Page_MesAnnonces(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderMesAnnonces = new FXMLLoader();
            loaderMesAnnonces.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderMesAnnonces.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderMesAnnonces.load());
            loaderMesAnnonces = new FXMLLoader();
            loaderMesAnnonces.setLocation(getClass().getResource("Vue_MesAnnonces.fxml"));
            loaderMesAnnonces.setControllerFactory(iC->new Vue_Mes_Annonces_Controller(directDealing));
            panneau.setCenter(loaderMesAnnonces.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_MesDeals(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderMesDeals = new FXMLLoader();
            loaderMesDeals.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderMesDeals.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderMesDeals.load());
            loaderMesDeals = new FXMLLoader();
            loaderMesDeals.setLocation(getClass().getResource("Vue_MesDeals.fxml"));
            loaderMesDeals.setControllerFactory(iC->new Vue_MesDeals_Controller(directDealing));
            panneau.setCenter(loaderMesDeals.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_Rechercher(BorderPane panneau, DirectDealing directDealing) { 
        try {
            FXMLLoader loaderRechercher = new FXMLLoader();
            loaderRechercher.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderRechercher.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderRechercher.load());
            loaderRechercher = new FXMLLoader();
            loaderRechercher.setLocation(getClass().getResource("Vue_Rechercher.fxml"));
            Vue_Rechercher_Controller c = new Vue_Rechercher_Controller(directDealing);
            Vue_Rechercher_Moteur_Controller m = new Vue_Rechercher_Moteur_Controller(directDealing);
            Vue_Rechercher_Annonces_Controller a = new Vue_Rechercher_Annonces_Controller(directDealing);
            loaderRechercher.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.Vue_Rechercher_Controller.class)) return c;
            else if (ic.equals(eu.telecomnancy.Vue_Rechercher_Moteur_Controller.class)) return m;
            else if (ic.equals(eu.telecomnancy.Vue_Rechercher_Annonces_Controller.class)) return a;
            else return null ; //par défault
            });
            panneau.setCenter(loaderRechercher.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_Profil(BorderPane panneau, DirectDealing directDealing) {
        try {
            FXMLLoader loaderProfil = new FXMLLoader();
            loaderProfil.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderProfil.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderProfil.load());
            loaderProfil = new FXMLLoader();
            loaderProfil.setLocation(getClass().getResource("Vue_Profil.fxml"));
            loaderProfil.setControllerFactory(iC->new Vue_Profil_Controller(directDealing));
            panneau.setCenter(loaderProfil.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_Creation_Annonce(BorderPane panneau, DirectDealing directDealing) { 
        try {
            //System.out.println("Page_Creation_Annonce");
            FXMLLoader loaderAnnonceInfos = new FXMLLoader();
            loaderAnnonceInfos.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderAnnonceInfos.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderAnnonceInfos.load());
            loaderAnnonceInfos = new FXMLLoader();
            loaderAnnonceInfos.setLocation(getClass().getResource("Vue_AnnonceAnnonceur.fxml"));
            Vue_AnnonceAnnonceur_Controller aa = new Vue_AnnonceAnnonceur_Controller(directDealing);
            Vue_AnnonceInfos_Controller ai = new Vue_AnnonceInfos_Controller(directDealing, null);
            Vue_AnnonceAnnonceur_Panneau_Controller ap = new Vue_AnnonceAnnonceur_Panneau_Controller(directDealing, ai);
            loaderAnnonceInfos.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.Vue_AnnonceAnnonceur_Controller.class)) return aa;
            else if (ic.equals(eu.telecomnancy.Vue_AnnonceInfos_Controller.class)) return ai;
            else if (ic.equals(eu.telecomnancy.Vue_AnnonceAnnonceur_Panneau_Controller.class)) return ap;
            else return null ; //par défault
            });
            panneau.setCenter(loaderAnnonceInfos.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_ModifierAnnonce(BorderPane panneau, DirectDealing directDealing) { 
        try {
            //System.out.println("Page_Modification_Annonce");
            FXMLLoader loaderAnnonceInfos = new FXMLLoader();
            loaderAnnonceInfos.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderAnnonceInfos.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderAnnonceInfos.load());
            loaderAnnonceInfos = new FXMLLoader();
            loaderAnnonceInfos.setLocation(getClass().getResource("Vue_AnnonceAnnonceur.fxml"));
            Vue_AnnonceAnnonceur_Controller aa = new Vue_AnnonceAnnonceur_Controller(directDealing);
            Vue_AnnonceInfos_Controller ai = new Vue_AnnonceInfos_Controller(directDealing, this.directDealing.getAnnonceAffichee());
            Vue_AnnonceAnnonceur_Panneau_Controller ap = new Vue_AnnonceAnnonceur_Panneau_Controller(directDealing, ai);
            loaderAnnonceInfos.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.Vue_AnnonceAnnonceur_Controller.class)) return aa;
            else if (ic.equals(eu.telecomnancy.Vue_AnnonceInfos_Controller.class)) return ai;
            else if (ic.equals(eu.telecomnancy.Vue_AnnonceAnnonceur_Panneau_Controller.class)) return ap;
            else return null ; //par défault
            });
            panneau.setCenter(loaderAnnonceInfos.load());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Page_VoirAnnonce(BorderPane panneau, DirectDealing directDealing) { 
        try {
            //System.out.println("Page_Modification_Annonce");
            FXMLLoader loaderAnnonceInfos = new FXMLLoader();
            loaderAnnonceInfos.setLocation(getClass().getResource("Vue_Menu.fxml"));
            loaderAnnonceInfos.setControllerFactory(iC->new Vue_Menu_Controller(directDealing));
            panneau.setTop(loaderAnnonceInfos.load());
            loaderAnnonceInfos = new FXMLLoader();
            loaderAnnonceInfos.setLocation(getClass().getResource("Vue_AnnonceDemandeur.fxml"));
            Vue_AnnonceDemandeur_Controller aa = new Vue_AnnonceDemandeur_Controller(directDealing);
            Vue_AnnonceInfos_Controller ai = new Vue_AnnonceInfos_Controller(directDealing, this.directDealing.getAnnonceAffichee());
            Vue_AnnonceDemandeur_Panneau_Controller ap = new Vue_AnnonceDemandeur_Panneau_Controller(directDealing);
            loaderAnnonceInfos.setControllerFactory(ic -> {
            if (ic.equals(eu.telecomnancy.Vue_AnnonceDemandeur_Controller.class)) return aa;
            else if (ic.equals(eu.telecomnancy.Vue_AnnonceInfos_Controller.class)) return ai;
            else if (ic.equals(eu.telecomnancy.Vue_AnnonceDemandeur_Panneau_Controller.class)) return ap;
            else return null ; //par défault
            });
            panneau.setCenter(loaderAnnonceInfos.load());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Méthode publique pour changer la scène
    public void changeScene(String page) {
        if ("Page_Accueil".equals(page)) {
            Page_Accueil(panneau, directDealing);
        }
        else if ("Page_Inscription".equals(page)) {
            Page_Inscription(panneau, directDealing);
        }
        else if ("Page_Messagerie".equals(page)){
            Page_Messagerie(panneau, directDealing);
        }
        else if ("Page_Message_Annonceur".equals(page)) {
            Page_Message_Annonceur(panneau, directDealing);
        }
        else if ("Page_Message_Demandeur".equals(page)) {
            Page_Message_Demandeur(panneau, directDealing);
        }
        else if ("Page_MesAnnonces".equals(page)){
            Page_MesAnnonces(panneau, directDealing);
        }
        else if ("Page_MesDeals".equals(page)) {
            Page_MesDeals(panneau, directDealing);
        }
        else if ("Page_Rechercher".equals(page)) {
            Page_Rechercher(panneau, directDealing);
        }
        else if ("Page_Profil".equals(page)) {
            Page_Profil(panneau, directDealing);
        }
        else if ("Page_Creation_Annonce".equals(page)) {
            Page_Creation_Annonce(panneau, directDealing);
        }
        else if ("Page_ModifierAnnonce".equals(page)) {
            Page_ModifierAnnonce(panneau, directDealing);
        }
        else if ("Page_VoirAnnonce".equals(page)) {
            Page_VoirAnnonce(panneau, directDealing);
        }
        else if ("Page_Litige_Deals".equals(page)) {
            Page_Litige_Deals(panneau, directDealing);
        }
        else if ("Page_Retour".equals(page)) {
            Page_Retour(panneau, directDealing);
        }
        else {
            System.out.println("Erreur : page inconnue");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}