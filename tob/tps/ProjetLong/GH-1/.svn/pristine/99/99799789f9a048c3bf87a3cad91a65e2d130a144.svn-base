package Zone.VC;

import Affichable.Interactif;

import javax.swing.*;
import java.awt.*;

public class LabelElementInteractif extends JLabel {

    protected final Interactif element;

    public LabelElementInteractif(Interactif element){
        super(element.getImage());
        this.element = element;
        this.setPreferredSize(new Dimension(element.getLargeur(), element.getHauteur()));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setForeground(Color.BLACK);
        this.setBounds(element.getX(), element.getY(), 2*element.getLargeur(), 2*element.getHauteur());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

}
