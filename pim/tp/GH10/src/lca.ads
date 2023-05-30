with Ada.Sequential_IO ;	-- pour l'accès typé aux fichiers


package LCA is

    type T_LCA is private;

    type T_octet is mod 2**8;

    package Byte_file is new Ada.Sequential_IO(T_octet) ;
	use Byte_file ;

	-- Initialiser une Sda.  La Sda est vide.
	procedure Initialiser(Sda: out T_LCA) with
		Post => Est_Vide (Sda);


	-- Est-ce qu'une Sda est vide ?
	function Est_Vide (Sda : T_LCA) return Boolean;


	-- Obtenir le nombre d'éléments d'une Sda.
	function Taille (Sda : in T_LCA) return Integer with
		Post => Taille'Result >= 0
			and (Taille'Result = 0) = Est_Vide (Sda);

    --récupérer la première donnée d'une liste
    function la_donnee(sda : in T_LCA) return T_octet with
            Pre => not Est_Vide(sda);


    --récupérer l'élément suivant dans une liste chainée
    function suivant(sda : in T_LCA) return T_LCA with
            Pre => not Est_Vide(sda);

    --supprimer le premier élément d'une sda
    procedure supprimer_1er(sda : in out T_LCA);


    --ajouter une cellule a la fin d'une sda
    procedure ajouter (sda : in out T_LCA; donnee : in T_octet) with
            post => Taille (Sda) = Taille (Sda)'Old + 1;


    --copier une lca
    procedure copier(sda : in T_LCA; copie : out T_LCA);


    --verifier si deux lca sont egales
    function sont_egales(sda_1 : in T_LCA; sda_2 : in T_LCA) return Boolean;


    --afficher les elements d'une sda binaire
    procedure afficher(sda : in T_LCA);


    --donner la valeur d'un entier en octet
    --cette fonction est dans lca.ads car les lca utilisent des octets
    function int2byte(val : in integer) return T_octet;


    --encoder une sda dans un fichier
    procedure encoder_sda(sda : in T_LCA; compteur : in out Integer; octet : in out T_octet; file_hff : in out Byte_file.file_type);


	-- Supprimer tous les éléments d'une Sda.
	procedure Vider (Sda : in out T_LCA) with
		Post => Est_Vide (Sda);


private
   Type T_Cellule;
   Type T_LCA is access T_Cellule;
   Type T_Cellule is
      record
         donnee : T_octet;
         suivant : T_LCA;
      end record;

end LCA;
