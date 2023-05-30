#include <stdlib.h> 
#include <stdio.h>
#include <assert.h>
#include <stdbool.h>

#define nombre_monnaies 5

// Definition du type monnaie
typedef struct t_monnaie{ 
        int valeur; 
        char devise; } t_monnaie;

/**
 * \brief Initialiser une monnaie 
 * \param[]
 * \pre valeur>0
 * // TODO
 */ 
void initialiser(t_monnaie* monnaie_init, int valeur_init, char devise_init){
    (*monnaie_init).valeur=valeur_init;
    (*monnaie_init).devise=devise_init;
}


/**
 * \brief Ajouter une monnaie m2 à une monnaie m1 
 * \param[]
 * // TODO
 */ 
bool ajouter(t_monnaie monnaie1,t_monnaie monnaie2){
    //Récupération des valeurs et devises
    bool ajout_effectue = false;
    int valeur1 = monnaie1.valeur; 
    char devise1 = monnaie1.devise; 
    int valeur2 = monnaie2.valeur; 
    char devise2 = monnaie2.devise;  
    
    //Test si la monnaie est la même
    if (devise1==devise2) {
        valeur2+=valeur1;
        ajout_effectue=true;
    } else {
    }
    return(ajout_effectue);
}


/**
 * \brief Tester Initialiser 
 * \param[]
 * // TODO
 */ 
void tester_initialiser(){
    t_monnaie monnaie_ex1;
    initialiser(&monnaie_ex1, 1, 'c');
    t_monnaie monnaie_ex2;
    initialiser(&monnaie_ex2, 2, 'e');
    
    assert(monnaie_ex1.valeur==1);
    assert(monnaie_ex1.devise=='c');
    assert(monnaie_ex2.valeur==2);
    assert(monnaie_ex2.devise=='e');

/**
 * \brief Tester Ajouter 
 * \param[]
 * // TODO
 */ 
void tester_ajouter(){
    t_monnaie monnaie_ex1;
    initialiser(&monnaie_ex1, 1, 'c');
    t_monnaie monnaie_ex2;
    initialiser(&monnaie_ex2, 2, 'e');
    t_monnaie monnaie_ex3;
    initialiser(&monnaie_ex3, 3, 'e');
    
    assert(ajouter(monnaie_ex1,monnaie_ex2)==false);
    assert(ajouter(monnaie_ex2,monnaie_ex3)==true); 
    
    ajouter(monnaie_ex2,monnaie_ex3);
    assert(monnaie_ex3.valeur==5);
    ajouter(monnaie_ex2,monnaie_ex3);
    assert(monnaie_ex3.valeur==7);
}



int main(void){
    // Un tableau de 5 monnaies
    typedef t_monnaie t_porte_monnaie[nombre_monnaies];
    t_porte_monnaie porte_monnaie; 
    
    //Initialiser les monnaies
    int valeur;
    char devise;
    t_monnaie monnaie;
    char devise_demandee;
    
    for (int i=0; i<5; i++) {  
    scanf("%d,%c",&valeur,&devise);
    initialiser(&monnaie, valeur, devise);
    porte_monnaie[i]=monnaie;
    }
    
    // Afficher la somme des toutes les monnaies qui sont dans une devise entrée par l'utilisateur.
    t_monnaie somme_monnaie;
    initialiser(&somme_monnaie, 0, devise_demandee);
    scanf("%c",&devise_demandee);
    
    for (int i=0; i<5; i++) {  
        if (porte_monnaie[i].devise==devise_demandee) {
        ajouter(porte_monnaie[i],somme_monnaie);
        }
    }
    printf("La somme demandée est %d,%c",somme_monnaie.valeur,devise_demandee);
    return EXIT_SUCCESS;
}
