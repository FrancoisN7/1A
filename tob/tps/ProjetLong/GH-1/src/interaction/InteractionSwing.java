package interaction;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class InteractionSwing {



// Choix de réalisation :
// ----------------------
//
//  Les attributs correspondant à la structure fixe de l'IHM sont définis
//	« final static » pour montrer que leur valeur ne pourra pas changer au
//	cours de l'exécution.  Ils sont donc initialisés sans attendre
//  l'exécution du constructeur !
//  Les éléments de la vue (IHM)
//  ----------------------------

	/** Fenêtre principale */
	private JFrame fenetre;

	/** Reponses de l'interaction */
	private final JLabel[][] reponses = new JLabel[2][2];

	/** Zone qui indique perso qui parle */
	private final JLabel joueur = new JLabel();
	
	/** Zone ou le perso parle */
	private final JLabel texte = new JLabel();
	
	private ModeleInteraction modele;

// Le constructeur
// ---------------

	/** Construire le jeu de morpion */
	public InteractionSwing(ModeleInteractionSimple modele) {
		
		//this.perso = perso;
		//this.reponse1 = reponse1;
		//this.reponse2 = reponse2;
		//this.reponse3 = reponse3;
		//this.reponse4 = reponse4;
		//this.texteperso = texteperso;
		this.modele = modele;
		this.fenetre = new JFrame("interaction");
		this.fenetre.setLocation(100, 200);
		Container contenu = fenetre.getContentPane();

		
		contenu.setLayout(new java.awt.BorderLayout(3,1));
		JPanel tablereponse = new JPanel(new java.awt.GridLayout(2,2));
		this.joueur.setIcon(modele.getPerso());
		this.texte.setText(modele.getTexteperso());
		for (int i = 0;i<2;i++) {
			for (int j = 0; j<2; j++) {
				this.reponses[i][j] = new JLabel();
				this.reponses[i][j].addMouseListener(new Click(i,j));
				tablereponse.add(this.reponses[i][j]);
				
			}
		}
		this.reponses[0][0].setText(modele.getReponse1());
		this.reponses[0][1].setText(modele.getReponse2());
		this.reponses[1][0].setText(modele.getReponse3());
		this.reponses[1][1].setText(modele.getReponse4());
		
		contenu.add(this.texte, BorderLayout.NORTH);
		contenu.add(this.joueur, BorderLayout.CENTER);
		contenu.add(tablereponse, BorderLayout.SOUTH);
		
		
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// afficher la fenêtre
		this.fenetre.setSize(400, 400);			// redimmensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
	}

// Quelques réactions aux interactions de l'utilisateur
// ----------------------------------------------------

	/** Recommencer une nouvelle partie. */
	public void agir1() {
		this.modele.agir1();
	}
	
	public void agir2() {
		this.modele.agir2();
	}
	
	public void agir3() {
		this.modele.agir3();
	}
	
	public void agir4() {
		this.modele.agir4();
	}
	
	
	public class Click extends MouseAdapter {
		int i;
		int j;
		public Click(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (i==0) {
				if (j == 0) {
					agir1();
				} else {
					agir2();
				}
			} else {
				if (j == 0) {
					agir3();
				} else {
					agir4();
				}
			}
		}

		
	}
}
