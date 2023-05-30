with Ada.Text_IO;            use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with Ada.Unchecked_Deallocation;


package body LCA is

	procedure Free is
		new Ada.Unchecked_Deallocation (Object => T_Cellule, Name => T_LCA);


	procedure Initialiser(Sda: out T_LCA) is
	begin
        sda := null;
    end Initialiser;


	function Est_Vide (Sda : in T_LCA) return Boolean is
	begin
		return sda = null;
	end;


	function Taille (Sda : in T_LCA) return Integer is
	begin
        if Est_Vide(sda) then
            return 0;
        else
            return taille(sda.all.suivant) + 1;
        end if;
	end Taille;


    function la_donnee(sda : in T_LCA) return T_octet is
    begin
        return sda.all.donnee;
    end la_donnee;


    function suivant(sda : in T_LCA) return T_LCA is
    begin
        return sda.all.suivant;
    end suivant;


    procedure supprimer_1er(sda : in out T_LCA) is
        a_detruire : T_LCA;
    begin
        if sda /= null then
            a_detruire := sda;
            sda := sda.all.suivant;
            free(a_detruire);
        end if;
    end supprimer_1er;


    procedure ajouter (sda : in out T_LCA; donnee : in T_octet) is
    begin
        if sda = null then
            sda := new T_Cellule;
            sda.all.donnee := donnee;
            sda.all.suivant := null;
        else
            ajouter (sda.all.suivant, donnee);
        end if;
    end ajouter;


    procedure Vider (Sda : in out T_LCA) is
	begin
        if sda /= null then
            vider(sda.all.suivant);
            free(sda);
        end if;
    end Vider;


    procedure copier(sda : in T_LCA; copie : out T_LCA) is
    begin
        if sda /= null then
            ajouter(copie, sda.all.donnee);
            copier(sda.all.suivant, copie);
        end if;
    end copier;


    function sont_egales(sda_1 : in T_LCA; sda_2 : in T_LCA) return Boolean is
    begin
        if sda_2 = null and sda_1 = null then
            return True;
        else
            if sda_1 = null and sda_2 /= null then
                return False;
            elsif sda_1 /= null and sda_2 = null then
                return False;
            elsif sda_1.all.donnee /= sda_2.all.donnee then
                return False;
            end if;
            return sont_egales(sda_1.all.suivant, sda_2.all.suivant);
        end if;
    end sont_egales;

    procedure afficher(sda : in T_LCA) is
    begin
        if sda /= null then
            if sda.all.donnee = 1 then
                put(1,1);
            else
                put(0,1);
            end if;
            afficher(sda.all.suivant);
        end if;
    end afficher;


    function int2byte(val : in integer) return T_octet is
        octet : T_octet;
        bit : integer;
        courant : integer;
    begin
        octet := 0;
        courant := val;
        if val = -1 then
            return -1;
        end if;
        for i in 1..8 loop
            bit := courant/128;
            if bit = 1 then
                octet := (octet * 2) or 1;
            else
                octet := (octet * 2) or 0;
            end if;
            courant := courant * 2 mod 2**8;
        end loop;
        return octet;
    end int2byte;

    procedure encoder_sda(sda : in T_LCA; compteur : in out Integer; octet : in out T_octet; file_hff : in out Byte_file.file_type) is
        bit : T_octet;
    begin
        if compteur = 8 then--encoder l'octet quand il est complet
            compteur := 0;
            Write(file_hff, octet);
            octet := 0;
        end if;
        if sda /= null then--ajouter la donnee de la lca dans l'octet
            bit := sda.all.donnee;
            octet := (octet * 2) or bit;
            compteur := compteur + 1;
            encoder_sda(sda.all.suivant, compteur, octet, file_hff);
        end if;
    end encoder_sda;

end LCA;
