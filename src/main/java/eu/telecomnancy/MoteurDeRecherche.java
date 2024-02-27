package eu.telecomnancy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoteurDeRecherche {
    private List<Annonce> annonces;
    private List<Annonce> annoncesFiltrees;

    public MoteurDeRecherche(){
        this.annonces = new ArrayList<>();
        this.annoncesFiltrees = new ArrayList<>();
        
    }

    public void ajouterAnnonce(Annonce annonce){
        this.annonces.add(annonce);
        this.annoncesFiltrees.add(annonce);
    }

    public void supprimerAnnonce(Annonce annonce){
        this.annonces.remove(annonce);
        this.annoncesFiltrees.remove(annonce);
    }

    public void rechercher(Boolean filtreService, Boolean filtreMateriel, LocalDate dateDebut, LocalDate dateFin, String localisation, String recherche) {
        this.annoncesFiltrees.clear();
        String[] motsRecherche = recherche.toLowerCase().split("\\s+"); // Divise la recherche en mots
        
        dateDebut = dateDebut != null ? dateDebut : LocalDate.of(1900, 1, 1);
        dateFin = dateFin != null ? dateFin : LocalDate.of(3000, 1, 1);
        
        for (Annonce annonce : this.annonces) {
            String titre = annonce.getTitre().toLowerCase();
            String description = annonce.getDescription().toLowerCase();
            boolean motTrouve = false;
    
            // Vérifie chaque mot de la recherche dans le titre ou la description
            for (String mot : motsRecherche) {
                if (titre.contains(mot) || description.contains(mot)) {
                    motTrouve = true;
                    break; // Sort de la boucle dès qu'un mot est trouvé
                }
            }
            //On filtre les annonces en fonction des dates en parcourant la liste des dates de l'annonce
            //On affiche l'annonce dès que la date de début ou de fin est comprise dans l'intervalle de recherche
            Date debutCreneauRecherche = Date.from(dateDebut.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            Date finCreneauRecherche = Date.from(dateFin.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
            if (motTrouve && annonce.getLocalisation().toLowerCase().contains(localisation.toLowerCase())) {
                for (int i = 0; i < annonce.getDisponibilites().size(); i++) {
                    List<Date> creneau = annonce.getDisponibilites().get(i);
                    Date dateDebutAnnonce = creneau.get(0);
                    Date dateFinAnnonce = creneau.get(1);
                    //Si dateDebutAnnonce est strictement supérieur à fin recherche ou que dateFinAnnonce est strictement inférieur à debut recherche, on ne fait rien, sinon on ajoute l'annonce
                    if (!(dateDebutAnnonce.after(finCreneauRecherche) || dateFinAnnonce.before(debutCreneauRecherche))) {
                        if ((annonce.getTypeAnnonce().equals("service") && filtreService) || (annonce.getTypeAnnonce().equals("materiel") && filtreMateriel)) {
                            this.annoncesFiltrees.add(annonce);
                            break; 
                        }
                    }
                }
            }
        }
    }
    

    public List<Annonce> getAnnonces(){
        return this.annonces;
    }

    public List<Annonce> getAnnoncesFiltrees(){
        return this.annoncesFiltrees;
    }
}