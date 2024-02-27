package eu.telecomnancy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conversation {
    
    private List<Message> messages = new ArrayList<>();
    private Annonce annonceAssociee;
    private String proposition;
    private int montantDeLOffre;
    private Date debut;
    private Date fin;
    private Utilisateur demandeur;
    private Boolean isDecisionTakenByAnnoncer;
    private DirectDealing directDealing;
    private Deal deal;


    public Conversation(Annonce annonceAssociee, String proposition, int montantDeLOffre, Date debut, Date fin, Utilisateur demandeur){
        this.annonceAssociee = annonceAssociee;
        this.proposition = proposition;
        this.montantDeLOffre = montantDeLOffre;
        this.debut = debut;
        this.fin = fin;
        this.demandeur = demandeur;
        //Pour l'affichage des dates, on utilise le format suivant : "dd/MM/yyyy"
        String debutStr = new String(new SimpleDateFormat("dd/MM/yyyy").format(this.debut));
        String finStr = new String(new SimpleDateFormat("dd/MM/yyyy").format(this.fin));
        this.messages.add(new Message(this.demandeur, proposition + "\n" + "Montant de l'offre: " + montantDeLOffre + " florains" + annonceAssociee.getUniteCout() + "\nDates: " + debutStr + " - " + finStr));
        demandeur.addConversation(this);
        annonceAssociee.getProprietaire().addConversation(this);
        isDecisionTakenByAnnoncer = false;
    }

    //Conversation avec l'admin pour un litige à partir des annonces
    public Conversation(Annonce annonceAssociee, int montantDeLOffre, Date debut, Date fin, Utilisateur demandeur, DirectDealing directDealing, String message){
        this.directDealing = directDealing;
        this.annonceAssociee = annonceAssociee;
        this.montantDeLOffre = montantDeLOffre;
        this.debut = debut;
        this.fin = fin;
        this.demandeur = demandeur;
        this.messages.add(new Message(demandeur, message));
        demandeur.addConversation(this);
        directDealing.getAdmin().addConversation(this);
        isDecisionTakenByAnnoncer = true;
    }

    //Conversation avec l'admin pour un litige à partir des deals
    /*public Conversation(Deal deal,  Utilisateur demandeur, DirectDealing directDealing, String message){
        this.directDealing = directDealing;
        this.annonceAssociee = deal.getAnnonce();
        this.montantDeLOffre = deal.getCout();
        this.disponibilites = deal.getAnnonce().getDisponibilites();
        this.demandeur = demandeur;
        this.messages.add(new Message(demandeur, message));
        demandeur.addConversation(this);
        directDealing.getAdmin().addConversation(this);
        isDecisionTakenByAnnoncer = false;
    }*/

    public void addMessage(Utilisateur emmeteur, String message){
        messages.add(new Message(emmeteur, message));
    }

    public List<Message> getMessages(){
        return messages;
    }

    public int getMontantDeLOffre(){
        return montantDeLOffre;
    }

    public Annonce getAnnonce(){
        return annonceAssociee;
    }

    public Utilisateur getInterlocutorOf(Utilisateur utilisateur){
        if(annonceAssociee.getProprietaire() == utilisateur){
            return demandeur;
        }else{
            return annonceAssociee.getProprietaire();
        }
    }

    public Utilisateur getDemmandeur(){
        return demandeur;
    }

    public void setIsDecisionTakenByAnnoncerValue(Boolean value){
        this.isDecisionTakenByAnnoncer = value;
    }

    public Boolean isDecisionTakenByAnnoncer(){
        return this.isDecisionTakenByAnnoncer;
    }

    public Date getDateDebut(){
        return debut;
    }

    public Date getDateFin(){
        return fin;
    }
}
