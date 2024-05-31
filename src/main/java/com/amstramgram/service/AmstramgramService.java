package com.amstramgram.service;

import com.amstramgram.model.Utilisateur;
import com.amstramgram.model.Publication;
import com.amstramgram.model.Commentaire;
import java.util.ArrayList;
import java.util.List;


public class AmstramgramService {
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private List<Publication> publications = new ArrayList<>();

    public void inscrireUtilisateur(String username, String password) {
        utilisateurs.add(new Utilisateur(username, password));
    }

    public Utilisateur connecterUtilisateur(String username, String password) {
        for (Utilisateur u : utilisateurs) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null; // Retourne null si aucun utilisateur correspondant n'est trouvé.
    }

    public void publier(Utilisateur auteur, String image, String description) {
        publications.add(new Publication(auteur, image, description));
    }

    public void commenter(Utilisateur auteur, Publication publication, String texte) {
        publication.addCommentaire(new Commentaire(auteur, publication, texte));
    }
}
