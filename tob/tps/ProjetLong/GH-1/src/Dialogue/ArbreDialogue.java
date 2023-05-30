package Dialogue;

import GestionnaireJeu.GestionnaireDeJeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArbreDialogue {


	private class Cellule {
		ArrayList<String> options;
		ArrayList<Cellule> enfants;
		String phrase;

		Executable executable;
		public Cellule(Executable executable, String phrase, String... options) {
			this.options = new ArrayList<>(Arrays.asList(options));
			this.enfants = new ArrayList<>();
			this.phrase = phrase;
			this.executable = executable;
		}
	}

	private Cellule racine;

	private Cellule celluleCourante;

	public ArbreDialogue(Executable executable, String phrase, String... options) {
		this.racine = new Cellule(executable, phrase, options);
		this.celluleCourante = this.racine;
	}

	public List<String> getOptions() {
		return celluleCourante.options;
	}

	public String getPhrase() {
		return celluleCourante.phrase;
	}

	public void retourARacine() {
		celluleCourante = racine;
	}

	public void ajouterEnfant(Executable executable, String phrase, String... options) {
		celluleCourante.enfants.add(new Cellule(executable, phrase, options));
	}

	public void executerConsequences(){
		if (this.celluleCourante.executable != null) {
			this.celluleCourante.executable.executer();
		}
	}

	public Boolean estTermine() {
		return celluleCourante.enfants.size() == 0;
	}

	public void descendreSurEnfant(int choix) {
		celluleCourante = celluleCourante.enfants.get(choix - 1);
	}
}
