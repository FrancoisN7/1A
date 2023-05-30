package Dialogue;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

class FiltreEntier extends DocumentFilter {

	private Dialogue dialogue;

	public FiltreEntier(Dialogue dialogue){
		super();
		this.dialogue = dialogue;
	}
	@Override
	public void insertString(FilterBypass fb, int offset, String string,
		  AttributeSet attr) throws BadLocationException {
 
	   Document doc = fb.getDocument();
	   StringBuilder sb = new StringBuilder();
	   sb.append(doc.getText(0, doc.getLength()));
	   sb.insert(offset, string);
 
	   if (estChiffre(sb.toString())) {
		  super.insertString(fb, offset, string, attr);
	   }
	}
 
	private boolean estChiffre(String text) {
	   try {
		  if (text != null && text.length() != 0) {
		  	int entier = Integer.parseInt(text);
		  	return (0 < entier && entier <= dialogue.getOptions().size());
		  }
		  return true;
	   } catch (NumberFormatException e) {
		  return false;
	   }
	}
 
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,
		  AttributeSet attrs) throws BadLocationException {
 
	   Document doc = fb.getDocument();
	   StringBuilder sb = new StringBuilder();
	   sb.append(doc.getText(0, doc.getLength()));
	   sb.replace(offset, offset + length, text);
 
	   if (estChiffre(sb.toString())) {
		  super.replace(fb, offset, length, text, attrs);
	   }

	}
 
	@Override
	public void remove(FilterBypass fb, int offset, int length)
		  throws BadLocationException {
	   Document doc = fb.getDocument();
	   StringBuilder sb = new StringBuilder();
	   sb.append(doc.getText(0, doc.getLength()));
	   sb.delete(offset, offset + length);
 
	   if (sb.toString().length() == 0){
		   super.remove(fb, offset, length);
	   }
	   if (estChiffre(sb.toString())) {
		  super.remove(fb, offset, length);
	   } else {
	   }
 
	}
 }