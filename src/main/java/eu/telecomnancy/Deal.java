package eu.telecomnancy;

import java.util.Date;
import java.util.List;

import javafx.scene.image.Image;

public class Deal {

    private Annonce annonceAssociee;
    private String titre;
    private Image photo;
    private int cout;
    private String uniteCout;
    private Date debut;
    private Date fin;
    private int note;
    private Boolean rendu;
    private Utilisateur demandeur;

    public Deal(Conversation conversation) {
        annonceAssociee = conversation.getAnnonce();
        titre = new String(annonceAssociee.getTitre());
        photo = annonceAssociee.getPhoto();
        
        cout = conversation.getMontantDeLOffre();
        uniteCout = new String(annonceAssociee.getUniteCout());
        rendu = false;
        demandeur = conversation.getDemmandeur();
    }

    public Image getPhoto() {
        return photo;
    }

    public String getTitre() {
        return titre;
    }

    public int getCout() {
        return cout;
    }

    public String getUniteCout() {
        return uniteCout;
    }

    public Annonce getAnnonce() {
        return annonceAssociee;
    }

    public void setNote(int note){
        this.note = note;
        this.annonceAssociee.addNote(note);    
    }

    public int getNote(){
        return note;
    }

    public void setRendu(Boolean rendu){
        this.rendu = rendu;
    }

    public Boolean getRendu(){
        return rendu;
    }

    public void rendreEtEnvoyer(int note){
        setNote(note);
        setRendu(true);
        //transaction
        annonceAssociee.getProprietaire().addFlorainLibre(this.cout);
        demandeur.removeFlorainReservee(this.cout);
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }

}


