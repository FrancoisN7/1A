with LCA;
generic
   type T_Cle is private;
   type T_Donnee is private;
   CAPACITE : Integer;
   with function Hachage(Cle : in T_Cle) return Integer;

package TH is

   type T_TH is limited private;

   package LCA_2 is
     new LCA(T_Cle => T_Cle, T_Donnee => T_Donnee);
   use LCA_2;

-- Initialiser une Th.  La Th est vide.
	procedure Initialiser(Th: out T_TH) with
		Post => Est_Vide (Th);


	-- Est-ce qu'une Th est vide ?
	function Est_Vide (Th : T_TH) return Boolean;


	-- Obtenir le nombre d'éléments d'une Th.
	function Taille (Th : in T_TH) return Integer with
		Post => Taille'Result >= 0
     and (Taille'Result = 0) = Est_Vide (Th);


   -- Obtenir l'indice du tableau avec la cl�;
   function Indice_tableau(Cle : in T_Cle) return Integer;


	-- Enregistrer une Donnée associée à une Clé dans une Th.
 -- Si la clé est déjà présente dans la Th, sa donnée est changée.
	procedure Enregistrer (Th : in out T_TH ; Cle : in T_Cle ; Donnee : in T_Donnee) with
		Post => Cle_Presente (Th, Cle) and (La_Donnee (Th, Cle) = Donnee)   -- donnée insérée
				and (not (Cle_Presente (Th, Cle)'Old) or Taille (Th) = Taille (Th)'Old)
				and (Cle_Presente (Th, Cle)'Old or Taille (Th) = Taille (Th)'Old + 1);

	-- Supprimer la Donnée associée à une Clé dans une Th.
	-- Exception : Cle_Absente_Exception si Clé n'est pas utilisée dans la Th
	procedure Supprimer (Th : in out T_TH ; Cle : in T_Cle) with
		Post =>  Taille (Th) = Taille (Th)'Old - 1 -- un élément de moins
			and not Cle_Presente (Th, Cle);         -- la clé a été supprimée


	-- Savoir si une Clé est présente dans une Th.
	function Cle_Presente (Th : in T_TH ; Cle : in T_Cle) return Boolean;


	-- Obtenir la donnée associée à une Cle dans la Th.
	-- Exception : Cle_Absente_Exception si Clé n'est pas utilisée dans l'Th
	function La_Donnee (Th : in T_TH ; Cle : in T_Cle) return T_Donnee;


	-- Supprimer tous les éléments d'une Th.
	procedure Vider (Th : in out T_TH) with
		Post => Est_Vide (Th);


	-- Appliquer un traitement (Traiter) pour chaque couple d'une Th.
	generic
		with procedure Traiter (Cle : in T_Cle; Donnee: in T_Donnee);
   procedure Pour_Chaque (Th : in T_TH);



private
   type T_TH is array(1..CAPACITE) of LCA_2.T_LCA;

end TH;
