package eu.telecomnancy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.scene.image.Image;

public class Annonce {

    private Utilisateur proprietaire;
    private String titre;
    private String description;
    private String typeAnnonce;
    private int cout;
    private String uniteCout;
    private String localisation;
    private Boolean visible;
    private Image photo;
    private List<List<Date>> disponibilites = new ArrayList<>(); //TODO système pour gérer les dates disponibles
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private double sommeNotation;
    private double nombreNotation;

    public Annonce(Utilisateur proprietaire, String titre, String description, String typeAnnonce, int cout, String uniteCout, String localisation, List<List<Date>> disponibilites, Image photo){
        this.proprietaire = proprietaire;
        this.titre = titre;
        this.description = description;
        this.typeAnnonce = typeAnnonce;
        this.cout = cout;
        this.uniteCout = uniteCout;
        this.localisation = localisation;
        this.disponibilites = disponibilites;
        this.photo = photo;
        this.visible = true;
    }

    // GESTION CRENEAUX
    // afficherCreneauxDispo()
    // estCreneauDisponible(Date debutCreneau, Date finCreneau)  boolean
    // ajouterCreneauDispo(Date debutCreneauDispo, Date finCreneauDispo)
    // retirerCreneauDispo(Date debutCreneauDispo, Date finCreneauDispo)  boolean
    // reserverCreneau(Date debutCreneau, Date finCreneau)  boolean

    public void afficherCreneauxDispo() {
        //System.out.println("Créneaux disponibles :");
        if (disponibilites.isEmpty()) {
            //System.out.println("- Aucun créneau");
        } else {
            for(List<Date> creneau : disponibilites) {
                Date debutCreneau = creneau.get(0);
                Date finCreneau = creneau.get(1);
                //System.out.println("- Créneau du " + sdf.format(debutCreneau) + " au " + sdf.format(finCreneau));
            }
        }
    }

    public void ajouterCreneauDispo(Date debutCreneauDispo, Date finCreneauDispo) {
        boolean creneauAjouteOuNon = false;
    
        for (int i = 0; i < disponibilites.size(); i++) {
            List<Date> creneau = disponibilites.get(i);
            Date debutCreneauExistant = creneau.get(0);
            Date finCreneauExistant = creneau.get(1);
    
            // Vérifier si les créneaux se chevauchent
            if (!(finCreneauDispo.before(debutCreneauExistant) || debutCreneauDispo.after(finCreneauExistant))) {
                Date newDebut = debutCreneauDispo.before(debutCreneauExistant) ? debutCreneauDispo : debutCreneauExistant;
                Date newFin = finCreneauDispo.after(finCreneauExistant) ? finCreneauDispo : finCreneauExistant;
    
                creneau.set(0, newDebut);
                creneau.set(1, newFin);
                creneauAjouteOuNon = true;
                break;
            }
        }
    
        // Si le nouveau créneau n'est pas inclus dans un créneau existant, on l'ajoute
        if (!creneauAjouteOuNon) {
            List<Date> nouveauCreneauDispo = new ArrayList<>();
            nouveauCreneauDispo.add(debutCreneauDispo);
            nouveauCreneauDispo.add(finCreneauDispo);
            disponibilites.add(nouveauCreneauDispo);
        }
    }

