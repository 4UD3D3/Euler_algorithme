//Programme de Andréa Gainche et Andréa Aubert le 01/04/21

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFenetre implements ActionListener {

    private Fenetre fen;
    public int clicMenu;
    public boolean montreDetails;

    public int[][] grilleTemporaire = new int[8][8];
    private int clicBouton;
    public boolean clicValider;

    ControlFenetre(Fenetre fen) {
        montreDetails = false;
        this.fen = fen;
        clicMenu = 0;
        clicBouton = -1;
        clicValider = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grilleTemporaire[i][j] = 0;
            }
        }
        fen.setControlFenetre(this);
    }


    /**
     * Détecte quel élément a été cliqué et agit en conséquence
     */
    public void actionPerformed(ActionEvent e) {

        // SOURCE : MENU
        if (e.getSource() == fen.boutonMenu1) {
            fen.afficheGrilleResolution();
            montreDetails = fen.checkboxMenu.isSelected();
            clicMenu = 1;
        }
        if (e.getSource() == fen.boutonMenu2) {
            fen.affichePersonnalisationGrille();
            clicMenu = 2;
            montreDetails = fen.checkboxMenu.isSelected();
        }

        // SOURCE : GRILLE RESOLUTION
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource() == fen.tabButton[i][j]) {
                    fen.majCoordonnees(i, j);
                }
            }
        }


        // SOURCE : GRILLE CHOIX

        // Reset de la grille
        if(e.getSource()==fen.reset){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    grilleTemporaire[i][j] = 0;
                    fen.resetGrilleChoix();
                }
            }
        }

        // Valider : Pour être valide la grille doit contenir 1 source
        if(e.getSource()==fen.valider){
            boolean is7 = false;
            for (int a = 0; a < 8; a++) {
                for (int b = 0; b < 8; b++) {
                    if (grilleTemporaire[a][b] == 7) {
                        is7 = true;
                    }
                }
            }
            if(!is7){
                String message = "Impossible de valider ! Votre grille ne possède pas de source !";
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, message, "Scores", JOptionPane.INFORMATION_MESSAGE);
            } else {
                clicValider = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(grilleTemporaire[i][j]+" ");
                    }
                    System.out.println(" ");
                }
            }
        }

        // Grille de boutons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource() == fen.tabButtonChoix[i][j]) {
                    placerImage(i,j);
                }
            }
        }

        // Boutons images à gauche
        if (e.getSource() == fen.bouton0) {
            fen.changerLesBords(0);
            clicBouton = 0;
        }
        if (e.getSource() == fen.bouton1) {
            fen.changerLesBords(1);
            clicBouton = 1;
        }
        if (e.getSource() == fen.bouton2) {
            fen.changerLesBords(2);
            clicBouton = 2;
        }
        if (e.getSource() == fen.bouton3) {
            fen.changerLesBords(3);
            clicBouton = 3;
        }
        if (e.getSource() == fen.bouton4) {
            fen.changerLesBords(4);
            clicBouton = 4;
        }
        if (e.getSource() == fen.bouton5) {
            fen.changerLesBords(5);
            clicBouton = 5;
        }
        if (e.getSource() == fen.bouton6) {
            fen.changerLesBords(6);
            clicBouton = 6;
        }
        if (e.getSource() == fen.bouton7) {
            fen.changerLesBords(7);
            clicBouton = 7;
        }

    }


    /**
     * Gère le placement des images dans la grille
     */
    public void placerImage(int i, int j){

        boolean is7 = false;
        if (clicBouton == 7) {                           // Erreur 2 fontaines
            for (int a = 0; a < 8; a++) {
                for (int b = 0; b < 8; b++) {
                    if (grilleTemporaire[a][b] == 7) {
                        is7 = true;
                    }
                }
            }
            if (is7) {
                String message = "Impossible d'avoir deux sources ! Supprimez-la avant d'en remettre une.";
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, message, "Scores", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (clicBouton == 0) {
            grilleTemporaire[i][j] = clicBouton;
            fen.majImageDansGrilleChoix(clicBouton, i, j);
        } else if (clicBouton != -1 && !is7) {                  // Erreur 2 pieces cote a cote
            boolean isQqnAcote = false;

            if (i - 1 >= 0) {
                if (grilleTemporaire[i - 1][j] != 0) {
                    System.out.println("ici1 " + grilleTemporaire[i - 1][j]);
                    isQqnAcote = true;
                }
            }
            if (i + 1 <= 7) {
                if (grilleTemporaire[i + 1][j] != 0) {
                    System.out.println("ici2 " + grilleTemporaire[i + 1][j]);
                    isQqnAcote = true;
                }
            }
            if (j - 1 >= 0) {
                if (grilleTemporaire[i][j - 1] != 0) {
                    System.out.println("ici3 " + grilleTemporaire[i][j - 1]);
                    isQqnAcote = true;
                }
            }
            if (j + 1 <= 7) {
                if (grilleTemporaire[i][j + 1] != 0) {
                    System.out.println("ici4 " + grilleTemporaire[i][j + 1]);
                    isQqnAcote = true;
                }

            }
            if (isQqnAcote) {
                String message = "Impossible d'avoir deux pièces côte à côte !";
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, message, "Scores", JOptionPane.INFORMATION_MESSAGE);
            } else {
                grilleTemporaire[i][j] = clicBouton;
                fen.majImageDansGrilleChoix(clicBouton, i, j);
            }
        }
        fen.majCoordonnees(i, j);
    }

}
