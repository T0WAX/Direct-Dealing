package eu.telecomnancy;

public class Vue_AnnonceAnnonceur_Controller implements Observateur {
    private DirectDealing directDealing;

    public Vue_AnnonceAnnonceur_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }
    
    @Override
    public void reagir() {
    }
}