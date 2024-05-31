package com.amstramgram.model;

import java.util.ArrayList;
import java.util.List;

public class Publication {
    private Utilisateur auteur;
    private String image; // Chemin de l'image
    private String description;
    private List<Commentaire> commentaires;

    public Publication(Utilisateur auteur, String image, String description) {
        this.auteur = auteur;
        this.image = image;
        this.description = description;
        this.commentaires = new ArrayList<>();
    }

    // Getters
    public Utilisateur getAuteur() {
        return auteur;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    // Setters
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Methodes
    public void addCommentaire(Commentaire commentaire) {
        commentaires.add(commentaire);
    }
}
