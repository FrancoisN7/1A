package Bataille_Navale;
import GestionnaireJeu.GestionnaireDeJeu;
import Zone.Modele.Indien;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class BatailleSwing {
	enum figure {ROND,CROIX};
	// les images à utiliser en fonction de l'état du jeu.
	private final Map<ModeleBataille.Etat, ImageIcon> images = new HashMap<ModeleBataille.Etat, ImageIcon>();
	{
		images.put(ModeleBataille.Etat.VIDE, new ImageIcon(this.getClass().getResource("blanc.jpg")));
		images.put(ModeleBataille.Etat.CROIX, new ImageIcon(this.getClass().getResource("eaupetit.jpg")));
		images.put(ModeleBataille.Etat.ROND, new ImageIcon(this.getClass().getResource("bateaupetit.jpg")));
	}

	private ModeleBataille modele; 

//  Les éléments de la vue (IHM)
//  ----------------------------
	private PlacerSwing grillePersoSwing;
	/** Fenêtre principale */
	private JFrame fenetre;

	/** Bouton pour quitter */
	private final JButton boutonQuitter = new JButton("Q");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonNouvellePartie = new JButton("N");

	/** Cases du jeu */
	private final JLabel[][] cases = new JLabel[ModeleBataille.TAILLE][ModeleBataille.TAILLE];

	/** Zone qui indique le joueur qui doit jouer */
	private final JLabel joueur = new JLabel();

	private Indien indien;
	private GestionnaireDeJeu gestionnaireDeJeu;

// Le constructeur
// ---------------

	public BatailleSwing(ModeleGrillePerso grillePerso, Indien indien, GestionnaireDeJeu gestionnaireDeJeu) {
		this(new ModeleBatailleSimple(grillePerso));
		
		this.grillePersoSwing = new PlacerSwing(grillePerso);
		this.indien = indien;
		this.gestionnaireDeJeu = gestionnaireDeJeu;
	}

	public BatailleSwing(ModeleGrillePerso grillePerso) {
		this(new ModeleBatailleSimple(grillePerso));

		this.grillePersoSwing = new PlacerSwing(grillePerso);
	}

	public BatailleSwing(ModeleBataille modele) {
		// Initialiser le modèle
		this.modele = modele;
		ConstruireGrille(800,200);
	}
	
	public void ConstruireGrille(int position_x, int position_y) {

		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = new JLabel();
			}
		}

		// Initialiser le jeu
		this.recommencer();

		
		this.fenetre = new JFrame("Bataille_Navale");
		this.fenetre.setLocation(position_x, position_y);
		this.fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// afficher la fenêtre
		this.fenetre.setMinimumSize(new Dimension(305, 350));
		this.fenetre.pack(); // redimmensionner la fenêtre
		this.fenetre.setVisible(true); // l'afficher

		// Création d'un support contenu
		java.awt.Container contenu = fenetre.getContentPane();
		contenu.setLayout(new java.awt.GridLayout(ModeleBataille.TAILLE, ModeleBataille.TAILLE));

		// Création du menu
		// créer un menu
		JMenuBar menu = new JMenuBar();
		// Ajout du menu
		contenu.add(menu);

		// créer les élémnts de menu
		JMenu jeu = new JMenu("Jeu");

		// créer les sous-menu
		JMenuItem nouveau = new JMenuItem("Nouvelle partie");
		nouveau.addActionListener(null); // A modifier
		jeu.add(nouveau);

		JMenuItem quitter = new JMenuItem("Quitter");
		jeu.add(quitter);
		quitter.addActionListener(null); // A modifier
		menu.add(jeu);
		this.fenetre.setJMenuBar(menu);

		
		// Création des carrés blancs
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				contenu.add(this.cases[i][j]);
				this.cases[i][j].addMouseListener(new Click(i,j));
			}
		}
	}
	public ModeleBataille getBataille() {
		return modele;
	}
	
	private class Click extends MouseAdapter {
		int i;
		int j;
		public Click(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			ajouterIcone(i, j, figure.CROIX);
			 if (modele.estGagnee()) {
				 indien.setTentative(Indien.Tentative.DEJAREUSSI);
				 gestionnaireDeJeu.getJoueur().setMessage("Vous avez gagné!");
				 fermer();
			 }
		}
	}
	
	public void fermer() {
		
		fenetre.dispose();
		grillePersoSwing.fenetre.dispose();
	}
	
	// Ajouter une croix ou un rond
	public void ajouterIcone(int i, int j, figure figure) {
		try {
			modele.cocher(i, j);
		} catch (CaseOccupeeException e) {
		}
		cases[i][j].setIcon(images.get(modele.getValeur(i, j)));
	}

	/** Recommencer une nouvelle partie. */
	public void recommencer() {
		this.modele.recommencer();

		// Vider les cases
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j].setIcon(images.get(this.modele.getValeur(i, j)));
			}
		}

	}

}
