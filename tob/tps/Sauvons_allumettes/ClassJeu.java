package allumettes;

public class ClassJeu implements Jeu {
	int NombreAllu = 13;
	
	@Override
	public int getNombreAllumettes() {
		return NombreAllu;
	}

	@Override
	public void retirer(int prise) throws CoupInvalideException {
		if (prise<1) {
	        throw new CoupInvalideException(prise,"(< 1)");
	    }
		
		if (prise>Jeu.PRISE_MAX) {
			throw new CoupInvalideException(prise,"NbMax");	
		}
		
		if (prise>NombreAllu) {
			throw new CoupInvalideException(prise, "NbAl");	
		}
	    NombreAllu = NombreAllu - prise;
	}
	

}
