package Bataille_Navale;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class PlacerSwing {
	enum figure {ROND,CROIX};
	
	// les images à utiliser en fonction de l'état du jeu.
	private final Map<ModeleGrillePerso.Etat, ImageIcon> images = new HashMap<ModeleGrillePerso.Etat, ImageIcon>();
	{
		images.put(ModeleGrillePerso.Etat.VIDE, new ImageIcon(this.getClass().getResource("blanc.jpg")));
		images.put(ModeleGrillePerso.Etat.CROIX, new ImageIcon(this.getClass().getResource("bateaupetit.jpg")));
		images.put(ModeleGrillePerso.Etat.ROND, new ImageIcon(this.getClass().getResource("rond.jpg")));
	}


	private ModeleGrillePerso modele; 

//  Les éléments de la vue (IHM)
//  ----------------------------

	/** Fenêtre principale */
	public JFrame fenetre;

	/** Bouton pour quitter */
	private final JButton boutonQuitter = new JButton("Q");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonNouvellePartie = new JButton("N");

	/** Cases du jeu */
	private final JLabel[][] cases = new JLabel[ModeleGrillePerso.TAILLE][ModeleGrillePerso.TAILLE];

	/** Zone qui indique le joueur qui doit jouer */
	private final JLabel joueur = new JLabel();

// Le constructeur
// ---------------

	/** Construire le jeu de morpion */
	public PlacerSwing() {
		this(new ModeleGrillePersoSimple());
	}
	public PlacerSwing(ModeleGrillePerso modele) {
		// Initialiser le modèle
		this.modele = modele;
		ConstruireGrille(100,200);
	}
	/** Construire le jeu de morpion */
	public void ConstruireGrille(int position_x, int position_y) {

		// Créer les cases du Morpion
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = new JLabel();
			}
		}

		// Initialiser le jeu
		this.recommencer();

		// Construire la vue (présentation)
		// Définir la fenêtre principale
		
		this.fenetre = new JFrame("Bataille_Navale");
		this.fenetre.setLocation(position_x, position_y);

		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// afficher la fenêtre
		this.fenetre.setMinimumSize(new Dimension(305, 350));
		this.fenetre.pack(); // redimmensionner la fenêtre
		this.fenetre.setVisible(true); // l'afficher

		// Création d'un support contenu
		java.awt.Container contenu = fenetre.getContentPane();
		contenu.setLayout(new java.awt.GridLayout(ModeleGrillePerso.TAILLE, ModeleGrillePerso.TAILLE));

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
	
	public ModeleGrillePerso getGrille() {
		return this.modele;
	}
	
	
	public void cochercasebot(int i, int j) {
		try {
			modele.cocherRandom(i, j);
		} catch (CaseOccupeeException e) {
		}
		cases[i][j].setIcon(images.get(modele.getValeur(i, j)));
		
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
			;
		}
	}
	
	// Ajouter une croix ou un rond
	public void ajouterIcone(int i, int j, figure figure) {
		try {
			modele.cocherCreer(i, j);
		} catch (CaseOccupeeException e) {
		}
		cases[i][j].setIcon(images.get(modele.getValeur(i, j)));
	}

// Quelques réactions aux interactions de l'utilisateur
// ----------------------------------------------------

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

// La méthode principale
// ---------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PlacerSwing();
			}
		});
	}

}
