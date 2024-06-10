package com.amstramgram.ui;

import com.amstramgram.service.AmstramgramService;
import java.util.InputMismatchException;
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
            System.out.print("Entrez votre choix : ");
            int choix = 0;
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erreur : veuillez entrer un nombre valide.");
                scanner.nextLine();  // Nettoie le buffer
                continue;
            }
            scanner.nextLine();  // Nettoie le buffer après la lecture d'un int

            switch (choix) {
                case 1: userManager.inscrireUtilisateur(); break;
                case 2: userManager.connecterUtilisateur(); break;
                case 3: postManager.gestionPublications(userManager.getUtilisateurConnecte()); break;
                case 4: userManager.seDeconnecter(); break;
                case 5: System.out.println("Au revoir !"); System.exit(0); break;
                default: System.out.println("Choix invalide, veuillez réessayer."); break;
            }
        }
    }

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }
}
