with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with SDA_Exceptions; 		use SDA_Exceptions;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;
--! Les Unbounded_String ont une capacitÃ© variable, contrairement au String
--! pour lesquelles une capacitÃ© doit Ãªtre fixÃ©e.
with TH;


procedure th_sujet is

   function Hachage_longueur (Cle : Unbounded_String) return Integer is
   begin
      return Cle'Size;
   end Hachage_longueur;

   package TH_11 is
     new TH (T_Cle => Unbounded_String, T_Donnee => Integer, CAPACITE=>11, Hachage => Hachage_longueur);
   use TH_11;

   T_ex : T_TH;

   -- Afficher T_ex !

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

   procedure Afficher_Th is new Pour_Chaque(Afficher) ;

   --Enregistrement des différentes clés et données
begin
   Enregistrer(T_ex, To_Unbounded_String("un"),1);

   Enregistrer(T_ex,To_Unbounded_String("deux"),2);

   Enregistrer(T_ex,To_Unbounded_String("trois"),3);

   Enregistrer(T_ex,To_Unbounded_String("quatre"),4);

   Enregistrer(T_ex,To_Unbounded_String("cinq"),5);

   Enregistrer(T_ex,To_Unbounded_String("quatre_vingt-dix-neuf"),99);

   Enregistrer(T_ex,To_Unbounded_String("vingt-et-un"),21);

   Afficher_Th(T_ex);

end th_sujet;
