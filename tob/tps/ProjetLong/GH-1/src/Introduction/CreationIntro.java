package Introduction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;



public class CreationIntro {

	///private static Graphics2D panel;
	private JFrame frame;
	private  JPanel panel; 
	private JLabel labelText;
	private String[] tabtexte;
	
	/** Bouton pour quitter */
	private final JButton boutonPasser = new JButton("Passer l'introduction");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonContinuer = new JButton("Continuer");
	
	public CreationIntro(JFrame fenetre, JPanel panel, String[] tableau) {
		this.frame = fenetre;
		this.panel= panel;
		this.tabtexte =tableau;
		this.labelText = new JLabel();
		
	}
	
	public void definirTexteIntro(String texte1, String texte2, String texte3) {
		tabtexte[0] = texte1;
		tabtexte[1] = texte2;
		tabtexte[2] =texte3;
	}
	
	public void construireFenetre() throws IOException {
		construirePanel();
	    
	    frame.add(panel);
	    final ImageIcon icon = new ImageIcon(this.getClass().getResource("avion.jpg"));
	    JTextArea text = new JTextArea()
	    {
	      Image img = icon.getImage();
	      // initialiseur d'instance
	      {setOpaque(false);}
	      public void paintComponent(Graphics graphics)
	      {
	        graphics.drawImage(img, 0, 0, this);
	        super.paintComponent(graphics);
	      }
	    };
	    JScrollPane pane = new JScrollPane(text);
	    Container content = frame.getContentPane();
	    content.add(pane, BorderLayout.CENTER);
	    frame.setDefaultCloseOperation(3);

	    frame.setSize(3008, 2000);
	    //frame.setLayout(null);
	    frame.setVisible(true);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void construirePanel() {
		panel.setBounds(800,50,500,150);  
	      //Spécifier la couleur d'arrière-plan du JPanel

	      panel.setBackground(Color.white);
	     
	    //Lay out the label and scroll pane from top to bottom.
	      JPanel listPane = new JPanel();
	      listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
	      
	      //JLabel labelText = new JLabel(tabtexte[0]);
	      labelText.setText(tabtexte[0]);
	      
	      listPane.add(labelText);
	      listPane.add(Box.createRigidArea(new Dimension(0,5)));

	      listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

	      //Lay out the buttons from left to right.
	      JPanel buttonPane = new JPanel();
	      buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
	      buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	      buttonPane.add(Box.createHorizontalGlue());
	      buttonPane.add(boutonContinuer);
	      buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
	      buttonPane.add(boutonPasser);

	      //Put everything together, using the content pane's BorderLayout.
	      panel.setLayout(new BorderLayout());
	      panel.add(listPane, BorderLayout.CENTER);
	      panel.add(buttonPane, BorderLayout.PAGE_END);
	     
	    //JTextArea texte = new JTextArea(tabtexte[0]);

	   // cases[0][0].setText(tabtexte[0]);

		//panel.add(boutonContinuer);
		boutonContinuer.addActionListener(new ActionContinuer());
			
		//panel.add(boutonPasser);
		boutonPasser.addActionListener(new ActionPasser());	
	      
	}
	
	
	private class ActionContinuer implements ActionListener {
		private int compteur;
		
		 public void actionPerformed(ActionEvent continuer) {
			 compteur ++;
			continuer(compteur);
		} }
	
	public void continuer(int compteur) {

		if (compteur <= tabtexte.length - 1) {
			labelText.setText(tabtexte[compteur]);
		} else {
			passer();
		}
		
	}
	
	private class ActionPasser implements ActionListener {
		 public void actionPerformed(ActionEvent passer) {
			 passer();
		} }
	
	private void passer() {
		System.exit(1);
	}

	}
