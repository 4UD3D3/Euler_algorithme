//Programme de Andréa Gainche et Andréa Aubert le 01/04/21

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class Fenetre extends JFrame{

    // VARIABLES
    private Grille g;
    private int[][] gb;
    // IMAGES
    private ImageIcon i0 = new ImageIcon(new ImageIcon("Image/0.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i1 = new ImageIcon(new ImageIcon("Image/Pose/1.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i2 = new ImageIcon(new ImageIcon("Image/Pose/2.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i3 = new ImageIcon(new ImageIcon("Image/Pose/3.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i4 = new ImageIcon(new ImageIcon("Image/Pose/4.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i5 = new ImageIcon(new ImageIcon("Image/Pose/5.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i6 = new ImageIcon(new ImageIcon("Image/Pose/6.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i7 = new ImageIcon(new ImageIcon("Image/7.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i9 = new ImageIcon(new ImageIcon("Image/9.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i1b = new ImageIcon(new ImageIcon("Image/Base/1.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i2b = new ImageIcon(new ImageIcon("Image/Base/2.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i3b = new ImageIcon(new ImageIcon("Image/Base/3.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i4b = new ImageIcon(new ImageIcon("Image/Base/4.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i5b = new ImageIcon(new ImageIcon("Image/Base/5.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    private ImageIcon i6b = new ImageIcon(new ImageIcon("Image/Base/6.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));

    // MENU
    private JLabel labelMenu = new JLabel();
    public JButton boutonMenu1 = new JButton();
    public JButton boutonMenu2 = new JButton();
    private JLabel textCheck = new JLabel();
    public JCheckBox checkboxMenu = new JCheckBox();
    // RESOLUTION GRILLE
    public JLabel temps = new JLabel();
    public JButton valider = new JButton();
    public JButton reset = new JButton();
    private JLabel titre = new JLabel();
    private JLabel coordonnees = new JLabel();
    public JButton[][] tabButton = new JButton[8][8];
    // PERSONNALISATION GRILLE
    public JButton[][] tabButtonChoix = new JButton[8][8];
    private JLabel textChoix = new JLabel();
    public JButton bouton0 = new JButton();
    public JButton bouton1 = new JButton();
    public JButton bouton2 = new JButton();
    public JButton bouton3 = new JButton();
    public JButton bouton4 = new JButton();
    public JButton bouton5 = new JButton();
    public JButton bouton6 = new JButton();
    public JButton bouton7 = new JButton();


    public Fenetre(Grille g){
        gb = new int[10][10];
        this.g = g;
        afficheMenuChoix();
        this.setTitle("Projet INF1404 2021  -  Andréa AUBERT & Andréa GAINCHE");
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                tabButton[i][j] = new JButton();
                tabButtonChoix[i][j] = new JButton();
            }
        }
    }


    /**
     * Affiche le menu de départ
     */
    public void majGB(int[][] gb){
        for(int i=1; i<9; i++){
            for(int j=1; j<9; j++) {
                this.gb[i][j] = gb[i][j];
            }
        }
    }


    /**
     * Affiche le menu de départ
     */
    private void afficheMenuChoix(){
        // panoBoutons
        boutonMenu1.setText("Grille imposée");
        boutonMenu1.setPreferredSize(new Dimension(150,40));
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(10,10));
        boutonMenu2.setText("Grille personnalisée");
        boutonMenu2.setPreferredSize(new Dimension(150,40));

        JPanel panoBoutons = new JPanel();
        panoBoutons.setPreferredSize(new Dimension(400,80));
        panoBoutons.add(new JPanel());
        panoBoutons.add(boutonMenu1);
        panoBoutons.add(panel1);
        panoBoutons.add(boutonMenu2);
        panoBoutons.add(new JPanel());

        // panocheckbox
        textCheck.setText("Montrer toutes les opérations");

        JPanel panocheckbox = new JPanel();
        panocheckbox.add(textCheck);
        panocheckbox.add(checkboxMenu);

        // grandPano
        labelMenu.setText(" Choisissez le mode de résolution :");

        JPanel grandPano = new JPanel(new GridLayout(3,1));
        grandPano.setPreferredSize(new Dimension(400,200));
        grandPano.add(labelMenu);
        grandPano.add(panoBoutons);
        grandPano.add(panocheckbox);

        // grosPano
        JPanel grosPano = new JPanel();
        grosPano.add(grandPano);

        // afficher
        setContentPane(grosPano);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Affiche la fenêtre de résolution
     */
    public void afficheGrilleResolution(){

        // bebePano1
        temps.setText("Calcul en cours ...        ");
        titre.setText("Résolution de la grille  ");
        titre.setForeground(Color.RED);
        coordonnees.setText("        (Clic sur une case pour + d'infos)");

        JPanel bebePano1 = new JPanel();
        bebePano1.add(temps);
        bebePano1.add(titre);
        bebePano1.add(coordonnees);

        // bebePano2
        JPanel bebePano2 = new JPanel(new GridLayout(8,8));
        bebePano2.setPreferredSize(new Dimension(800,800));
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                bebePano2.add(tabButton[i][j]);
            }
        }

        // pano
        JPanel pano = new JPanel();
        pano.setLayout(new BoxLayout(pano, BoxLayout.Y_AXIS));
        pano.add(bebePano1);
        pano.add(bebePano2);

        // papaPano
        JPanel PapaPano = new JPanel();
        PapaPano.add(pano);

        // Affichage
        setContentPane(PapaPano);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Affiche la fenêtre de personnalisation de grille
     */
    public void affichePersonnalisationGrille(){

        // panoBouton
        bouton0.setIcon(i0);
        bouton1.setIcon(i1);
        bouton2.setIcon(i2);
        bouton3.setIcon(i3);
        bouton4.setIcon(i4);
        bouton5.setIcon(i5);
        bouton6.setIcon(i6);
        bouton7.setIcon(i7);
        Border borderWHITE = BorderFactory.createLineBorder(java.awt.Color.WHITE);
        bouton0.setBorder(borderWHITE);
        bouton1.setBorder(borderWHITE);
        bouton2.setBorder(borderWHITE);
        bouton3.setBorder(borderWHITE);
        bouton4.setBorder(borderWHITE);
        bouton5.setBorder(borderWHITE);
        bouton6.setBorder(borderWHITE);
        bouton7.setBorder(borderWHITE);

        JPanel panoBouton = new JPanel(new GridLayout(8,1));
        panoBouton.setPreferredSize(new Dimension(100,800));
        panoBouton.add(bouton0);
        panoBouton.add(bouton1);
        panoBouton.add(bouton2);
        panoBouton.add(bouton3);
        panoBouton.add(bouton4);
        panoBouton.add(bouton5);
        panoBouton.add(bouton6);
        panoBouton.add(bouton7);

        // panoGrille
        JPanel espace = new JPanel();
        espace.setPreferredSize( new Dimension(50,700));

        JPanel panoGrille = new JPanel(new GridLayout(8,8));
        panoGrille.setPreferredSize(new Dimension(800,800));
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                panoGrille.add(tabButtonChoix[i][j]);
            }
        }

        // panocote
        valider.setText("Valider");
        valider.setPreferredSize(new Dimension(100,50));
        reset.setText("Reset");
        reset.setPreferredSize(new Dimension(100,50));
        JPanel espace2 = new JPanel();
        espace2.setPreferredSize( new Dimension(20,700));

        JPanel panocote = new JPanel(new GridLayout(4,1));
        panocote.add(valider);
        panocote.add(new JPanel());
        panocote.add(reset);

        // papaPano
        JPanel papaPano = new JPanel();
        papaPano.add(panoBouton);
        papaPano.add(espace);
        papaPano.add(panoGrille);
        papaPano.add(espace2);
        papaPano.add(panocote);

        // Affichage
        setContentPane(papaPano);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Met à jour les images de la grille en fonction de la classe Grille
    */
    public void majImage(){
        for(int i=1; i<9; i++){
            for(int j=1; j<9; j++) {
                System.out.print(gb[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ---");

        for(int i=1; i<9; i++){
            for(int j=1; j<9; j++){
                switch ( g.grille[i][j] ){
                    case 0 :
                        tabButton[i-1][j-1].setIcon(i0); break;
                    case 1 :
                        tabButton[i-1][j-1].setIcon(i1); break;
                    case 2 :
                        tabButton[i-1][j-1].setIcon(i2); break;
                    case 3 :
                        tabButton[i-1][j-1].setIcon(i3); break;
                    case 4 :
                        tabButton[i-1][j-1].setIcon(i4); break;
                    case 5 :
                        tabButton[i-1][j-1].setIcon(i5); break;
                    case 6 :
                        tabButton[i-1][j-1].setIcon(i6); break;
                    case 7 :
                        tabButton[i-1][j-1].setIcon(i7); break;
                    default:
                        tabButton[i-1][j-1].setIcon(i9); break;
                }
                switch (gb[i][j]){
                    case 1 :
                        tabButton[i-1][j-1].setIcon(i1b); break;
                    case 2 :
                        tabButton[i-1][j-1].setIcon(i2b); break;
                    case 3 :
                        tabButton[i-1][j-1].setIcon(i3b); break;
                    case 4 :
                        tabButton[i-1][j-1].setIcon(i4b); break;
                    case 5 :
                        tabButton[i-1][j-1].setIcon(i5b); break;
                    case 6 :
                        tabButton[i-1][j-1].setIcon(i6b); break;
                }
            }
        }
    }


    /**
     * Met à jour les coordonnées de "coordonnees"
     */
    public void majCoordonnees(int i, int j){
        coordonnees.setText("     i="+(i+1)+", j="+(j+1)+" piece="+g.grille[i+1][j+1]);
    }


    /**
     * Mets à jour les images dans grilleChoix
     */
    public void majImageDansGrilleChoix(int clic, int i, int j){
        switch (clic){
            case 0 : tabButtonChoix[i][j].setIcon(i0); break;
            case 1 : tabButtonChoix[i][j].setIcon(i1); break;
            case 2 : tabButtonChoix[i][j].setIcon(i2); break;
            case 3 : tabButtonChoix[i][j].setIcon(i3); break;
            case 4 : tabButtonChoix[i][j].setIcon(i4); break;
            case 5 : tabButtonChoix[i][j].setIcon(i5); break;
            case 6 : tabButtonChoix[i][j].setIcon(i6); break;
            case 7 : tabButtonChoix[i][j].setIcon(i7); break;
        }
    }


    /**
     *  Réinitialise grilleChoix
     */
    public void resetGrilleChoix(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                tabButtonChoix[i][j].setIcon(i0);
            }
        }
    }


    /**
     *  Change les bords du bouton qui est cliqué
     */
    public void changerLesBords(int choix){
        Border borderRED = BorderFactory.createLineBorder(java.awt.Color.RED);
        Border borderWHITE = BorderFactory.createLineBorder(java.awt.Color.WHITE);
        bouton0.setBorder(borderWHITE);
        bouton1.setBorder(borderWHITE);
        bouton2.setBorder(borderWHITE);
        bouton3.setBorder(borderWHITE);
        bouton4.setBorder(borderWHITE);
        bouton5.setBorder(borderWHITE);
        bouton6.setBorder(borderWHITE);
        bouton7.setBorder(borderWHITE);
        switch (choix){
            case 0 : bouton0.setBorder(borderRED); break;
            case 1 : bouton1.setBorder(borderRED); break;
            case 2 : bouton2.setBorder(borderRED); break;
            case 3 : bouton3.setBorder(borderRED); break;
            case 4 : bouton4.setBorder(borderRED); break;
            case 5 : bouton5.setBorder(borderRED); break;
            case 6 : bouton6.setBorder(borderRED); break;
            case 7 : bouton7.setBorder(borderRED); break;
        }
    }


    /**
     * Pour qu'il y ai bien les contrôles
     */
    public void setControlFenetre(ActionListener lis){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                tabButton[i][j].addActionListener(lis);
                tabButtonChoix[i][j].addActionListener(lis);
                tabButtonChoix[i][j].setIcon(i0);
            }
        }
        boutonMenu1.addActionListener(lis);
        boutonMenu2.addActionListener(lis);
        bouton0.addActionListener(lis);
        bouton1.addActionListener(lis);
        bouton2.addActionListener(lis);
        bouton3.addActionListener(lis);
        bouton4.addActionListener(lis);
        bouton5.addActionListener(lis);
        bouton6.addActionListener(lis);
        bouton7.addActionListener(lis);
        valider.addActionListener(lis);
        reset.addActionListener(lis);
    }

}
