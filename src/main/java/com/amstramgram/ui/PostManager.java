package com.amstramgram.ui;

import com.amstramgram.model.Publication;
import com.amstramgram.model.Utilisateur;
import com.amstramgram.model.Commentaire;
import com.amstramgram.service.AmstramgramService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PostManager {
    private AmstramgramService service;
    private Scanner scanner;

    public PostManager(AmstramgramService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void gestionPublications(Utilisateur utilisateurConnecte) {
        while (true) {
            System.out.println("1. Poster\n2. Voir Publications\n3. Quitter");
            System.out.print("Entrez votre choix : ");
            int choix;
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erreur : veuillez entrer un nombre valide.");
                scanner.nextLine();  // Nettoie le buffer
                continue;
            }
            scanner.nextLine();  // Nettoie le buffer après la lecture d'un int

            switch (choix) {
                case 1:
                    poster(utilisateurConnecte);
                    break;
                case 2:
                    afficherPublications(utilisateurConnecte);
                    break;
                case 3:
                    return;  // Retour au menu principal
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        }
    }

    public void afficherPublications(Utilisateur utilisateurConnecte) {
        List<Publication> publications = service.getPublications();
        if (publications.isEmpty()) {
            System.out.println("Il n'y a pas de publications.");
            return;
        }
        for (int i = 0; i < publications.size(); i++) {
            Publication pub = publications.get(i);
            System.out.println(i + ": " + pub.getDescription() + " - Likes: " + pub.getLikesCount());
            // Afficher les commentaires
            if (!pub.getCommentaires().isEmpty()) {
                System.out.println("    Commentaires:");
                for (Commentaire commentaire : pub.getCommentaires()) {
                    System.out.println("        " + commentaire.getAuteur().getUsername() + ": " + commentaire.getTexte());
                }
            }
        }
        int choix = 0;
        while (true) {
            System.out.println("Choisissez une publication (numéro) pour interagir, -1 pour quitter ou retour au menu principal:");
            try {
                choix = scanner.nextInt();
                if (choix == -1) {
                    return;  // Retour au menu précédent
                }
                if (choix >= 0 && choix < publications.size()) {
                    interactionPublication(utilisateurConnecte, publications.get(choix));
                    break; // Sortir de la boucle après l'interaction
                } else {
                    System.out.println("Sélection invalide, veuillez réessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : veuillez entrer un numéro valide.");
                scanner.nextLine();  // Nettoie le buffer de toute entrée incorrecte
            }
        }
    }

    private void interactionPublication(Utilisateur utilisateurConnecte, Publication publication) {
        System.out.println("1. Commenter\n2. Aimer\n3. Retirer le like\n4. Quitter");
        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                commenter(utilisateurConnecte, publication);
                break;
            case 2:
                aimerPublication(utilisateurConnecte, publication);
                break;
            case 3:
                retirerLikePublication(utilisateurConnecte, publication);
                break;
            case 4:
                return; // Retour au menu des publications
        }
    }

    public void aimerPublication(Utilisateur utilisateurConnecte, Publication publication) {
        if (utilisateurConnecte == null) {
            System.out.println("Vous devez être connecté pour aimer une publication.");
            return;
        }
        // Appelle la méthode ajouterLike et vérifie si le like a été ajouté avec succès
        boolean isLiked = publication.ajouterLike(utilisateurConnecte);
        if (isLiked) {
            System.out.println("Publication aimée avec succès!");
        } else {
            System.out.println("Vous avez déjà aimé cette publication.");
        }
    }


    public void retirerLikePublication(Utilisateur utilisateurConnecte, Publication publication) {
        if (utilisateurConnecte == null) {
            System.out.println("Vous devez être connecté pour retirer un like.");
            return;
        }
        publication.retirerLike(utilisateurConnecte);
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

    public void commenter(Utilisateur utilisateurConnecte, Publication publication) {
        if (utilisateurConnecte == null) {
            System.out.println("Vous devez être connecté pour commenter.");
            return;
        }
        List<Commentaire> commentaires = publication.getCommentaires();
        System.out.println("Votre commentaire:");
        String texte = scanner.nextLine().trim();
        service.commenter(utilisateurConnecte, publication, texte);
        System.out.println("Commentaire ajouté avec succès!");
    }

}