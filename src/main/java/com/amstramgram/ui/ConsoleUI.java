package com.amstramgram.ui;

import com.amstramgram.model.Utilisateur;
import com.amstramgram.service.AmstramgramService;
import java.util.Scanner;

public class ConsoleUI {
    private AmstramgramService service = new AmstramgramService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("1. S'inscrire\n2. Se connecter\n3. Poster\n4. Commenter\n5. Quitter");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoie le buffer

            switch (choix) {
                case 1:
                    System.out.println("Nom d'utilisateur:");
                    String username = scanner.nextLine().trim(); // Utilise trim() pour enlever les espaces blancs
                    if (username.isEmpty()) {
                        System.out.println("Veuillez remplir le champ du nom d'utilisateur.");
                        break; // Sort du case si le champ est vide
                    }
                    System.out.println("Mot de passe:");
                    String password = scanner.nextLine().trim();
                    if (password.isEmpty()) {
                        System.out.println("Veuillez remplir le champ du mot de passe.");
                        break; // Sort du case si le champ est vide
                    }
                    service.inscrireUtilisateur(username, password);
                    break;
                case 2:
                    System.out.println("Nom d'utilisateur:");
                    String usernameLogin = scanner.nextLine().trim();
                    System.out.println("Mot de passe:");
                    String passwordLogin = scanner.nextLine().trim();
                    Utilisateur utilisateur = service.connecterUtilisateur(usernameLogin, passwordLogin);
                    if (utilisateur == null) {
                        System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
                    } else {
                        System.out.println("Connexion réussie!");
                    }
                    break;
                case 3:
                    // Poster une publication
                    break;
                case 4:
                    // Ajouter un commentaire
                    break;
                case 5:
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
