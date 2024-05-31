package com.amstramgram.ui;

import java.util.List;
import com.amstramgram.model.Publication;
import com.amstramgram.model.Utilisateur;
import com.amstramgram.service.AmstramgramService;
import java.util.Scanner;

public class ConsoleUI {
    private AmstramgramService service = new AmstramgramService();
    private Scanner scanner = new Scanner(System.in);
    private Utilisateur utilisateurConnecte = null;  // Ajout de la variable pour gérer l'utilisateur connecté

    public void start() {
        while (true) {
            System.out.println("1. S'inscrire\n2. Se connecter\n3. Poster\n4. Commenter\n5. Se déconnecter\n6. Quitter");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoie le buffer

            switch (choix) {
                case 1:
                    System.out.println("Nom d'utilisateur:");
                    String username = scanner.nextLine().trim();
                    System.out.println("Mot de passe:");
                    String password = scanner.nextLine().trim();
                    service.inscrireUtilisateur(username, password);
                    break;
                case 2:
                    if (utilisateurConnecte != null) {
                        System.out.println("Un utilisateur est déjà connecté. Veuillez vous déconnecter d'abord.");
                        break;
                    }
                    System.out.println("Nom d'utilisateur:");
                    String usernameLogin = scanner.nextLine().trim();
                    System.out.println("Mot de passe:");
                    String passwordLogin = scanner.nextLine().trim();
                    utilisateurConnecte = service.connecterUtilisateur(usernameLogin, passwordLogin);
                    if (utilisateurConnecte == null) {
                        System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
                    } else {
                        System.out.println("Connexion réussie!");
                    }
                    break;
                case 3:
                    if (utilisateurConnecte == null) {
                        System.out.println("Veuillez vous connecter pour poster.");
                    } else {
                        System.out.println("Chemin de l'image:");
                        String image = scanner.nextLine().trim();
                        System.out.println("Description:");
                        String description = scanner.nextLine().trim();
                        service.publier(utilisateurConnecte, image, description);
                        System.out.println("Publication ajoutée avec succès!");
                    }
                    break;
                case 4:
                    if (utilisateurConnecte == null) {
                        System.out.println("Veuillez vous connecter pour commenter.");
                    } else {
                        List<Publication> publications = service.getPublications();  // Utilisez le getter ici
                        if (publications.isEmpty()) {
                            System.out.println("Il n'y a pas de publications à commenter.");
                        } else {
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
                    break;
                case 5:
                    if (utilisateurConnecte == null) {
                        System.out.println("Aucun utilisateur n'est connecté.");
                    } else {
                        utilisateurConnecte = null;
                        System.out.println("Vous avez été déconnecté.");
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }
}
