package Dialogue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DialogueVue extends JPanel implements PropertyChangeListener {

	List<JLabel> labelsChoix;
	JLabel labelPhrase;

	DialogueIU dialogueFenetre;

	public DialogueVue(Dialogue dialogue, DialogueIU dialogueFenetre){
		super();
		this.dialogueFenetre = dialogueFenetre;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Affichage image
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		JLabel labelImage = new JLabel(dialogue.getImage());
		this.add(labelImage, c);

		// Affichage phrase
		labelPhrase = new JLabel(dialogue.getPhrase());
		labelPhrase.setFont(new Font("Verdana", Font.PLAIN, 18));
		labelPhrase.setVerticalAlignment(JLabel.CENTER);
		labelPhrase.setHorizontalAlignment(JLabel.CENTER);
		labelPhrase.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 10;
		this.add(labelPhrase, c);

		// Affichage options
		labelsChoix = new ArrayList<>();
		int i = 0;
		for (String option : dialogue.getOptions()){
			JLabel labelOption = new JLabel(option);
			labelsChoix.add(labelOption);
			c.gridx = 0;
			c.gridy = i + 3;
			c.weighty = 0.5;
			c.ipady = 10;
			i++;
			this.add(labelOption, c);
		}
		dialogue.addPropertyChangeListener(this);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("options")){
			int i = 0;
			for (JLabel labelChoix : labelsChoix){
				labelChoix.setText("");
			}
			List<?> options = (List<?>) evt.getNewValue();
			for (Object option : options) {
				labelsChoix.get(i++).setText((String) option);
			}
			dialogueFenetre.pack();
		}
		else if (evt.getPropertyName().equals("phrase")){
			labelPhrase.setText((String) evt.getNewValue());
			dialogueFenetre.pack();
		}

		else if (evt.getPropertyName().equals("Terminé")){
			dialogueFenetre.dispose();
		}
	}
}
