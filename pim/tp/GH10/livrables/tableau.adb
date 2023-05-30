with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;

package body tableau is


    procedure Initialiser(Tableau : out T_Tableau; donnee : in T_Donnee) is
    begin
        for i in 1..capacite loop
            Tableau(i) := donnee;
        end loop;
    end Initialiser;


    procedure Enregistrer(tableau : in out T_Tableau; pos : in Integer; donnee : in T_Donnee) is
    begin
        Tableau(pos) := donnee;
    end Enregistrer;


    function la_donnee(Tableau : in T_Tableau; pos : in Integer) return T_donnee is
    begin
        return Tableau(pos);
    end la_donnee;


    function longueur(Tableau : in T_Tableau) return integer is
    begin
        return capacite;
    end longueur;


    function position(donnee : in T_Donnee; Tableau : in T_Tableau) return Integer is
        compteur : Integer;
        pos : Integer;
    begin
        compteur := 1;
        pos := -1;
        while compteur<=capacite and pos = -1 loop
            if egal(Tableau(compteur), donnee) then
                pos := compteur;
            end if;
            compteur := compteur + 1;
        end loop;
        return pos;
    end position;


end Tableau;
