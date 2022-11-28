//Programme de Andréa Gainche et Andréa Aubert le 01/04/21

import java.util.Calendar;

public class Application {

    private static final Grille g = new Grille();
    private static final Grille maxG = new Grille();
    private static Chemin chemin;
    private static Fenetre fen;
    private static ControlFenetre control;

    public static void main(String[] args) throws InterruptedException {
        chemin = new Chemin();
        fen = new Fenetre(g);
        control = new ControlFenetre(fen);

        // on attend que l'utilisateur choisisse un mode avant toute chose.
        while (control.clicMenu == 0) {
            try { Thread.sleep(50); }
            catch (InterruptedException e) { System.out.println("erreur try catch"); }
        }

        // Resolution problème du prof
        if (control.clicMenu == 1) {
            g.placePiece(5, 4, 7);
            g.initialisationDuProf();
            fen.majGB(g.grille);
            fen.majImage();
            Resoudre();
        }

        // résolution problème personnalisé
        if (control.clicMenu == 2) {
            // On attends que l'utilisateur ai fini de placer ces pièces
            while (!control.clicValider) {
                try { Thread.sleep(500); }
                catch (InterruptedException e) { System.out.println("erreur try catch"); }
            }
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    g.placePiece(x+1,y+1,control.grilleTemporaire[x][y]);
                }
            }
            g.print();
            fen.majGB(g.grille);
            fen.afficheGrilleResolution();
            fen.majImage();
            Resoudre();
        }
    }


    /**
     * Résoud le problème
     */
    private static void Resoudre() {

        initialisationCoupDepart(chemin,g);


        Coup prochainCoup;
        Coup caseActuelle = chemin.getLastCoup();
        int choixpiece;
        int choix;
        int maxChemin = 0;
        Calendar calDebut = Calendar.getInstance();
        Calendar calFin;

        while (caseActuelle.depart != 5 && g.isComplete()) {                            //tant que le programme n'a pas testé toutes les possibilitées autour de la case de départ ou si la grille est complete
            caseActuelle = chemin.getLastCoup();                                        //La case actuel est le dernier coup enrengistrer dans le chemin
            if (caseActuelle.piece != 7) {                                              //Si la case actuel n'est pas la case de départ on prend la prochaine case avec la fonction prochaineCase
                prochainCoup = g.prochaineCase(caseActuelle, chemin);
            } else {
                if (caseActuelle.choix == 4) {                                          //Si on est sur la case de départ et que le l'on a fait tout les choix possibles
                    caseActuelle.choix = 1;                                             //On remet le choix de la case de départ a 1
                }
                prochainCoup = g.prochaineCaseSource(caseActuelle);                     //On determine le prochain coup à jouer avec la fonction prochaineCaseSource
            }

            if (g.grille[prochainCoup.i][prochainCoup.j] == 0) {                        //le prochain coup est une case vide
                choix = prochainCoup.choix;                                             //On prend le choix du porchain coup à jouer
                choixpiece = g.choixPiece(caseActuelle, prochainCoup, choix);           //On choisis la piece a jouer en fonction du coup actuel et et du prochain coup

                if (choixpiece == -1) {                                                 //Si le choix de la piece est -1 c est que l'on doit revenir en arriere
                    deleteSpecial(chemin, caseActuelle);                                //Supprime le coup actuelle
                } else {
                    prochainCoup.piece = choixpiece;
                    chemin.addCoup(prochainCoup);                                       //Ajoute le coup au chemin
                    g.placePiece(prochainCoup.i, prochainCoup.j, prochainCoup.piece);   //Place la piece dans la grille
                    prochainCoup.choix = 1;                                             //augmmente le choix du prochain coup de 1
                }

            } else if (g.grille[prochainCoup.i][prochainCoup.j] == -1) {                //Demi-tour : La pièce va vers le mur
                deleteSpecial(chemin, caseActuelle);                                    //Supprime le coup actuelle
            } else {
                if (g.pieceCompatible(prochainCoup, caseActuelle)) {                    //Vérifier que la pièce est compatible.
                    chemin.addCoup(new Coup(prochainCoup.i, prochainCoup.j, g.getResultatCase(prochainCoup.i, prochainCoup.j), 1, 0));      //ajoute le coup au chemin en precisans que c est une piece a pas changé
                } else {                                                                //Si non, demi-tour
                    deleteSpecial(chemin, caseActuelle);                                //Supprime le coup actuelle
                }
            }

            if (maxChemin < chemin.taille()) {                                          //Compare la taille du chemin et la variable maxChemin qui contient la taille max du chemin deja fait
                maxChemin = chemin.taille();                                            //Si le chemin est plus long on la valeur max est la taille du chemin
                for (int i = 0; i < 10; i++) {                                          //parcourt la grille de base g et la copie dans maxG
                    for (int j = 0; j < 10; j++) {
                        maxG.placePiece(i, j, g.getResultatCase(i, j));
                    }
                }
            }

            if(control.montreDetails){
                fen.majImage();
            }

        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.placePiece(i, j, maxG.getResultatCase(i, j));                         //copie maxG dans g pour affiché le chemin le plus long
            }
        }

        fen.majImage();

        calFin = Calendar.getInstance();
        int tempsCalcul;
        tempsCalcul = (calFin.get(Calendar.HOUR_OF_DAY)*360*1000+calFin.get(Calendar.MINUTE)*60*1000+calFin.get(Calendar.SECOND)*1000+calFin.get(Calendar.MILLISECOND))-(calDebut.get(Calendar.HOUR_OF_DAY)*360*1000+calDebut.get(Calendar.MINUTE)*60*1000+calDebut.get(Calendar.SECOND)*1000+calDebut.get(Calendar.MILLISECOND));
        fen.temps.setText("Résolu en : "+tempsCalcul+" ms        ");
    }


    /**
     * Enleve un coup
     */
    private static void deleteSpecial(Chemin chemin, Coup caseActuel) {
        Coup pc;
        if (caseActuel.type == 0) {                                                         //si c est un coup fait de base par l'utilisateur
            g.supprimePiece(chemin.getLastCoupSpecial().i, chemin.getLastCoupSpecial().j);  //on supprime de la grille le coup qui est fait par l'ordinateur
        } else {
            g.supprimePiece(chemin.getLastCoup().i, chemin.getLastCoup().j);                //on supprime de la grille le dernier coup du chemin
        }
        if (caseActuel.piece == 7){
            caseActuel.depart +=1;

        }
        else{
            chemin.deleteLastCoup();                                                            //enleve le  dernier coup dans le chemin
            pc = chemin.getLastCoup();                                                          //revient au coup precedent
            pc.choix += 1;                                                                      //passe au choix suivant
        }
    }


    /**
     * Initialise le coup de départ
     */
    public static void initialisationCoupDepart(Chemin chemin, Grille g){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (g.getResultatCase(i,j)==7){
                    chemin.addCoup(new Coup(i, j, 7));
                }
            }
        }
    }


    /**
     * Affiche la fenetre (debug)
     */
    public void Affichefenetre() {
        //Affichage fenêtre finale
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Fenetre f = new Fenetre(g);
            }
        });
    }

}
