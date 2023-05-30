/**
 *  \author Xavier Crégut <nom@n7.fr>
 *  \file file.c
 *
 *  Objectif :
 *	Implantation des opérations de la file
*/

#include <malloc.h>
#include <assert.h>

#include "file.h"


void initialiser(File *f)
{
    // TODO
    f -> queue = NULL;
    f -> tete = NULL;

    assert(est_vide(*f));
}


void detruire(File *f)
{
    while (f -> tete != NULL) {
        //On libËre la tÍte tout en accÈdant ‡ la nouvelle tÍte
        //(le pointeur d'aprËs)
    	Cellule *tampon = f -> tete;
    	f -> tete = (f -> tete) -> suivante;
    	free(tampon);
    }
    f = NULL;
}


char tete(File f)
{
    assert(! est_vide(f));

    // TODO
    return f.tete -> valeur;
}


bool est_vide(File f)
{
    // TODO
    return (f.queue == NULL) && (f.tete == NULL);
}

/**
 * Obtenir une nouvelle cellule allouée dynamiquement
 * initialisée avec la valeur et la cellule suivante précisé en paramètre.
 */
static Cellule * cellule(char valeur, Cellule *suivante)
{
    // TODO
    Cellule *NouvCell = malloc(sizeof(Cellule));
    NouvCell -> suivante = suivante;
    NouvCell -> valeur = valeur;
    return NouvCell;
}


void inserer(File *f, char valeur)
{
    assert(f != NULL);

    // TODO
    if (f -> tete == NULL) {
        //Dans ce cas la tÍte vaut la queue qui vaut la 
        //nouvelle cellule
    	f -> queue = cellule(valeur, NULL);
    	f -> tete = f -> queue;
    } else {
        //Dans ce cas, l'ÈlÈment suivant ‡ la queue devient la
        //nouvelle queue
    	(f -> queue) -> suivante = cellule(valeur, NULL);
    	f -> queue = (f -> queue) -> suivante;
    }
}

void extraire(File *f, char *v)
{
    assert(f != NULL);
    assert(! est_vide(*f));

    // TODO
    //On RÈcupËre la valeur
    *v = (f -> tete) -> valeur;
    //MÍme technique que dans dÈtruire pour supprimer la tÍte
    Cellule *tampon = (f -> tete) -> suivante;
    free(f -> tete);
    f -> tete = tampon;
}


int longueur(File f)
{
    // TODO
    if (f.tete == NULL) {
    	return 0;
    } else {
        //On rÈduit la file d'un ÈlÈment (mÈthode rÈcursive)
    	File FileReduite;
    	FileReduite.tete = f.tete -> suivante;
    	return 1 + longueur(FileReduite);
    }
}
