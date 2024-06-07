package com.amstramgram.ui;

import com.amstramgram.service.AmstramgramService;
import java.util.Scanner;

public class ConsoleUI {
    private AmstramgramService service = new AmstramgramService();
    private Scanner scanner = new Scanner(System.in);
    private UserManager userManager;
    private PostManager postManager;

    public ConsoleUI() {
        userManager = new UserManager(service, scanner);
        postManager = new PostManager(service, scanner);
    }

    public void start() {
        while (true) {
            System.out.println("1. S'inscrire\n2. Se connecter\n3. Publications\n4. Se déconnecter\n5. Quitter");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoie le buffer

            switch (choix) {
                case 1:
                    userManager.inscrireUtilisateur();
                    break;
                case 2:
                    userManager.connecterUtilisateur();
                    break;
                case 3:
                    gestionPublications();
                    break;
                case 4:
                    userManager.seDeconnecter();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    private void gestionPublications() {
        System.out.println("1. Poster\n2. Voir Publications\n3. Quitter");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Nettoie le buffer

        switch (choix) {
            case 1:
                postManager.poster(userManager.getUtilisateurConnecte());
                break;
            case 2:
                postManager.afficherPublications(userManager.getUtilisateurConnecte());
                break;
            case 3:
                return; // Retour au menu principal
        }
    }

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }
}