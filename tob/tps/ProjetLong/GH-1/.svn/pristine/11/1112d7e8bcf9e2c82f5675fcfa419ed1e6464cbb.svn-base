package Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class MenuReprendre {


    //** Constructeur du menu reprendre. */
    public MenuReprendre(){

        // Construire la fenêtre de jeu

        /* Fenetre du menu */
        JFrame fenetre = new JFrame("Jeu d'aventure");
        fenetre.setPreferredSize(new Dimension(1450, 900));
        fenetre.setLocation(200, 3000);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contenu  = fenetre.getContentPane();
        contenu.setLayout(null);


        /* Bouton pour lancer le premier profil */
        JButton profil1 = new JButton("Profil du joueur 1");
        profil1.setForeground(Color.WHITE);
        profil1.setFont(new Font("Serif", Font.BOLD, 25));
        profil1.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("pacarte_bois.jpeg"))));
        profil1.setVerticalTextPosition(SwingConstants.CENTER); 
        profil1.setHorizontalTextPosition(SwingConstants.CENTER);
        profil1.setBounds(575, 200, 300, 125);

        /* Bouton pour lancer le deuxième profil */
        JButton profil2 = new JButton("Profil du joueur 2");
        profil2.setForeground(Color.WHITE);
        profil2.setFont(new Font("Serif", Font.BOLD, 25));
        profil2.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("pacarte_bois.jpeg"))));
        profil2.setVerticalTextPosition(SwingConstants.CENTER); 
        profil2.setHorizontalTextPosition(SwingConstants.CENTER);
        profil2.setBounds(575, 400, 300, 125);


        /* Bouton pour lancer le troisième profil */
        JButton profil3 = new JButton("Profil du joueur 3");
        profil3.setForeground(Color.WHITE);
        profil3.setFont(new Font("Serif", Font.BOLD, 25));
        profil3.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("pacarte_bois.jpeg"))));
        profil3.setVerticalTextPosition(SwingConstants.CENTER); 
        profil3.setHorizontalTextPosition(SwingConstants.CENTER);
        profil3.setBounds(575, 600, 300, 125);

        /* Titre du menu */
        JLabel titreReprendre = new JLabel("Choisissez votre profil");
        titreReprendre.setBounds(280, 15, 1000, 200);
        titreReprendre.setForeground(Color.WHITE);
        titreReprendre.setFont(new Font("Serif", Font.BOLD, 75));

        /* Fond du menu */
        JLabel fondReprendre = new JLabel(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("ile_deserte3.jpeg"))));
        fondReprendre.setBounds(0, 0, 1450, 900);
        fondReprendre.add(titreReprendre);
        
        fondReprendre.add(profil1);
		fondReprendre.add(profil2);
		fondReprendre.add(profil3);

        contenu.add(fondReprendre);
		

        // Afficher la fenêtre
		fenetre.pack();
		fenetre.setVisible(true);


    }
}
