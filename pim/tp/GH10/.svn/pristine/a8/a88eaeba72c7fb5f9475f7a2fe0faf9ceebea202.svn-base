generic
   type T_Donnee is private;
   capacite : Integer;

package Tableau is

    type T_Tableau is private;

    --initialiser un tableau en mettant toutes ses case a la valeur : donnee
    procedure Initialiser(Tableau : out T_Tableau; donnee : in T_Donnee);


    --enregistrer un element dans une case d'un tableau
    procedure Enregistrer(tableau : in out T_Tableau; pos : in Integer; donnee : in T_Donnee);


    --trouver la donnee d'un tableau dans une case
    function la_donnee(Tableau : in T_Tableau; pos : in Integer) return T_donnee;

    --trouver la taille d'un tableau
    function longueur(Tableau : in T_Tableau) return integer;

    --position d'un élément dans un tableau
    --retourne -1 si l'élément n'est pas dans le tableau
    generic
        with function egal(case1 : in T_Donnee; case2 : in T_Donnee) return Boolean;
    function position(donnee : in T_Donnee; Tableau : in T_Tableau) return Integer;


private
   type T_Tableau is array(1..capacite) of T_Donnee;
end Tableau;
