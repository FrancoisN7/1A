package Menu;

import javax.swing.*;

import com.formdev.flatlaf.*;

import java.awt.*;


import java.awt.event.*;
import java.util.Objects;


import Introduction.Introduction;

public class MenuDemarrage {


    /** Fenetre du menu */
    private final JFrame fenetre;

    /** Constructeur du menu de démarrage. */
    public MenuDemarrage(){

        // Construire la fenêtre de jeu
        this.fenetre = new JFrame("Jeu d'aventure");
        fenetre.setPreferredSize(new Dimension(1450, 900));
        this.fenetre.setLocation(200, 3000);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenu = fenetre.getContentPane();
        contenu.setLayout(null);

        /* Bouton demarrer */
        JButton boutonDemarrer = new JButton("Demarrer");
        boutonDemarrer.setForeground(Color.BLACK);
        boutonDemarrer.setFont(new Font("Serif", Font.BOLD, 25));
        boutonDemarrer.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("jaune_bouton.jpeg"))));
        boutonDemarrer.setVerticalTextPosition(SwingConstants.CENTER); 
        boutonDemarrer.setHorizontalTextPosition(SwingConstants.CENTER);
        boutonDemarrer.setBounds(575, 200, 300, 125);

        /* Bouton reprendre */
        JButton boutonReprendre = new JButton("Reprendre");
        boutonReprendre.setForeground(Color.BLACK);
        boutonReprendre.setFont(new Font("Serif", Font.BOLD, 25));
        boutonReprendre.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("jaune_bouton.jpeg"))));
        boutonReprendre.setVerticalTextPosition(SwingConstants.CENTER); 
        boutonReprendre.setHorizontalTextPosition(SwingConstants.CENTER);
        boutonReprendre.setBounds(575, 400, 300, 125);
 
        /* Bouton quitter */
        JButton boutonQuitter = new JButton("Quitter");
        boutonQuitter.setForeground(Color.BLACK);
        boutonQuitter.setFont(new Font("Serif", Font.BOLD, 25));
        boutonQuitter.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("jaune_bouton.jpeg"))));
        boutonQuitter.setVerticalTextPosition(SwingConstants.CENTER); 
        boutonQuitter.setHorizontalTextPosition(SwingConstants.CENTER); 
        boutonQuitter.setBounds(575, 600, 300, 125);

        /* Titre du jeu */
        JLabel titre = new JLabel("L'île 100 fin");
        titre.setBounds(500, 15, 1000, 200);
        titre.setForeground(Color.BLUE);
        titre.setFont(new Font("Serif", Font.BOLD, 75));

        /* Fond du menu */
        JLabel fond = new JLabel(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("fond_menu.jpg"))));
        fond.setBounds(0, 0, 1450, 900);
        fond.add(titre);
        
        fond.add(boutonDemarrer);
		fond.add(boutonReprendre);
		fond.add(boutonQuitter);

        contenu.add(fond);
		

        boutonDemarrer.addActionListener(new ActionDemarrer());

        boutonReprendre.addActionListener(new ActionReprendre());

		boutonQuitter.addActionListener(new ActionQuitter());

        // Afficher la fenêtre
		this.fenetre.pack();
		this.fenetre.setVisible(true);

    }

    private static class ActionQuitter implements ActionListener {
		public void actionPerformed(ActionEvent quitter){
			System.exit(0);
		}
	}  

    private class ActionReprendre implements ActionListener {
		public void actionPerformed(ActionEvent reprendre){
            fenetre.dispose();
            new MenuReprendre();	
		}
    }

    private class ActionDemarrer implements ActionListener {
        public void actionPerformed(ActionEvent demarrer){
                fenetre.dispose();
                new Introduction("Le crash de votre avion vous laisse seul sur une île déserte.", "L'objectif? S'enfuir vivant de l'île.", "Et attention, chaque mauvaise décision met votre vie en péril.");
        }
	} 

    // La méthode principale
	public static void main(String[] args) {
        FlatLightLaf.setup();
		EventQueue.invokeLater(() -> new MenuDemarrage());
	}


}

