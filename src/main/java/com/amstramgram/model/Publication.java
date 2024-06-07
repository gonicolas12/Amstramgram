package com.amstramgram.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Publication {
    private Utilisateur auteur;
    private String image; // Chemin de l'image
    private String description;
    private List<Commentaire> commentaires;
    private Set<Utilisateur> likes; // Pour stocker les utilisateurs qui ont aimé la publication

    public Publication(Utilisateur auteur, String image, String description) {
        this.auteur = auteur;
        this.image = image;
        this.description = description;
        this.commentaires = new ArrayList<>();
        this.likes = new HashSet<>(); // Initialise avec un HashSet pour éviter les doublons
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

    public int getLikesCount() {
        return likes.size(); // Retourne le nombre de likes
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

    // Méthodes
    public void addCommentaire(Commentaire commentaire) {
        commentaires.add(commentaire);
    }

    public boolean ajouterLike(Utilisateur utilisateur) {
        return likes.add(utilisateur);
    }

    public void retirerLike(Utilisateur utilisateur) {
        if (!likes.remove(utilisateur)) {
            System.out.println("Vous n'aviez pas aimé cette publication.");
        } else {
            System.out.println("Like retiré avec succès.");
        }
    }
}