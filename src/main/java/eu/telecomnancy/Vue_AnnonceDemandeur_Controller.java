package eu.telecomnancy;

public class Vue_AnnonceDemandeur_Controller implements Observateur {
    private DirectDealing directDealing;

    public Vue_AnnonceDemandeur_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }
    
    @Override
    public void reagir() {
    }
}