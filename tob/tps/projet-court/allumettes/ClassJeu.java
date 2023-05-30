package allumettes;

public class ClassJeu implements Jeu {
	int NombreAllu;

	public ClassJeu(int nbAllu) {
		assert (nbAllu != 0);
		this.NombreAllu = nbAllu;
	}

	@Override
	public int getNombreAllumettes() {
		return NombreAllu;
	}

	@Override
	public void retirer(int prise) throws CoupInvalideException {
		if (prise < 1) {
			throw new CoupInvalideException(prise, "(< 1)");
		}
		if (prise > NombreAllu) {
			throw new CoupInvalideException(prise, "NbAl");
		}
		if (prise > Jeu.PRISE_MAX) {
			throw new CoupInvalideException(prise, "NbMax");
		}
		NombreAllu = NombreAllu - prise;
	}

}
