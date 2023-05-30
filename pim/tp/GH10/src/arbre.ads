with tableau;
with LCA; use LCA;

package Arbre is

    type T_Arbre is private;

    package tab_entier is new tableau (T_Donnee => Integer, capacite => 258);
    use tab_entier;


    package tab_lca is new tableau (T_Donnee => T_LCA, capacite => 257);
    use tab_lca;


    procedure Initialiser(arbre : out T_Arbre) with
            post => Est_Vide(arbre);


    --est-ce que l'arbre est vide ?
    function Est_Vide (arbre : in T_Arbre) return Boolean;

    --savoir si un arbre est une feuille ou un noeud
    function est_feuille(arbre : in T_Arbre) return Boolean;


    --initialise un arbre avec cle et donnee
    procedure creer (arbre : in out T_Arbre; cle: in Integer; donnee : in Integer);


    --obtenir la clé d'un arbre
    function la_cle (arbre : in T_Arbre) return Integer with
            pre => not Est_Vide(arbre);


    --obtenir la donnee d'un arbre
    function la_donnee (arbre : in T_Arbre) return Integer with
            pre => not Est_Vide(arbre);


    function fils_gauche (arbre : in T_Arbre) return T_Arbre with
            pre => not Est_Vide(arbre);

    function fils_droit (arbre : in T_Arbre) return T_Arbre with
            pre => not est_vide(arbre);

    --fusionner deux arbres en un nouvel arbre
    procedure Fusionner (arbre : out T_Arbre; arbre_g : in T_arbre; arbre_d : in T_arbre);


    procedure afficher_carac(val : in Integer);

    --afficher un arbre en commençant a la colonne  pos
    procedure afficher_arbre (arbre : in T_Arbre; pos : in out Integer; code : in out tab_entier.T_Tableau);


    procedure creer_tab_huffman(arbre : in T_arbre; tab_huffman : in out tab_lca.T_Tableau; code : in out T_LCA);


    --reconstituer un arbre à partir de sa structure
    procedure reconstituer_arbre(arbre : in out T_Arbre; structure_arbre : in out T_LCA; tab_infixe : in tab_entier.T_Tableau; indice : in out Integer);

   -- Supprimer tous les éléments d'un arbre.
   procedure Vider (arbre : in out T_Arbre) with
     Post => Est_Vide (arbre);


private
   Type T_Noeud;
   Type T_Arbre is access T_Noeud;
   Type T_Noeud is
      record
         cle : Integer;
         donnee : integer;
         fils_g : T_Arbre;
         fils_d : T_Arbre;
      end record;

end Arbre;
