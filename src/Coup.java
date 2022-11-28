//Programme de Andréa Gainche et Andréa Aubert le 01/04/21

public class Coup {
    public int i;
    public int j;
    public int piece;
    public int choix;
    public int type; // type de piece celle de base dans la grille(0) ou celle qui on met nous meme(1)
    public int depart;

    public Coup(int i, int j, int piece) {
        this.i = i;
        this.j = j;
        this.piece = piece;
        choix = 1;
        type = 1;
        depart = 1;
    }

    public Coup(int i, int j, int piece, int choix) {
        this.i = i;
        this.j = j;
        this.piece = piece;
        this.choix = choix;
        type = 1;
        depart = 1;
    }

    public Coup(int i, int j, int piece, int choix, int type) {
        this.i = i;
        this.j = j;
        this.piece = piece;
        this.choix = choix;
        this.type = type;
        depart = 1;
    }

    public Coup(int i, int j, int piece, int choix, int type, int depa) {
        this.i = i;
        this.j = j;
        this.piece = piece;
        this.choix = choix;
        this.type = type;
        this.depart = depa;
    }


    /**
     * Affiche le coup
     */
    public void print() {
        System.out.println("[" + i + "," + j + "] : " + piece + " (choix=" + choix + ")" + "type = " + type + " depart = " + depart);
    }

}
