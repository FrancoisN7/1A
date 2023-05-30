-- with Ada.Text_IO;            use Ada.Text_IO;
with SDA_Exceptions;         use SDA_Exceptions;
with Ada.Unchecked_Deallocation;

package body LCA is

	procedure Free is
		new Ada.Unchecked_Deallocation (Object => T_Cellule, Name => T_LCA);


	procedure Initialiser(Sda: out T_LCA) is
	begin
		Sda:=null;
	end Initialiser;


	function Est_Vide (Sda : in T_LCA) return Boolean is
	begin
		return Sda=null;
	end Est_vide;


   function Taille (Sda : in T_LCA) return Integer is
   begin
      if Sda=null then
         return 0;
      end if;
      return Taille(Sda.all.suivant)+1;
	end Taille;


   procedure Enregistrer (Sda : in out T_LCA ; Cle : in T_Cle ; Donnee : in T_Donnee) is
	begin
      if Sda=null then
         Sda:=new T_Cellule'(Cle,Donnee,null);

      else
         if Sda.all.cle=Cle then
            Sda.all.donnee:=Donnee;
         else
            Enregistrer(Sda.all.suivant,Cle,Donnee);
         end if;
      end if;
	end Enregistrer;


   function Cle_Presente (Sda : in T_LCA ; Cle : in T_Cle) return Boolean is
      begin
      if Sda=null then
         return False ;
      elsif Sda.cle=Cle then
         return True;
      else
         return Cle_Presente(Sda.all.suivant, Cle);
      end if;
	end;


   function La_Donnee (Sda : in T_LCA ; Cle : in T_Cle) return T_Donnee is
   begin
      if Sda=null then
         raise Cle_Absente_Exception;
      elsif Sda.cle=Cle then
         return(Sda.all.donnee);
      else
         return(La_Donnee(Sda.all.suivant,Cle));
      end if;
	end La_Donnee;


   procedure Supprimer (Sda : in out T_LCA ; Cle : in T_Cle) is
      A_supp : T_LCA;
   begin
      if Sda=null then
         raise Cle_Absente_Exception;
      else
         if Sda.cle=Cle then
            A_supp:=Sda;
            Sda:=Sda.all.suivant;
            Free(A_supp);
         else
            Supprimer(Sda.all.suivant,Cle);
         end if;
      end if;
	end Supprimer;


   procedure Vider (Sda : in out T_LCA) is
	begin
      While Sda/=null loop
         Supprimer(Sda,Sda.all.cle);
      end loop;
	end Vider;


   procedure Pour_Chaque (Sda : in T_LCA) is
      Cle : T_cle;
      Donnee : T_Donnee;
   begin
      if not Est_Vide(Sda) then
         begin
            Cle:=Sda.cle;
            Donnee:=Sda.donnee;
            Traiter(Cle,Donnee);
            Pour_chaque(Sda.all.suivant);
         exception
            When others => Pour_chaque(Sda.all.suivant);
         end;
      end if;

   end Pour_Chaque;


end LCA;
