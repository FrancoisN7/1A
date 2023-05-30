import java.util.List;

/** Quelques outils (méthodes) sur les listes. */
public class Outils {

	/**
	 * Retourne vrai ssi tous les éléments de la collection respectent le critère.
	 */
	public static <E1> boolean tous(List<E1> elements, Critere<? super E1> critere) {
		boolean verif = true;
		for (E1 e : elements) {
			if (verif && !critere.satisfaitSur(e)) {
				verif = false;
			}
		}
		return verif;
	}

	/** Ajouter dans resultat tous les éléments de la source
	 * qui satisfont le critère aGarder.
	 */
	public static <E1 extends E2, E2> void filtrer(
			List<E1> source,
			Critere<? super E1> aGarder,
			List<E2> resultat)
	{
		for (E1 e : source) {
			if (aGarder.satisfaitSur(e)){
				resultat.add(e);
			}
		}
	}
}

