package com.biblio.biblioapp.models;

import java.util.Date;

public class Emprunt {
    private int idEmprunt;
    private String clientNom;
    private String clientEmail;
    private int idLivre;
    private Date dateEmprunt;
    private Date dateRetour;


    public Emprunt(int idEmprunt, String clientNom,String clientEmail, int idLivre, Date dateEmprunt, Date dateRetour) {
        this.idEmprunt = idEmprunt;
        this.clientNom = clientNom;
        this.clientEmail = clientEmail;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    // Getters et setters
    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
}