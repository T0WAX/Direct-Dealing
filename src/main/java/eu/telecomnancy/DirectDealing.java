package eu.telecomnancy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javafx.scene.image.Image;

public class DirectDealing {
    private ArrayList<Observateur> obs = new ArrayList<>();
    private Utilisateur utilisateurCourant;
    private Annonce annonceAffichee;
    private Conversation conversationAffichee;
    private Deal dealAffichee;
    private MoteurDeRecherche moteurDeRecherche;
    private HashMap<String, Utilisateur> mapUtilisateurs = new HashMap<>(); // identifiant (attention c'est pas l'idUser) | Utilisateur


    public DirectDealing(){
        List<List<Date>> disponibilitesTest = new ArrayList<>();

        List<Date> premierCreneauTest = new ArrayList<>();
        List<Date> deuxiemeCreneauTest = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        premierCreneauTest.add(calendar.getTime());
        calendar.clear();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        premierCreneauTest.add(calendar.getTime());
        calendar.clear();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        deuxiemeCreneauTest.add(calendar.getTime());
        calendar.clear();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        deuxiemeCreneauTest.add(calendar.getTime());

        disponibilitesTest.add(premierCreneauTest);
        disponibilitesTest.add(deuxiemeCreneauTest);
        
        //Utilisation Admin
        Utilisateur Admin = new Utilisateur(mapUtilisateurs.size(), "admin", "admin", "JE SUIS L ADMIN", "admin", "admin");
        mapUtilisateurs.put(Admin.getIdendtifiant(), Admin);
        
        //Utilisateur Bob
        Utilisateur Bob = new Utilisateur(mapUtilisateurs.size(), "bobby", "bob", "JE SUIS BOB", "Fergusson", "Bob");
        mapUtilisateurs.put(Bob.getIdendtifiant(), Bob);
        this.utilisateurCourant = Bob;

        //Utilisateur Victor
        Utilisateur Victor = new Utilisateur(mapUtilisateurs.size(), "victor", "victor", "Je suis Victor", "Ménestrel", "Victor");
        mapUtilisateurs.put(Bob.getIdendtifiant(), Bob);
        Victor.setFlorainLibre(10000);
        this.utilisateurCourant = Victor;

        //Utilisateur Pierre
        Utilisateur Pierre = new Utilisateur(mapUtilisateurs.size(), "pierrot", "pierre", "JE SUIS PIERRE", "Lafeuille", "Pierre");
        mapUtilisateurs.put(Pierre.getIdendtifiant(), Pierre);

        //Utilisateur Pascal
        Utilisateur Pascal = new Utilisateur(mapUtilisateurs.size(), "pascalou", "pascal", "JE SUIS PASCAL", "Lefeu", "Pascal");
        mapUtilisateurs.put(Pascal.getIdendtifiant(), Pascal);

        Bob.posterAnnonce("Tonte pelouse de jardin", "Je tonds votre pelouse de jardin tout en vous préparant du café", "service", 25, "/m²", "Nancy", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/tondre_pelouse.png")));
        Bob.posterAnnonce("Location tractopelle", "Je loue un tractopelle de très bonne qualité. Vous pouvez même monter à deux dedans", "materiel", 140, "/jour", "Nancy", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/tractopelle.png")));
        Bob.posterAnnonce("Location camionnette lugubre", "Je loue ma camionnette pour vous offir la possibilité d'effectuer d'y efectuer des bails sombres.", "materiel", 20, "/jour", "Villers-les-Nancy", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/camionnette.png")));
        Pierre.posterAnnonce("Nettoyage façade (visage)", "Je nettoie votre façade (visage)", "service", 50, "/visage", "Nancy", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/nettoyage_visage.png")));
        Pierre.posterAnnonce("Location tronçonneuse", "Je loue ma tronçonneuse de la marque CoupeVite. Elle est prête à l'emploi et a très peu servie.", "materiel", 10, "/jour", "Nancy", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/tronconneuse.png")));
        Pascal.posterAnnonce("Peindre un arbre", "Je peints littéralement des arbres", "service", 200, "/arbre", "Woippy", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/peinture_arbre.png")));
        Pascal.posterAnnonce("Location voiture tah les fous", "Je loue une voiture tah les fous qui va tellement vite que vous allez en perdre vos cheveux", "materiel", 340, "/jour", "Paris", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/voiture_de_sport.png")));
        Pascal.posterAnnonce("Location fusée qui va t'envoyer au 7ème ciel", "Je loue une une fusée de qualité Made-In-China. Je l'ai déjà testé plusieurs fois et je peux vous assurer que votre caleçon n'en sortira pas indemne.", "materiel", 3000000, "/jour", "Guyane", deepCopyDisponibilites(disponibilitesTest), new Image(getClass().getResourceAsStream("/eu/telecomnancy/images/Fusee.png")));

        Date dans10jours = Date.from(LocalDate.now().plusDays(12).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date dans20jours = Date.from(LocalDate.now().plusDays(14).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        this.utilisateurCourant.makeAnOffer(mapUtilisateurs.get("pierrot").getAnnonces().get(0), "Je vous propose 10 Florains pour votre annonce", 10, dans10jours, dans20jours);

        moteurDeRecherche = new MoteurDeRecherche();
        for(Utilisateur utilisateur : mapUtilisateurs.values()){
            for(Annonce annonce : utilisateur.getAnnonces()){
                //System.out.println("Utilisateur : " + utilisateur.getNom() + " | Annonce : " + annonce.getTitre());
                moteurDeRecherche.ajouterAnnonce(annonce);
            }
        }
        mapUtilisateurs.put(utilisateurCourant.getIdendtifiant(), utilisateurCourant);
    }

    private List<List<Date>> deepCopyDisponibilites(List<List<Date>> original) {
        List<List<Date>> copy = new ArrayList<>();
        for (List<Date> dateList : original) {
            List<Date> dateListCopy = new ArrayList<>();
            for (Date date : dateList) {
                dateListCopy.add((Date) date.clone());
            }
            copy.add(dateListCopy);
        }
        return copy;
    }
    

    public Boolean login(String identifiant, String motDePasse){
        if(mapUtilisateurs.get(identifiant) != null && mapUtilisateurs.get(identifiant).getMotDePasse().equals(motDePasse)){
            utilisateurCourant = mapUtilisateurs.get(identifiant);
            return true;
        } else{
            return false;
        }
    }

    public Boolean signin(String identifiant, String motDePasse, String confirmationMDP, String description, String nom, String prenom){
        if(mapUtilisateurs.get(identifiant) == null){
            if(!motDePasse.equals("") && !nom.equals("") && !prenom.equals("") && !description.equals("")){
                if(confirmationMDP.equals(motDePasse)){
                    utilisateurCourant = new Utilisateur(mapUtilisateurs.size(), identifiant, motDePasse, description, nom, prenom);
                    mapUtilisateurs.put(utilisateurCourant.getIdendtifiant(), utilisateurCourant);
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        } 
    }

    public HashMap<String, Utilisateur> getMapUtilisateurs(){
        return mapUtilisateurs;
    }

    public void accepterOffre(Conversation conversation){
        utilisateurCourant.accepterOffre(conversation);
    }

    public void ajouterObservateur(Observateur o){
        obs.add(o);
    }

    public void supprimerObservateur(Observateur o){
        obs.remove(o);
    }

    public void notifierObservateursMoteur(){
        for(Observateur o : obs){
            if (o instanceof Vue_Rechercher_Annonces_Element_Controller || o instanceof Vue_Rechercher_Annonces_Controller) {
                o.reagir();
            }
        }
    }

    public void notifierObservateursMessage(){
        for(Observateur o : obs){
            if (o instanceof Vue_MessageGlobal_Controller || o instanceof Vue_MessageAnnonceurControle_Controller || o instanceof Vue_MessageDemandeurControle_Controller) {
                o.reagir();
            }
        }
    }

    public void notifierObservateurs(){
        for(Observateur o : obs){
            o.reagir();
            //System.out.println(o.getClass());
        }
    }

    public Utilisateur getUtilisateurCourant(){
        return this.utilisateurCourant;
    }

    public void setUtilisateurCourant(Utilisateur utilisateurCourant){
        this.utilisateurCourant = utilisateurCourant;
    }

    public MoteurDeRecherche getMoteurDeRecherche(){
        return this.moteurDeRecherche;
    }

    public void setMoteurDeRecherche(MoteurDeRecherche moteurDeRecherche){
        this.moteurDeRecherche = moteurDeRecherche;
    }


    public Annonce getAnnonceAffichee(){
        return this.annonceAffichee;
    }

    public void setAnnonceAffichee(Annonce annonceAffichee){
        this.annonceAffichee = annonceAffichee;
    }

    public Conversation getConversationAffichee(){
        return this.conversationAffichee;
    }

    public void setConversationAffichee(Conversation conversation){
        this.conversationAffichee = conversation;
    }

    public void sendMessage(String message){
        getConversationAffichee().addMessage(getUtilisateurCourant(), message);
    }

    public void refuserOffre() {
        if(utilisateurCourant.equals(conversationAffichee.getAnnonce().getProprietaire())){
            utilisateurCourant.refuserOffre(conversationAffichee);
        }else{
            System.out.println("Erreur refuser offre, il faut etre proprietaire de l'offre pour prendre une telle décision.");
        }
    }

    public void accepterOffre() {
        if(utilisateurCourant.equals(conversationAffichee.getAnnonce().getProprietaire())){
            utilisateurCourant.accepterOffre(conversationAffichee);
        }else{
            System.out.println("Erreur refuser offre, il faut etre proprietaire de l'offre pour prendre une telle décision.");
        }
    }

    public Utilisateur getAdmin(){
        return this.mapUtilisateurs.get("admin");
    }

    public Deal getDealAffichee(){
        return this.dealAffichee;
    }

    public void setDealAffichee(Deal dealAffichee){
        this.dealAffichee = dealAffichee;
    }
}
