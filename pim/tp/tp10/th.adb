-- with Ada.Text_IO;            use Ada.Text_IO;
with SDA_Exceptions;         use SDA_Exceptions;
with Ada.Unchecked_Deallocation;

package body TH is

   procedure Initialiser(Th: out T_TH) is
   begin
      for i in 1..CAPACITE loop
         LCA_2.Initialiser(Th(i));
      end loop;
   end Initialiser;


   function Est_Vide (Th : in T_TH) return Boolean is
      test : Boolean;
      indice : Integer;
   begin
      indice:=1;
      test:=True;
      While indice<=CAPACITE and test loop
         if LCA_2.Est_vide(Th(indice)) then
            null;
         else
            test:=False;
         end if;
         indice:=indice+1;
      end loop;
      return test;
   end Est_vide;


   function Taille (Th : in T_TH) return Integer is
      Taille_th : Integer;
   begin
      Taille_th:=0;
      for i in 1..CAPACITE loop
         Taille_th:=Taille_th+LCA_2.Taille(Th(i));
      end loop;
      return(Taille_th);
   end Taille;

   function Indice_tableau(Cle : in T_Cle) return Integer is
      v_hachage : Integer;
   begin
      v_hachage := Hachage(Cle);
      return (v_hachage mod CAPACITE) +1;
   end Indice_tableau;



   procedure Enregistrer (Th : in out T_TH ; Cle : in T_Cle ; Donnee : in T_Donnee) is
      indice_tab : Integer;
   begin
      indice_tab := Indice_tableau(Cle);
      LCA_2.Enregistrer(Th(indice_tab), Cle, Donnee);
   end Enregistrer;


   function Cle_Presente (Th : in T_TH ; Cle : in T_Cle) return Boolean is
      indice_tab : Integer;
   begin
      indice_tab:= Indice_tableau(Cle);
      return LCA_2.Cle_Presente(Th(indice_tab), Cle);
   end;


   function La_Donnee (Th : in T_TH ; Cle : in T_Cle) return T_Donnee is
      indice_tab : Integer;
   begin
      indice_tab:=Indice_tableau(Cle);
      return LCA_2.La_Donnee(Th(indice_tab), Cle);
   end La_Donnee;


   procedure Supprimer (Th : in out T_TH ; Cle : in T_Cle) is
      indice_tab : Integer;
   begin
      indice_tab:=Indice_tableau(Cle);
      LCA_2.Supprimer(Th(indice_tab), Cle);
   end Supprimer;


   procedure Vider (Th : in out T_TH) is
   begin
      for i in 1..CAPACITE loop
         LCA_2.Vider(Th(i));
      end loop;
   end Vider;


   procedure Pour_Chaque (Th : in T_TH) is
      procedure Pour_Chaque_2 is new LCA_2.Pour_Chaque(Traiter) ;
   begin
      for i in 1..CAPACITE loop
         Pour_Chaque_2(Th(i));
      end loop;

   end Pour_Chaque;


end TH;
