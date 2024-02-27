package eu.telecomnancy;

public class Message {

    private Utilisateur emmeteur;
    private String message;

    public Message(Utilisateur emmeteur, String message){
        this.emmeteur = emmeteur;
        this.message = message;
    }

    public Utilisateur getEmmeteur(){
        return emmeteur;
    }

    public String getMessage(){
        return message;
    }
}
