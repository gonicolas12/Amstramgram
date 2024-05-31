package com.amstramgram.ui;

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
                    String username = scanner.nextLine();
                    System.out.println("Mot de passe:");
                    String password = scanner.nextLine();
                    service.inscrireUtilisateur(username, password);
                    break;
                case 2:
                    // Similaire pour connexion
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
