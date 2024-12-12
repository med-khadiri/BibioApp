package com.biblio.biblioapp.models;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private boolean dispo;
    private String categorie;

    // Constructeurs
    public Livre() {}

    public Livre(int id, String titre, String auteur, boolean dispo, String categorie) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.dispo = dispo;
        this.categorie = categorie;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
