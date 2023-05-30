package Dialogue;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.*;
import java.awt.EventQueue;

public class DialogueIU extends JDialog {

	public DialogueIU(DialogueSimple dialogue){
		super();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		DialogueVue dialogueVue = new DialogueVue(dialogue, this);
		DialogueControleur dialogueControleur = new DialogueControleur(dialogue);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(dialogueVue, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.5;
		this.add(dialogueControleur, c);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
	}
	
}
