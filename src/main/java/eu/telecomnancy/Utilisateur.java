package eu.telecomnancy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.image.Image;

public class Utilisateur {
    private int idUser;
    private int florainLibre;
    private int florainReservee;
    private Boolean modeSommeil;
    private String identifiant;
    private String motDePasse;
    private String description;
    private String nom;
    private String prenom;
    private List<Annonce> annonces = new ArrayList<>();
    private List<Deal> deals = new ArrayList<>();
    private List<Conversation> conversations = new ArrayList<>();

    public Utilisateur(int idUser, String identifiant, String motDePasse, String description, String nom, String prenom){
        this.idUser = idUser;
        this.florainLibre = 100;
        this.florainReservee = 0;
        this.modeSommeil = false;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.description = description;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public int getFlorain(){
        return florainLibre + florainReservee;
    }

    public int getFlorainLibre(){
        return florainLibre;
    }

    public int getFlorainReservee(){
        return florainReservee;
    }

    public void setFlorainLibre(int florain) {
        this.florainLibre = florain;
    }

    public void addFlorainLibre(int florain) {
        this.florainLibre = this.florainLibre + florain;
    }

    public void removeFlorainLibre(int florain) {
        this.florainLibre = this.florainLibre - florain;
    }

    public void setFlorainReservee(int florain) {
        this.florainReservee = florain;
    }

    public void addFlorainReservee(int florain) {
        this.florainReservee = this.florainReservee + florain;
    }

    public void removeFlorainReservee(int florain) {
        this.florainReservee = this.florainReservee - florain;
    }

    public void reserverFlorain(int florain){
        if(florainLibre >= florain){
            removeFlorainLibre(florain);
            addFlorainReservee(florain);
        }else{
            System.out.println("Erreur : pas assez de florain pour effectuer cette opération.");
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public String getIdendtifiant() {
        return identifiant;
    }

    public Boolean getModeSommeil() {
        return modeSommeil;
    }

    public void setModeSommeil(Boolean modeSommeil) {
        this.modeSommeil = modeSommeil;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Getter et setter pour annonces
    public List<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }
 
    public void posterAnnonce(String titre, String description, String typeAnnonce, int cout, String uniteCout, String local, List<List<Date>> disponibilites, Image image) {
        Annonce nouvelleAnnonce = new Annonce(this, titre, description, typeAnnonce, cout, uniteCout, local, disponibilites, image);
        annonces.add(nouvelleAnnonce);
    }

    public void modifierAnnonce(Annonce annonce, String titre, String description, String type, int cout, String uniteCout, String local, List<List<Date>> disponibilites, Image image) {
        if (type.equals("service"))
        {
            annonce.setTitre(titre);
            annonce.setDescription(description);
            annonce.setCout(cout);
            annonce.setUniteCout(uniteCout);
            annonce.setLocalisation(local);
            annonce.setDisponibilites(disponibilites);
            annonce.setPhoto(image);
        }
        else if (type.equals("materiel"))
        {
            annonce.setTitre(titre);
            annonce.setDescription(description);
            annonce.setCout(cout);
            annonce.setUniteCout(uniteCout);
            annonce.setLocalisation(local);
            annonce.setDisponibilites(disponibilites);
            annonce.setPhoto(image);
        }
    }

    // Getter et setter pour deals
    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    // Getter et setter pour conversations
    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public void addConversation(Conversation conversation) {
        this.conversations.add(conversation);
    }

    public void makeAnOffer(Annonce annonceAssociee, String proposition, int montantDeLOffre, Date debut, Date fin) {
        if(annonceAssociee.estCreneauDisponible(debut, fin)){
            new Conversation(annonceAssociee, proposition, montantDeLOffre, debut, fin, this);
        }
    }

    public void accepterOffre(Conversation conversation) {
        if(this == conversation.getAnnonce().getProprietaire() && conversation.getInterlocutorOf(this).getFlorainLibre() >= conversation.getMontantDeLOffre() && conversation.getAnnonce().reserverCreneau(conversation.getDateDebut(), conversation.getDateFin())){
            conversation.setIsDecisionTakenByAnnoncerValue(true);
            conversation.addMessage(this, this.getNom() + "" + this.getPrenom() + " a pris la décision d'accepter votre offre !");
            conversation.getInterlocutorOf(this).removeFlorainLibre(conversation.getMontantDeLOffre());
            conversation.getInterlocutorOf(this).addFlorainReservee(conversation.getMontantDeLOffre());

            Deal nouveauDeal = new Deal(conversation);
            deals.add(nouveauDeal);
            conversation.getInterlocutorOf(this).addDeal(nouveauDeal);
        }        
    }

    public void refuserOffre(Conversation conversation){
        if(this == conversation.getAnnonce().getProprietaire()){
            conversation.setIsDecisionTakenByAnnoncerValue(true);
            conversation.addMessage(this, "L'annonceur a pris la décision de refuser votre offre !");
        }
    }

    public void addDeal(Deal deal){
        this.deals.add(deal);
    }
}
