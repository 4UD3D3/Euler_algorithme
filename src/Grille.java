//Programme de Andréa Gainche et Andréa Aubert le 01/04/21

public class Grille {
    public int[][] grille;

    public Grille() {
        grille = new int[10][10];
        // rempli le plateau de 0
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                grille[i][j] = 0;
            }
        }
        // rempli de -1 les cases autour du plateau
        for (int a = 0; a < 10; a++) {
            grille[0][a] = -1;
            grille[9][a] = -1;
            grille[a][0] = -1;
            grille[a][9] = -1;
        }
    }


    /**
     * place la pièce piece aux coordonnées i j
     */
    public void placePiece(int i, int j, int piece) {
        grille[i][j] = piece;
    }


    /**
     *  Supprime la pièce de coordonnées i j
     */
    public void supprimePiece(int i, int j) {
        grille[i][j] = 0;
    }


    /**
     * Retourne true si le tableau est rempli
     */
    public boolean isComplete() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grille[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Affiche la grille
     */
    public void print() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                System.out.print(" " + grille[i][j] + " ");
            }
            System.out.println();
        }
    }


    //----------------------------------------------------------------------------------------------------------------//

    /**
     * Place les pieces imposées pour le projet
     */
    public void initialisationDuProf() {
        grille[1][1] = 4;
        grille[2][3] = 5;
        grille[2][6] = 6;
        grille[3][2] = 1;
        grille[3][4] = 4;
        grille[3][7] = 5;
        grille[4][3] = 5;
        grille[4][5] = 1;
        grille[4][8] = 5;
        grille[5][6] = 5;
        grille[5][8] = 5;
        grille[6][1] = 3;
        grille[6][5] = 1;
        grille[7][3] = 5;
        grille[7][6] = 5;
        grille[8][5] = 2;
    }


    /**
     * Renvoie les coordonnées de la prochaine case à traiter
     */
    public Coup prochaineCase(Coup c, Chemin chemin) {
        Coup cp = chemin.getLastLastCoup();

        switch (c.piece) {                                                  //switch qui prend la piece sur laquelle on est
            case 6:
                if (c.i == cp.i && c.j - 1 == cp.j) {                       //regarde comment le dernier est relier a l'avant dernier coup pour chaque piece possible (sauf la fontaine)
                    return new Coup(c.i - 1, c.j, c.piece, c.choix);
                } else {
                    return new Coup(c.i, c.j - 1, c.piece, c.choix);
                }
            case 5:
                if (c.i == cp.i && c.j - 1 == cp.j) {
                    return new Coup(c.i + 1, c.j, c.piece, c.choix);
                } else {
                    return new Coup(c.i, c.j - 1, c.piece, c.choix);
                }
            case 4:
                if (c.i + 1 == cp.i && c.j == cp.j) {
                    return new Coup(c.i, c.j + 1, c.piece, c.choix);
                } else {
                    return new Coup(c.i + 1, c.j, c.piece, c.choix);
                }
            case 3:
                if (c.i - 1 == cp.i && c.j == cp.j) {
                    return new Coup(c.i, c.j + 1, c.piece, c.choix);
                } else {
                    return new Coup(c.i - 1, c.j, c.piece, c.choix);
                }
            case 2:
                if (c.i == cp.i && c.j - 1 == cp.j) {
                    return new Coup(c.i, c.j + 1, c.piece, c.choix);
                } else {
                    return new Coup(c.i, c.j - 1, c.piece, c.choix);
                }
            case 1:
                if (c.i - 1 == cp.i && c.j == cp.j) {
                    return new Coup(c.i + 1, c.j, c.piece, c.choix);
                } else {
                    return new Coup(c.i - 1, c.j, c.piece, c.choix);
                }
            default:
                System.out.println("ERREUR : Grille -> Prochaine case()");
        }
        return new Coup(-1, -1, c.piece, c.choix);
    }


    /**
     * Retourne le coup à jouer à partir de la source
     */
    public Coup prochaineCaseSource(Coup caseActuel) {
        Coup retour = new Coup(caseActuel.i,caseActuel.j,caseActuel.piece,caseActuel.choix,caseActuel.type,caseActuel.depart);
        switch (retour.depart) {               //switch qui prend en argument le depart, qui du coup, change à chaque fois que l'on a fait tout les choix possibles de cette position
            case 1:
                retour.j += 1;
                break;
            case 2:
                retour.i += 1;
                break;
            case 3:
                retour.j -= 1;
                break;
            case 4:
                retour.i -= 1;
                break;
        }
        return retour;
    }


    /**
     * Choisis la prochaine pièce à poser
     */
    public int choixPiece(Coup c, Coup pc, int choix) {

        if (choix == 4) {                                                   // si on fait tout les choix possible on return -1 (qui va enlever ce coup)
            return -1;
        }

        switch (c.piece) {
            case 7:                                                         //pour chaque piece possible on vérifie quel choix de piece on va faire
                if (pc.i == c.i + 1 && pc.j == c.j) {                       //condition pour voir si la piece sur laquelle on est va etre compatible avec la piece que l'on va placer ensuite
                    if (choix == 1) return 1;
                    if (choix == 2) return 3;
                    if (choix == 3){
                        c.depart +=1;                                       //pour la source de debut lorsque l'on a fait tout les choix possibles on passe à la prochaine position
                        return 6;
                    }
                    break;
                }
                if (pc.i == c.i - 1 && pc.j == c.j) {
                    if (choix == 1) return 1;
                    if (choix == 2) return 4;
                    if (choix == 3){
                        c.depart +=1;
                        return 5;
                    }
                    break;
                }
                if (pc.i == c.i && pc.j == c.j - 1) {
                    if (choix == 1) return 2;
                    if (choix == 2) return 3;
                    if (choix == 3){
                        c.depart +=1;
                        return 4;
                    }
                    break;
                }
                if (pc.i == c.i && pc.j == c.j + 1) {
                    if (choix == 1) return 2;
                    if (choix == 2) return 5;
                    if (choix == 3){
                        c.depart +=1;
                        return 6;
                    }
                }
            case 6:
                if (pc.i == c.i && pc.j == c.j - 1) {
                    if (choix == 1) return 2;
                    if (choix == 2) return 3;
                    if (choix == 3) return 4;

                } else {
                    if (choix == 1) return 1;
                    if (choix == 2) return 5;
                    if (choix == 3) return 4;
                }
            case 5:
                if (pc.i == c.i && pc.j == c.j - 1) {
                    if (choix == 1) return 2;
                    if (choix == 2) return 3;
                    if (choix == 3) return 4;
                } else {
                    if (choix == 1) return 1;
                    if (choix == 2) return 3;
                    if (choix == 3) return 6;
                }
            case 4:
                if (pc.i == c.i + 1 && pc.j == c.j) {
                    if (choix == 1) return 1;
                    if (choix == 2) return 3;
                    if (choix == 3) return 6;
                } else {
                    if (choix == 1) return 2;
                    if (choix == 2) return 5;
                    if (choix == 3) return 6;
                }
            case 3:
                if (pc.i == c.i - 1 && pc.j == c.j) {
                    if (choix == 1) return 1;
                    if (choix == 2) return 4;
                    if (choix == 3) return 5;
                } else {
                    if (choix == 1) return 2;
                    if (choix == 2) return 5;
                    if (choix == 3) return 6;
                }
            case 2:
                if (pc.i == c.i && pc.j == c.j - 1) {
                    if (choix == 1) return 2;
                    if (choix == 2) return 3;
                    if (choix == 3) return 4;
                } else {
                    if (choix == 1) return 2;
                    if (choix == 2) return 5;
                    if (choix == 3) return 6;
                }
            case 1:
                if (pc.i == c.i - 1 && pc.j == c.j) {
                    if (choix == 1) return 1;
                    if (choix == 2) return 5;
                    if (choix == 3) return 4;
                } else {
                    if (choix == 1) return 1;
                    if (choix == 2) return 3;
                    if (choix == 3) return 6;
                }

        }
        return -1;
    }


    /**
     * Retourne grille[a][b]
     */
    public int getResultatCase(int a, int b) {                                  //return le resultat de la case des coordonnées données
        return this.grille[a][b];
    }


    /**
     * Vérifie que les deux pièces sont compatibles
     */
    public boolean pieceCompatible(Coup prochaineCase, Coup actuel) {
        boolean reponse = false;

        if (actuel.piece == 1) {
            if (grille[prochaineCase.i][prochaineCase.j] == 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 3 || grille[prochaineCase.i][prochaineCase.j] == 6) && actuel.i == prochaineCase.i - 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 4 || grille[prochaineCase.i][prochaineCase.j] == 5) && actuel.i == prochaineCase.i + 1) reponse = true;
        }
        if (actuel.piece == 2) {
            if (grille[prochaineCase.i][prochaineCase.j] == 2) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 5 || grille[prochaineCase.i][prochaineCase.j] == 6) && actuel.j == prochaineCase.j - 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 3 || grille[prochaineCase.i][prochaineCase.j] == 4) && actuel.j == prochaineCase.j + 1) reponse = true;
        }
        if (actuel.piece == 3) {
            if ((grille[prochaineCase.i][prochaineCase.j] == 1 || grille[prochaineCase.i][prochaineCase.j] == 4 || grille[prochaineCase.i][prochaineCase.j] == 5) && actuel.i == prochaineCase.i + 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 2 || grille[prochaineCase.i][prochaineCase.j] == 6 || grille[prochaineCase.i][prochaineCase.j] == 5) && actuel.j == prochaineCase.j - 1) reponse = true;
        }
        if (actuel.piece == 4) {
            if ((grille[prochaineCase.i][prochaineCase.j] == 1 || grille[prochaineCase.i][prochaineCase.j] == 6 || grille[prochaineCase.i][prochaineCase.j] == 3) && actuel.i == prochaineCase.i - 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 2 || grille[prochaineCase.i][prochaineCase.j] == 6 || grille[prochaineCase.i][prochaineCase.j] == 5) && actuel.j == prochaineCase.j - 1) reponse = true;
        }
        if (actuel.piece == 5) {
            if ((grille[prochaineCase.i][prochaineCase.j] == 1 || grille[prochaineCase.i][prochaineCase.j] == 3 || grille[prochaineCase.i][prochaineCase.j] == 6) && actuel.i == prochaineCase.i - 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 2 || grille[prochaineCase.i][prochaineCase.j] == 3 || grille[prochaineCase.i][prochaineCase.j] == 4) && actuel.j == prochaineCase.j + 1) reponse = true;
        }
        if (actuel.piece == 6) {
            if ((grille[prochaineCase.i][prochaineCase.j] == 1 || grille[prochaineCase.i][prochaineCase.j] == 4 || grille[prochaineCase.i][prochaineCase.j] == 5) && actuel.i == prochaineCase.i + 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 2 || grille[prochaineCase.i][prochaineCase.j] == 3 || grille[prochaineCase.i][prochaineCase.j] == 4) && actuel.j == prochaineCase.j + 1) reponse = true;
        }
        if (actuel.piece == 7) {
            if ((grille[prochaineCase.i][prochaineCase.j] == 1 || grille[prochaineCase.i][prochaineCase.j] == 3 || grille[prochaineCase.i][prochaineCase.j] == 6) && actuel.i == prochaineCase.i - 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 1 || grille[prochaineCase.i][prochaineCase.j] == 4 || grille[prochaineCase.i][prochaineCase.j] == 5) && actuel.i == prochaineCase.i + 1) reponse = true;
            if ((grille[prochaineCase.i][prochaineCase.j] == 2 || grille[prochaineCase.i][prochaineCase.j] == 6 || grille[prochaineCase.i][prochaineCase.j] == 5) && actuel.j == prochaineCase.j - 1) reponse = true;
            if ((prochaineCase.piece == 2 || prochaineCase.piece == 4 || prochaineCase.piece == 3) && actuel.j == prochaineCase.j + 1) reponse = true;
        }
        return reponse;
    }

}