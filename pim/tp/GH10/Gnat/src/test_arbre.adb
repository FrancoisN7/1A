with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with arbre; use arbre;


procedure test_arbre is


    procedure construire_arbre(arbre : out T_Arbre) is
        fils_1 : T_Arbre;
        fils_2 : T_Arbre;
        fils_3 : T_Arbre;
        fils_4 : T_Arbre;
    begin
        Initialiser(fils_1);
        Initialiser(fils_2);
        Initialiser(fils_3);

        creer(fils_1, 0, 100);
        creer(fils_2, 1, 101);
        creer(fils_3, 2, 102);

        Fusionner(fils_4, fils_1, fils_2);
        fusionner(arbre, fils_4, fils_3);

        pragma assert (fils_gauche(fils_4) = fils_1);
        pragma assert (fils_droit(fils_4) = fils_2);
    end construire_arbre;


    procedure tester_feuille is
        arbre: T_Arbre;
    begin
        Initialiser(arbre);
        pragma Assert(Est_Vide(arbre));
        creer(arbre, 0, 0);
        pragma Assert(est_feuille(arbre));
        Vider(arbre);
    end tester_feuille;


    procedure tester_cle_donnee is
        arbre : T_Arbre;
    begin
        Initialiser(arbre);
        creer(arbre, 0, 1);
        pragma Assert(la_cle(arbre) = 0);
        pragma Assert(la_donnee(arbre) = 1);
        vider(arbre);
    end tester_cle_donnee;



    arbre : T_Arbre;
    pos : integer;
    code : tab_entier.T_Tableau;
begin
    construire_arbre(arbre);
    tab_entier.Initialiser(code, 0);
    pos := 0;
    afficher_arbre(arbre, pos, code);
    vider(arbre);


    tester_feuille;
    tester_cle_donnee;


    put_line("fin des tests OK");
end test_arbre;
