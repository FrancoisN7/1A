with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with ada.unchecked_deallocation;

package body arbre is



    procedure Free is
	new Ada.Unchecked_Deallocation (Object => T_noeud, Name => T_arbre);


    procedure Initialiser(arbre : out T_Arbre) is
    begin
        arbre := null;
    end Initialiser;


    function Est_Vide (arbre : in T_arbre) return Boolean is
    begin
	return arbre=null;
    end Est_vide;

    function est_feuille (arbre : in T_Arbre) return boolean is
    begin
        return est_vide(arbre.all.fils_g) and Est_Vide(arbre.all.fils_d);
    end est_feuille;


    procedure creer (arbre : in out T_Arbre; cle : in Integer; donnee : in Integer) is
    begin
        if est_vide(arbre) then
            arbre := new T_Noeud;
        end if;
        arbre.all.cle := cle;
        arbre.all.donnee := donnee;
        arbre.all.fils_g := null;
        arbre.all.fils_d := null;
    end creer;


    function la_cle (arbre : in T_Arbre) return Integer is
    begin
        return arbre.all.cle;
    end la_cle;


    function la_donnee (arbre : in T_Arbre) return Integer is
    begin
        return arbre.all.donnee;
    end la_donnee;


    function fils_gauche (arbre : in T_Arbre) return T_Arbre is
    begin
        return arbre.all.fils_g;
    end fils_gauche;


    function fils_droit (arbre : in T_Arbre) return T_Arbre is
    begin
        return arbre.all.fils_d;
    end fils_droit;



    procedure Fusionner (arbre : out T_Arbre; arbre_g : in T_arbre; arbre_d : in T_arbre) is
    begin
        arbre := new T_Noeud;
        arbre.all.cle := arbre_d.all.cle + arbre_g.all.cle;
        arbre.all.fils_g := arbre_g;
        arbre.all.fils_d := arbre_d;
    end Fusionner;



    procedure vider (arbre : in out T_Arbre) is
    begin
        if arbre = null then
            null;
        elsif arbre.all.fils_g=null and arbre.all.fils_d=null then
            free(arbre);
        else
            vider(arbre.all.fils_g);
            vider(arbre.all.fils_d);
            free(arbre);
        end if;
    end vider;

    procedure afficher_carac(val : in Integer) is
    begin
        if val = 10 then
            put("\n");
        elsif val = -1 or val = 257 then
            put("\$");
        else
            put(Character'val(val));
        end if;
    end afficher_carac;


    procedure afficher_arbre (arbre : in T_Arbre; pos : in out Integer; code : in out tab_entier.T_Tableau) is

        procedure afficher_branches(pos : in integer) is
            i : integer;
            taille : Integer;
        begin
            i:= 1;
            taille := 1;
            while taille < pos loop

                taille := taille + 1;
                if la_donnee(code, i) = 0 then
                    put(" |    ");
                else
                    put("      ");
                end if;
                i := i+1;
            end loop;
            put(" \--");
            put(la_donnee(code, i), 1);--ecrire 0 ou 1
            put("--");
        end afficher_branches;
    begin
        put("(");
        put(la_cle(arbre), 1);
        put(") ");
        if Est_Vide (fils_droit(arbre)) then--afficher le caractere
            afficher_carac (la_donnee(arbre));
            new_line;
        else
            new_line;
            pos := pos + 1;
            Enregistrer(code, pos, 0);
            afficher_branches(pos);--afficher les branches à gauche du caractere
            afficher_arbre(fils_gauche(arbre), pos, code);

            Enregistrer(code, pos, 1);
            afficher_branches(pos);
            afficher_arbre(fils_droit(arbre), pos, code);
            pos := pos - 1;
        end if;
    end afficher_arbre;


    procedure creer_tab_huffman(arbre : in T_arbre; tab_huffman : in out tab_lca.T_Tableau; code : in out T_LCA) is
        indice : integer;
        code0 : T_LCA;
        code1 : T_LCA;
    begin
        if est_feuille(arbre) then--ajouter le code du caractere à tab_huffman
            indice := la_donnee(arbre);
            if indice = -1 then
                indice := 257;
            end if;
            Enregistrer(tab_huffman, indice, code);
        else
            copier(code, code0);
            ajouter(code0, 0);
            creer_tab_huffman(fils_gauche(arbre), tab_huffman, code0);
            copier(code, code1);
            ajouter(code1, 1);
            creer_tab_huffman(fils_droit(arbre), tab_huffman, code1);
            vider(code);
        end if;
    end creer_tab_huffman;

    procedure reconstituer_arbre(arbre : in out T_Arbre; structure_arbre : in out T_LCA; tab_infixe : in tab_entier.T_Tableau; indice : in out Integer) is
        donnee : Integer;
    begin
        if Est_Vide(structure_arbre) then
            null;
        elsif la_donnee(structure_arbre) = 1 then--creer une feuille
            indice := indice + 1;
            donnee := tab_entier.la_donnee(tab_infixe, indice);
            creer(arbre, 0, donnee);
            supprimer_1er(structure_arbre);
        else--creer un noeud
            creer(arbre,0,0);
            supprimer_1er(structure_arbre);
            reconstituer_arbre(arbre.all.fils_g, structure_arbre, tab_infixe, indice);
            reconstituer_arbre(arbre.all.fils_d, structure_arbre, tab_infixe, indice);
        end if;
    end reconstituer_arbre;


end arbre;
