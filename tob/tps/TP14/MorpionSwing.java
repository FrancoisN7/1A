import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

/**
 * Programmation d'un jeu de Morpion avec une interface graphique Swing.
 *
 * REMARQUE : Dans cette solution, le patron MVC n'a pas été appliqué ! On a un
 * modèle (?), une vue et un contrôleur qui sont fortement liés.
 *
 * @author Xavier Crégut
 * @version $Revision: 1.4 $
 */

public class MorpionSwing {
	enum figure {ROND,CROIX};
	// les images à utiliser en fonction de l'état du jeu.
	private static final Map<ModeleMorpion.Etat, ImageIcon> images = new HashMap<ModeleMorpion.Etat, ImageIcon>();
	static {
		images.put(ModeleMorpion.Etat.VIDE, new ImageIcon("blanc.jpg"));
		images.put(ModeleMorpion.Etat.CROIX, new ImageIcon("croix.jpg"));
		images.put(ModeleMorpion.Etat.ROND, new ImageIcon("rond.jpg"));
	}

// Choix de réalisation :
// ----------------------
//
//  Les attributs correspondant à la structure fixe de l'IHM sont définis
//	« final static » pour montrer que leur valeur ne pourra pas changer au
//	cours de l'exécution.  Ils sont donc initialisés sans attendre
//  l'exécution du constructeur !

	private ModeleMorpion modele; // le modèle du jeu de Morpion

//  Les éléments de la vue (IHM)
//  ----------------------------

	/** Fenêtre principale */
	private JFrame fenetre;

	/** Bouton pour quitter */
	private final JButton boutonQuitter = new JButton("Q");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonNouvellePartie = new JButton("N");

	/** Cases du jeu */
	private final JLabel[][] cases = new JLabel[6][6];

	/** Zone qui indique le joueur qui doit jouer */
	private final JLabel joueur = new JLabel();

// Le constructeur
// ---------------

	/** Construire le jeu de morpion */
	public MorpionSwing() {
		this(new ModeleMorpionSimple());
	}

	/** Construire le jeu de morpion */
	public MorpionSwing(ModeleMorpion modele) {
		// Initialiser le modèle
		this.modele = modele;

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
		this.fenetre = new JFrame("Morpion");
		this.fenetre.setLocation(100, 200);

		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// afficher la fenêtre
		this.fenetre.setMinimumSize(new Dimension(153*2, 199*2));
		this.fenetre.pack(); // redimmensionner la fenêtre
		this.fenetre.setVisible(true); // l'afficher

		// Création d'un support contenu
		java.awt.Container contenu = fenetre.getContentPane();
		contenu.setLayout(new java.awt.GridLayout(6, 6));

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
			modele.cocher(i, j);
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

		// Mettre à jour le joueur
		joueur.setIcon(images.get(modele.getJoueur()));
	}

// La méthode principale
// ---------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MorpionSwing();
			}
		});
	}

}
