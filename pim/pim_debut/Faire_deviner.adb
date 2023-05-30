with Ada.Text_Io;          use Ada.Text_Io;
with Ada.Integer_Text_Io;  use Ada.Integer_Text_Io;

-- Faire deviner un nombre entre 1 et 999 à l'utilisateur
procedure Jeu_du_devin is	
	nombre : Integer ;
	proposition : Integer ;
	n_essais : Integer ;

begin
	--Choisir un entier nombre
	Put("J'ai choisi ");
	Get(nombre);
	--while nombre<1 or nombre>999 loop
	--    Put("Le nombre choisi n'est pas dans la bonne plage");
	--    Put("Choisir un nombre entre 1 et 999");
	--    Get(nombre);
	--end loop;

	--Faire trouver le nombre au devin
	n_essais:=0;
	loop
		--Demander au devin un entier proposition
		Put("Proposez un nombre : ");
		Get(proposition);

		--Donner un indice
		if nombre<proposition then
			Put_line("Le nombre proposé est trop grand.");
		elsif nombre>proposition then
			Put_line("Le nombre proposé est trop petit.");
		end if;

		n_essais:=n_essais+1;       -- on incrémente le nombre d'essais
		exit when proposition=nombre;
	end loop;

	--Afficher résultat
	Put("Trouvé!");
	Put("Bravo ! Vous avez trouvé en");
	Put(n_essais+1,1);                -- on rajoute au décompte l'essai réussi
	Put(" essai(s). ");

end Jeu_du_devin;

