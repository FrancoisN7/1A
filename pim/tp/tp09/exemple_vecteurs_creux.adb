with Ada.Text_IO;       use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Vecteurs_Creux;    use Vecteurs_Creux;

-- Exemple d'utilisation des vecteurs creux.
procedure Exemple_Vecteurs_Creux is

	V : T_Vecteur_Creux;
	Epsilon: constant Float := 1.0e-5;
begin
   Put_Line ("D√©but du sc√©nario");
   -- TODO ‡ complÈter
   Initialiser(V);
   pragma Assert(Est_Nul(V));













	Put_Line ("Fin du sc√©nario");
end Exemple_Vecteurs_Creux;
