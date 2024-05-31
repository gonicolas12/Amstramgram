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
            System.out.println("1. S'inscrire\n2. Se connecter\n3. Poster\n4. Commenter\n5. Se déconnecter\n6. Quitter");
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
                    postManager.poster(userManager.getUtilisateurConnecte());
                    break;
                case 4:
                    postManager.commenter(userManager.getUtilisateurConnecte());
                    break;
                case 5:
                    userManager.seDeconnecter();
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
