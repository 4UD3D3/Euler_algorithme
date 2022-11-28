//Programme de Andréa Gainche et Andréa Aubert le 01/04/21

import java.util.ArrayList;

public class Chemin {
    private ArrayList<Coup> coups;

    public Chemin() {
        coups = new ArrayList<>();
    }


    /**
     * Ajoute le coup coup au chemin
     */
    public void addCoup(Coup coup) {
        coups.add(coup);
    }


    /**
     * Supprime le dernier coup du chemin
     */
    public void deleteLastCoup() {
        if (coups.get(coups.size() - 1).type == 0) {
            coups.remove(coups.get(coups.size() - 1));
            coups.remove(coups.get(coups.size() - 1));
        } else {
            coups.remove(coups.get(coups.size() - 1));
        }
    }


    /**
     * retourne le dernier element du chemin
     */
    public Coup getLastCoup() {
        return coups.get(coups.size() - 1);
    }


    /**
     * Retourne le dernier element du chemin qui est n'est pas un tuyau de base
     */
    public Coup getLastCoupSpecial() {
        int i = 2;
        while (coups.get(coups.size() - i).type != 1) {
            i++;
        }
        return (coups.get(coups.size() - i));
    }


    /**
     * Retourne la taille du chemin
     */
    public int taille() {
        return coups.size();

    }


    /**
     * Renvoie l'avant dernier coup
     */
    public Coup getLastLastCoup() {
        return coups.get(coups.size() - 2);
    }


    /**
     * Affiche le chemin entier
     */
    public void print() {
        System.out.println("-----------------------");
        System.out.println("Chemin : ");
        for (Coup coup : coups) {
            coup.print();
        }
        System.out.println("-----------------------");
    }

}
