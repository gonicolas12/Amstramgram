package com.amstramgram.model;

public class Commentaire {
    private Utilisateur auteur;
    private Publication publication;
    private String texte;

    public Commentaire(Utilisateur auteur, Publication publication, String texte) {
        this.auteur = auteur;
        this.publication = publication;
        this.texte = texte;
    }

    // Getters
    public Utilisateur getAuteur() {
        return auteur;
    }

    public Publication getPublication() {
        return publication;
    }

    public String getTexte() {
        return texte;
    }

    // Setters
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
