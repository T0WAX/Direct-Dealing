package eu.telecomnancy;

public class Vue_MessageAnnonceur_Controller implements Observateur{
    private DirectDealing directDealing;

    public Vue_MessageAnnonceur_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
    }
}

