package eu.telecomnancy;

public class Vue_Annonce_Controller implements Observateur {
    private DirectDealing directDealing;

    public Vue_Annonce_Controller(DirectDealing directDealing) {
        this.directDealing = directDealing;
        this.directDealing.ajouterObservateur(this);
    }
    
    @Override
    public void reagir() {
    }
}