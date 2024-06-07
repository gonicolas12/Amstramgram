package com.amstramgram.ui;

import com.amstramgram.model.Publication;
import com.amstramgram.model.Utilisateur;
import com.amstramgram.service.AmstramgramService;
import java.util.List;
import java.util.Scanner;

public class PostManager {
    private AmstramgramService service;
    private Scanner scanner;

    public PostManager(AmstramgramService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void poster(Utilisateur utilisateurConnecte) {
        if (utilisateurConnecte == null) {
            System.out.println("Vous devez être connecté pour poster.");
            return;
        }
        System.out.println("Chemin de l'image:");
        String image = scanner.nextLine().trim();
        System.out.println("Description:");
        String description = scanner.nextLine().trim();
        service.publier(utilisateurConnecte, image, description);
        System.out.println("Publication ajoutée avec succès!");
    }

    public void commenter(Utilisateur utilisateurConnecte) {
        if (utilisateurConnecte == null) {
            System.out.println("Vous devez être connecté à un compte pour commenter.");
            return;
        }
        List<Publication> publications = service.getPublications();
        if (publications.isEmpty()) {
            System.out.println("Il n'y a pas de publications à commenter.");
            return;
        }
        for (int i = 0; i < publications.size(); i++) {
            Publication pub = publications.get(i);
            System.out.println(i + ": " + pub.getDescription());
        }
        System.out.println("Choisissez le numéro de la publication à commenter:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Nettoie le buffer
        if (index >= 0 && index < publications.size()) {
            System.out.println("Votre commentaire:");
            String texte = scanner.nextLine().trim();
            service.commenter(utilisateurConnecte, publications.get(index), texte);
            System.out.println("Commentaire ajouté avec succès!");
        } else {
            System.out.println("Numéro de publication invalide.");
        }
    }
}