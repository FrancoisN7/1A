with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with LCA; use LCA;

procedure Test_LCA is


    procedure construire_sda(sda : out T_LCA) is
        bit0 : T_octet;
        bit1: T_octet;
    begin
        Initialiser(sda);
        bit0 := 0;
        bit1 := 1;
        for i in 1..3 loop
            ajouter(sda, bit0);
            ajouter(sda, bit1);
        end loop;
        ajouter(sda, bit1);
    end construire_sda;


    procedure tester_vider is
        sda : T_LCA;
    begin
        construire_sda(sda);
        vider(sda);
        pragma Assert(Est_Vide(sda));
    end tester_vider;


    procedure tester_afficher is
        sda : T_LCA;
    begin
        construire_sda(sda);
        put("sda : ");
        afficher(sda);
        New_Line;
        vider(sda);
    end tester_afficher;


    procedure tester_taille is
        sda : T_LCA;
    begin
        Initialiser(sda);
        pragma Assert(Est_Vide(sda));
        construire_sda(sda);
        pragma Assert(taille(sda)= 7);
        vider(sda);
    end tester_taille;


    procedure tester_supprimer is
        sda :  T_LCA;
    begin
        construire_sda(sda);
        pragma assert (la_donnee(sda) = 0);
        supprimer_1er(sda);
        pragma assert (la_donnee(sda) = 1);
        vider(sda);
    end tester_supprimer;



    procedure tester_ajouter is
        sda : T_LCA;
        bit : T_octet;
    begin
        Initialiser(sda);
        bit := 1;
        ajouter(sda, bit);
        pragma assert(not(Est_Vide(sda)));
        pragma assert (la_donnee(sda) = 1);
        vider(sda);
    end tester_ajouter;


    procedure tester_sont_egales is
        sda_1 : T_LCA;
        sda_2 : T_LCA;
    begin
        construire_sda(sda_1);
        construire_sda(sda_2);
        pragma assert (sont_egales(sda_1, sda_2));
        vider(sda_1);
        vider(sda_2);
    end tester_sont_egales;

    procedure tester_copier is
        sda1 : T_LCA;
        sda2 : T_LCA;
    begin
        construire_sda(sda1);
        copier(sda1, sda2);
        pragma assert (sont_egales(sda1, sda2));
        vider(sda1);
        vider(sda2);
    end tester_copier;


    procedure tester_suivant is
        sda : T_LCA;
        bit : T_octet;
    begin
        construire_sda(sda);
        bit := la_donnee(suivant(sda));
        pragma Assert(bit = 1);
        vider(sda);
    end tester_suivant;



begin
    tester_vider;
    tester_afficher;
    tester_taille;
    tester_supprimer;
    tester_ajouter;
    tester_sont_egales;
    tester_copier;
    tester_suivant;
   Put_Line ("Fin des tests : OK.");
end Test_LCA;
