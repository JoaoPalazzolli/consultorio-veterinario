package models;

public class Consultorio {
    
    private String porta;
    private Boolean livre;

    public Consultorio() {
    }

    public Consultorio(String porta) {
        this.porta = porta;
        this.livre = true;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public Boolean getLivre() {
        return livre;
    }

    public void setLivre(Boolean livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "\nPorta: " + porta;
    }
}
