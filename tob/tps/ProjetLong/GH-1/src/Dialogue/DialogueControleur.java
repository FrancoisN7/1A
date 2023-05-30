package Dialogue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DialogueControleur extends JPanel {

	public DialogueControleur(Dialogue dialogue){
		super();
		this.setLayout(new FlowLayout());

		JLabel labelText = new JLabel("Votre choix: ");
		JTextField zoneDeTexte = new JTextField(2);
		PlainDocument doc = (PlainDocument) zoneDeTexte.getDocument();
		doc.setDocumentFilter(new FiltreEntier(dialogue));
		zoneDeTexte.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					if (zoneDeTexte.getText() != null && zoneDeTexte.getText().length() != 0) {
						dialogue.setChoix(Integer.parseInt(zoneDeTexte.getText()));
						zoneDeTexte.setText("");
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});

		this.add(labelText);
		this.add(zoneDeTexte);
	}
	
}
