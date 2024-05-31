package com.amstramgram.ui;

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
                    // Implémentation du poster (seulement si connecté)
                    break;
                case 4:
                    // Implémentation du commenter (seulement si connecté)
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
