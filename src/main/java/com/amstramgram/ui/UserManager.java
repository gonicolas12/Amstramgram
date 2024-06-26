package com.amstramgram.ui;

import com.amstramgram.model.Utilisateur;
import com.amstramgram.service.AmstramgramService;
import java.util.Scanner;

public class UserManager {
    private AmstramgramService service;
    private Scanner scanner;
    private Utilisateur utilisateurConnecte;

    public UserManager(AmstramgramService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public void inscrireUtilisateur() {
        System.out.println("Nom d'utilisateur:");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Le nom d'utilisateur ne peut pas être vide.");
            return;
        }

        System.out.println("Mot de passe:");
        String password = scanner.nextLine().trim();
        if (password.isEmpty()) {
            System.out.println("Le mot de passe ne peut pas être vide.");
            return;
        }

        service.inscrireUtilisateur(username, password);
    }

    public void connecterUtilisateur() {
        if (utilisateurConnecte != null) {
            System.out.println("Vous êtes déjà connecté à un compte.");
            return;
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
    }

    public void seDeconnecter() {
        if (utilisateurConnecte == null) {
            System.out.println("Aucun utilisateur n'est connecté.");
        } else {
            utilisateurConnecte = null;
            System.out.println("Vous avez été déconnecté.");
        }
    }
}
