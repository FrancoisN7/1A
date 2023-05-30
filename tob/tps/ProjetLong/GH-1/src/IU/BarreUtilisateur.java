package IU;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import Joueur.Joueur;
import net.miginfocom.swing.MigLayout;
import Barre_Outils.Inventaire;
import Carte.VC.CarteFenetre;

public class BarreUtilisateur extends JPanel implements PropertyChangeListener {

	static final int width = 1280;
	static final int height = 120;

	Joueur joueur;

	JLabel messageJoueurLabel;
	
	JButton boutonSac = new JButton("Inventaire");

	JButton boutonCarte = new JButton("Carte");

	CarteFenetre carteFenetre;

	private final AfficheurCarte afficheurCarte;


	/**
	 * 
	 */
	public BarreUtilisateur(Joueur joueur) {
		super();
		this.joueur = joueur;

		// Paramètres de la barre
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
		this.setLayout(new MigLayout("", "[1100::]", "[100::]"));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		// Affichage du message pour le joueur
		messageJoueurLabel = new JLabel();
		messageJoueurLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		messageJoueurLabel.setForeground(Color.WHITE);
		messageJoueurLabel.setHorizontalAlignment(JLabel.CENTER);
		messageJoueurLabel.setVerticalAlignment(JLabel.CENTER);
		this.add(messageJoueurLabel);
		joueur.addPropertyChangeListener(this);
		carteFenetre = new CarteFenetre(joueur);
		carteFenetre.setVisible(false);

		afficheurCarte = new AfficheurCarte(joueur);
	}

	private class AfficheurCarte implements ActionListener {

		private Joueur joueur;
		public AfficheurCarte(Joueur joueur){
			this.joueur = joueur;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String nomZone  = joueur.getZoneCourante().getNom();
			if (nomZone.equals("Grotte Sombre") || nomZone.equals("Grotte Noire")) {
				carteFenetre.setVisible(false);
				joueur.setMessage("Vous ne parvenez pas à lire la carte dans la pénombre!");
			} else {
				carteFenetre.setVisible(true);
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals("message")) {
			messageJoueurLabel.setText((String) evt.getNewValue());
		}
		else if (evt.getPropertyName().equals("carte obtenue")) {
			boutonCarte.addActionListener(afficheurCarte);
			this.add(boutonCarte, "dock west");
		}
		else if (evt.getPropertyName().equals("carte modifiée")){
			carteFenetre.dispose();
			carteFenetre = new CarteFenetre(joueur);
		}
	}

	public void initialiser() {
		this.removeAll();
		joueur.setMessage("");
		this.add(messageJoueurLabel, "dock center");
		
		
        
		
		// Bouton inventaire
		
		boutonSac.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new Inventaire(joueur.getInventaire());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		this.add(boutonSac, "dock east");
		this.repaint();


	}
	
}
