import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.util.zip.*;
import java.time.LocalDateTime;

/**
 * La classe principale.
 *
 * Tous les traitements demandés sont faits dans la mêthode
 * {@code repondreQuestions}. Il serait plus logique d'écrire des méthodes qui
 * pemettraient d'améliorer la structuration et la réutilisation. Cependant
 * l'objectif est ici la manipulation de l'API des collections, pas la
 * structuration du code en sous-programmes.
 */

public class Main {

	private static void repondreQuestions(Reader in) {
		Iterable<PointDeVente> iterable = PointDeVenteUtils.fromXML(in);

		// Construire un tableau associatif (pdvs) des points de vente avec un
		// accès par identifiant
		Map<Long, PointDeVente> pdvs = new HashMap<>();
		for (PointDeVente pdv : iterable) {
			pdvs.put(pdv.getIdentifiant(), pdv);
		}

		// Nombre de point de vente
		int nbPdv = pdvs.size();
		System.out.println("Le nombre de point de vente est : " + nbPdv);

		// Afficher le nombre de services existants
		Set<String> services = new TreeSet<String>();
		for (PointDeVente pdv : pdvs.values()) {
			services.addAll(pdv.getServices());
		}
		System.out.println("Le nombre de services est : " + services.size());

		// Afficher les services fournis
		for (String service : services) {
			System.out.println("\t - " + service);
		}

		// Afficher la ville correspondant au point de vente d'identifiant
		// 31075001 et le prix du gazole le 25 janvier 2017 à 10h.
		final long id = 31075001;
		final PointDeVente pdvEx = pdvs.get(id);
		System.out.print("Pour l'id " + id + ", ");
		System.out.println("le nom de la ville est : " + pdvEx.getVille() + ".");
		System.out.println("Le prix du Gazole y est : "
				+ pdvEx.getPrix(Carburant.GAZOLE, LocalDateTime.parse("2017-01-25T10:00")) + ".");

		// Afficher le nombre de villes offrant au moins un point de vente
		Set<String> villes = new TreeSet<>();
		for (PointDeVente pdv : pdvs.values()) {
			villes.add(pdv.getVille());
		}

		int nbVilles = villes.size();
		System.out.println("Le nombre de villes offrant au moins un point de vente est : " + nbVilles);

		// Afficher le nombre moyen de points de vente par ville
		System.out.println("Le nombre moyen de pdv par ville est : " + (float) nbPdv / nbVilles );

		// le nombre de villes offrant un certain nombre de carburants

		// Afficher le nombre et les points de vente dont le code postal est 31200

		// Nombre de PDV de la ville de Toulouse qui proposent et du Gazole
		// et du GPLc.

		// Afficher le nom et le nombre de points de vente des villes qui ont au
		// moins 20 points de vente
	}

	private static Reader ouvrir(String nomFichier) throws FileNotFoundException, IOException {
		if (nomFichier.endsWith(".zip")) {
			// On suppose que l'archive est bien formée :
			// elle contient fichier XML !
			ZipFile zfile = new ZipFile(nomFichier);
			ZipEntry premiere = zfile.entries().nextElement();
			return new InputStreamReader(zfile.getInputStream(premiere));
		} else {
			return new FileReader(nomFichier);
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("usage : java Main <fichier.xml ou fichier.zip>");
		} else {
			try (Reader in = ouvrir(args[0])) {
				repondreQuestions(in);
			} catch (FileNotFoundException e) {
				System.out.println("Fichier non trouvé : " + args[0]);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