    public boolean retirerCreneauDispo(Date debutCreneauDispo, Date finCreneauDispo) {
        if (!estCreneauDisponible(debutCreneauDispo, finCreneauDispo)) {
            return false;
        }
        else { 
            for (int i = 0; i < disponibilites.size(); i++) {
                List<Date> creneau = disponibilites.get(i);
                Date debutCreneauExistant = creneau.get(0);
                Date finCreneauExistant = creneau.get(1);
        
                if (!finCreneauDispo.before(debutCreneauExistant) && !debutCreneauDispo.after(finCreneauExistant)) {
                    // Entièrement inclus dans un créneau existant
                    if (debutCreneauDispo.after(debutCreneauExistant) && finCreneauDispo.before(finCreneauExistant)) {
                        List<Date> newCreneauPost = new ArrayList<>();
                        newCreneauPost.add(new Date(finCreneauDispo.getTime() + 1));
                        newCreneauPost.add(finCreneauExistant);
        
                        creneau.set(1, new Date(debutCreneauDispo.getTime() - 1));
                        disponibilites.add(i + 1, newCreneauPost);
                        return true;
                    }
        
                    // Partiellement inclus dans un créneau existant
                    if (debutCreneauDispo.equals(debutCreneauExistant) && finCreneauDispo.before(finCreneauExistant)) {
                        creneau.set(0, new Date(finCreneauDispo.getTime() + 1));
                        return true;
                    }
                    if (debutCreneauDispo.after(debutCreneauExistant) && finCreneauDispo.equals(finCreneauExistant)) {
                        creneau.set(1, new Date(debutCreneauDispo.getTime() - 1));
                        return true;
                    }
        
                    // Egal à un créneau existant
                    if (debutCreneauDispo.equals(debutCreneauExistant) && finCreneauDispo.equals(finCreneauExistant)) {
                        disponibilites.remove(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean estCreneauDisponible(Date debutCreneau, Date finCreneau) {
        for (List<Date> creneau : disponibilites) {
            Date debutDispo = creneau.get(0);
            Date finDispo = creneau.get(1);

            if (!debutCreneau.before(debutDispo) && !finCreneau.after(finDispo)) {
                //System.out.println("Créneau de " + sdf.format(debutCreneau) + " à " + sdf.format(finCreneau) + " disponible");
                return true;
            }
        }
        //System.out.println("Aucun créneau disponible");
        return false;
    }

    public boolean reserverCreneau(Date debutCreneau, Date finCreneau) {
        //System.out.println("Réservation du créneau de " + sdf.format(debutCreneau) + " à " + sdf.format(finCreneau));

        Date currentDate = new Date();

        if (debutCreneau.before(currentDate)) {
            //System.out.println("Impossible de réserver un créneau dans le passé");
        return false;
    }

        if (!estCreneauDisponible(debutCreneau, finCreneau)) {
            //System.out.println("Créneau indisponible");
            return false;
        }

        for (int i = 0; i < disponibilites.size(); i++) {
            List<Date> creneauDispo = disponibilites.get(i);
            Date debutDispo = creneauDispo.get(0);
            Date finDispo = creneauDispo.get(1);

            if (!debutCreneau.before(debutDispo) && !finCreneau.after(finDispo)) {
                Calendar cal = Calendar.getInstance();
                cal.clear();

                if (debutCreneau.equals(debutDispo) && finCreneau.equals(finDispo)) {
                    disponibilites.remove(i);
                } else if (debutCreneau.equals(debutDispo)) {
                    cal.setTime(finCreneau);
                    cal.add(Calendar.DAY_OF_MONTH, 1); // Jour suivant
                    creneauDispo.set(0, cal.getTime());
                } else if (finCreneau.equals(finDispo)) {
                    cal.setTime(debutCreneau);
                    cal.add(Calendar.DAY_OF_MONTH, -1); // Jour précédent
                    creneauDispo.set(1, cal.getTime());
                } else {
                    // Raccourcir le premier créneau disponible
                    cal.setTime(debutCreneau);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    creneauDispo.set(1, cal.getTime());

                    // Ajouter un nouveau créneau pour la période restante après la réservation
                    List<Date> nouveauCreneau = new ArrayList<>();
                    cal.setTime(finCreneau);
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    nouveauCreneau.add(cal.getTime());
                    nouveauCreneau.add(finDispo);
                    disponibilites.add(i + 1, nouveauCreneau);
                }
                return true;
            }
        }
        return false;
    }

    public boolean libererCreneau(Date debutCreneau, Date finCreneau) {
        //System.out.println("Ajout du créneau de " + sdf.format(debutCreneau) + " à " + sdf.format(finCreneau) + " aux disponibilités");
    
        // Créer un nouveau créneau et l'ajouter aux disponibilités
        List<Date> nouveauCreneau = new ArrayList<>();
        nouveauCreneau.add(debutCreneau);
        nouveauCreneau.add(finCreneau);
        disponibilites.add(nouveauCreneau);
    
        return true;
    }    

    public Utilisateur getProprietaire(){
        return proprietaire;
    }

    // Getter et setter pour localisation
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    // Getter et setter pour cout
    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    // Getter et setter pour titre
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    // Getter et setter pour description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter et setter pour uniteCout
    public String getUniteCout() {
        return uniteCout;
    }

    public void setUniteCout(String uniteCout) {
        this.uniteCout = uniteCout;
    }

    // Getter et setter pour visible
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    // Getter et setter pour photo
    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    // Getter et setter pour disponibilites
    public List<List<Date>> getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(List<List<Date>> disponibilites) {
        this.disponibilites = disponibilites;
    }

    public void setTypeAnnonce(String typeAnnonce) {
        this.typeAnnonce = typeAnnonce;
    }

    public String getTypeAnnonce() {
        return typeAnnonce;
    }

    public void addNote(double note){
        this.sommeNotation += note;
        this.nombreNotation++;
    }

    public double getNotation(){
        return this.sommeNotation/this.nombreNotation;
    }
}
