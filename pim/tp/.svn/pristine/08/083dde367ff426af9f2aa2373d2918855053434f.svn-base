with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with SDA_Exceptions; 		use SDA_Exceptions;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;

with LCA;

-- Insérer dans une LCA les premières données 1 et 2
procedure lca_sujet is
   package LCA_S is
		new LCA (T_Cle => Unbounded_String, T_Donnee => Integer);
   use LCA_S;
   Sda : LCA_S.T_LCA;

   function Avec_Guillemets (S: Unbounded_String) return String is
	begin
		return '"' & To_String (S) & '"';
	end;


   procedure Afficher (S : in Unbounded_String; N: in Integer) is
	begin
		Put (Avec_Guillemets (S));
		Put (" : ");
		Put (N, 1);
		New_Line;
   end Afficher;

   procedure Afficher_Sda is new Pour_Chaque(Afficher) ;



begin
   Initialiser(Sda);
   Enregistrer(Sda,To_Unbounded_String("un"),1);
   Enregistrer(Sda,To_Unbounded_String("deux"),2);
   Afficher_Sda(Sda);
   Vider(Sda);
end lca_sujet;
