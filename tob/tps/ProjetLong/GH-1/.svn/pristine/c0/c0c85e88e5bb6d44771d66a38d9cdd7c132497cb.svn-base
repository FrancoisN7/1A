package minijeux.minijeu_falaise;

import Joueur.Joueur;
import Zone.Modele.Tronceon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Ecran extends JFrame implements KeyListener{
	
	public final static int largeurEcran = 576;
	public final static int longueurEcran = 768;
	private final int vitesse = 6;
	private final int tempsTotal = 20000;
	private int tempsEcoule;
	
	private JFrame fenetre;
	
	public static final int fps = 30;
	private boolean perdu;
	private int nbVies;
	
	private List<JLabel> labelProjectiles;
	private List<Projectile> projectiles;
	
	private final List<ImageIcon> images = new ArrayList<>();
	{
		images.add(new ImageIcon(this.getClass().getResource("pierre1.jpg")));
		images.add(new ImageIcon(this.getClass().getResource("pierre2.jpg")));
		images.add(new ImageIcon(this.getClass().getResource("pierre3.jpg")));
		images.add(new ImageIcon(this.getClass().getResource("pierre4.jpg")));
		images.add(new ImageIcon(this.getClass().getResource("pierre5.jpg")));
	}
	
	private JLabel joueur;
	private JLabel vies;
	private JLabel barreTemps;
	
	public boolean getPerdu() {
		return perdu;
	}
	

	public Ecran(Joueur modeleJoueur, Tronceon tronceonATraverse) {
		perdu = false;
		nbVies = 3;
		tempsEcoule = 0;

		this.fenetre = new JFrame("Survivez à l'escalade de la falaise !");
		
		this.fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.fenetre.setSize(largeurEcran, longueurEcran);
		this.fenetre.setLayout(null);
		this.fenetre.addKeyListener(this);
		
		
		joueur = new JLabel();
		ImageIcon grimpeur = new ImageIcon(this.getClass().getResource("grimpeur1.jpg"));
		joueur.setBounds(largeurEcran/2, longueurEcran-200, 60, 108);
		joueur.setIcon(grimpeur);
		fenetre.add(joueur);
		//ajouterLabel(joueur, largeurEcran/2, longueurEcran-50, 16, 16, Color.red);
		
		vies = new JLabel("vies restantes : " + nbVies);
		vies.setBounds(largeurEcran-200, 50, 200, 50);
		vies.setForeground(Color.black);
		vies.setFont(new Font("Serif", Font.BOLD, 15));
		this.fenetre.add(vies);
		
		barreTemps = new JLabel();
		ajouterLabel(barreTemps, 0, 0, largeurEcran, 20, Color.green);
		JLabel barreGauche = new JLabel();
		ajouterLabel(barreGauche, 0, 0, 10, longueurEcran, Color.red);
		JLabel barreDroite = new JLabel();
		ajouterLabel(barreDroite,largeurEcran-10, 0, 10, longueurEcran, Color.red);
		
		this.getContentPane().setBackground(Color.white);
		/*
		ImageIcon falaise = new ImageIcon(this.getClass().getResource("mur.jpg"));
	    JLabel fond = new JLabel();
	    fond.setBounds(0, 0, largeurEcran, longueurEcran);
	    fond.setIcon(falaise);
		this.fenetre.add(fond);
		*/
		
		this.fenetre.setVisible(true);

		this.projectiles = new ArrayList<>();
		this.labelProjectiles = new ArrayList<>();
		
		
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					jouer();
					
				} catch (finiException e) {
					timer.cancel();
					if (perdu) {
						modeleJoueur.setMessage("Vous n'avez pas réussi à traverser et faites chemin arrière");
					} else {
						tronceonATraverse.setEstPasse(true);
						modeleJoueur.obtenirCarte();
					}
					//System.exit(0);
					fenetre.dispose();
					
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000/fps);	
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'z' :
			joueur.setLocation(joueur.getX(), joueur.getY() - vitesse);
			break;
		case 's' :
			joueur.setLocation(joueur.getX(), joueur.getY() + vitesse);
			break;
		case 'q' :
			joueur.setLocation(joueur.getX() - vitesse, joueur.getY());
			break;
		case 'd' :
			joueur.setLocation(joueur.getX() + vitesse, joueur.getY());
			break;
		}
	}
	
	
	public void jouer() throws finiException {
		majPosition();
			
		majCollision();
		if (tempsEcoule>tempsTotal) {
			throw new finiException();
		}
		tempsEcoule += 1000/fps;
	}
	
	
	public void majPosition() {
		JLabel label;
		Projectile proj = Projectile.randomProjectile();
		if (proj != null) {
			ajouter(proj);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			proj = projectiles.get(i);
			proj.translater(0, proj.getVitesse());
			label = labelProjectiles.get(i);
			label.setLocation(label.getX(), label.getY() + proj.getVitesse());
			if (proj.getCentre().getY() > longueurEcran) {
				retirer(i);
			} 
			
		}
		barreTemps.setLocation(-(int)(((float)tempsEcoule/tempsTotal)*largeurEcran), barreTemps.getY());
	}
	
	public void majCollision() throws finiException {
		Projectile proj;
		int x = joueur.getX();
		int y = joueur.getY();
		if (x<0 | x>largeurEcran | y<0 | y>longueurEcran) {
			perdu = true;
			throw new finiException();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			proj = projectiles.get(i);
			
			if (proj.getCentre().distance(new Point(x, y)) < proj.getRayon()) {
				nbVies --;
				vies.setText("vies restantes : " + nbVies);
				this.fenetre.add(vies);
				retirer(i);
				if (nbVies <=0) {
					perdu = true;
					throw new finiException();
				}
			}
		}
	}
	
	
	public void ajouter(Projectile proj) {
		JLabel label = new JLabel();
		int rayon = proj.getRayon();
		int x = proj.getCentre().getX() - rayon/2;
		int y = proj.getCentre().getY();
		label.setBounds(x, y, rayon, rayon);
		
		
		label.setIcon(images.get(rayon/10-1));
		//ajouterLabel(label, x, y, rayon, rayon, Color.blue);
		fenetre.add(label);
		
		labelProjectiles.add(labelProjectiles.size(), label);
		projectiles.add(projectiles.size(), proj);
	}
	
	public void retirer(int i) {
		this.fenetre.remove(labelProjectiles.get(i));
		projectiles.remove(i);
		labelProjectiles.remove(i);
	}
	
	public void ajouterLabel(JLabel label, int longueur, int largeur, int x, int y, Color couleur) {
		label.setBounds(longueur, largeur, x, y);
		label.setBackground(couleur);
		label.setOpaque(true);
		this.fenetre.add(label);
	}
}
