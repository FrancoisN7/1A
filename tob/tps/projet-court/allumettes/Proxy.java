package allumettes;

public class Proxy implements Jeu {

	Jeu jeu;

	public Proxy(Jeu jeu) {
		assert (jeu != null);
		this.jeu = jeu;
	}

	@Override
	public int getNombreAllumettes() {
		return this.jeu.getNombreAllumettes();
	}

	@Override
	public void retirer(int prise) throws CoupInvalideException {
		throw new OperationInterditeException();

	}

}
