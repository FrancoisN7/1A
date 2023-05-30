package Barre_Outils;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JTextArea;
import java.awt.event.*;

public class Inventaire {

    /** Le modèle du jeu de Morpion. */
    private ModeleInventaire modele;	

    /** Fenetre de l'inventaire */
    private JFrame fenetreInv;

    /** Fond du menu */
    private final JLabel fondInv = new JLabel(new ImageIcon(this.getClass().getResource("vert2.jpeg")));

    /** Cases de l'inventaire */
	private final JLabel[][] cases = new JLabel[6][3];

    /** Titre */
    private final JLabel titre = new JLabel("Votre Inventaire");


    /** Construire l'inventaire */
    public Inventaire(ModeleInventaire modele){

        // Initialiser le modèle
		this.modele = modele;

        // Construire la fenêtre de jeu
        this.fenetreInv = new JFrame("Inventaire");
        fenetreInv.setPreferredSize(new Dimension(1000, 600));
        this.fenetreInv.setLocation(200, 0);

        // Construire le contrôleur (gestion des événements)
		this.fenetreInv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Container
		Container contenu = fenetreInv.getContentPane();
        contenu.setLayout(null);

        //Titre
        titre.setBounds(315, 15, 1000, 100);
        titre.setForeground(Color.ORANGE);
        titre.setFont(new Font("Serif", Font.BOLD, 40));

        //Fond
        fondInv.setBounds(0, 0, 1000, 600);
        fondInv.add(titre);
        
        // Créer les cases de l'inventaire
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = new JLabel(new ImageIcon(this.getClass().getResource(this.modele.getNom(i,j))));
                this.cases[i][j].setBounds(15 +150*i,50 + 150*j, 300, 300);
                cases[i][j].addMouseListener(new ActionPrendre(i, j, this.fenetreInv));
                fondInv.add(cases[i][j]);			
            }
		}
		
		contenu.add(fondInv);
        
        
        // Afficher la fenêtre
		this.fenetreInv.pack();			// redimmensionner la fenêtre
		this.fenetreInv.setVisible(true);	// l'afficher
		}

    private class ActionPrendre extends MouseAdapter {

		private int i;
		private int j;
		private JFrame fenetre;

		public ActionPrendre(int i, int j, JFrame fenetre){
			this.i = i;
			this.j = j;
			this.fenetre = fenetre;
		}

		@Override
		public void mouseClicked(MouseEvent prendre){
			try {
				modele.prendre(this.i, this.j);
				this.fenetre.dispose();
			} catch (CaseVideException e){
			}
			}
    }
    

}