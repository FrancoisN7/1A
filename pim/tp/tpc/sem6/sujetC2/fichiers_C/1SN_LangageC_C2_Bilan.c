#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <assert.h>
#include <string.h>

// Consignes :
//      - Compléter les instructions pour réaliser les fonctions et 
//        procédures de ce fichier de façon à exécuter les tests avec succès.
// Vous pouvez utiliser les sous-programmes de la bibliothèque string.h pour réaliser 
// les principales opérations (copie, recherche, etc.)

struct string {
    char *str; // tableau de caracteres. Doit se terminer par `\0`.
    int N; // nombre de caractères, `\0` inclus.
};
typedef struct string string;

/**
 * \brief Initialiser à partir d’une chaîne de caractères classique 
 * (tableau de caractères terminé par le caractère nul)
 * \param[out] chaine_dest string initialisé
 * \param[in] chaine_src chaine conventionnelle
 */
void create(string *chaine_dest, char *chaine_src){
    // ****** TODO *******
    //Trouver le nombre de caractères
    int nb_char = 0;
    while(chaine_src[nb_char] != '\0'){
      nb_char++;
    }
    chaine_dest -> N = nb_char + 1;
    chaine_dest -> str = malloc((nb_char+1) * sizeof(char));
    for (int k = 0; k < nb_char; k++){
        chaine_dest -> str[k] = chaine_src[k];
        }
}

/**
 * \brief obtenir le nombre de caractères de la chaîne
 * \param[in] str chaine
 */
int length(string chaine){
    // ****** TODO *******
    int nb_char = chaine.N;
    return nb_char - 1;
}

/**
 * \brief ajouter un nouveau caractère à la fin de la chaîne. Sa longueur est donc augmentée de 1.
 * \param[inout] chaine
 * \param[in] c le caractère à ajouter en fin de chaine.
*/
void add(string *chaine, char c){
    // ****** TODO *******
    char* nouvelle_chaine = realloc(chaine -> str, ((chaine -> N) + 1) * sizeof(char));
    if (nouvelle_chaine) {
    //recopie de l'adresse uniquement si succès 
        chaine -> str = nouvelle_chaine;
        chaine -> N = chaine -> N + 1;
        chaine -> str[(chaine -> N) - 2] = c;
        chaine -> str[(chaine -> N) - 1] = '\0';
    }
}


/**
 * \brief supprimer le caractère à la position i.
 * \param[inout] chaine
 * \param[in] i position du caractere dans la chaine 
 * (attention à la precondition).
*/
void delete(string *chaine, int i){
    // ****** TODO *******
    assert(i < (chaine -> N));
    char* nouvelle_chaine = malloc(((chaine -> N) - 1) * sizeof(char));
    
    if (nouvelle_chaine) {
    //recopie de l'adresse uniquement si succès 
        chaine -> N = chaine -> N - 1;
        for (int k = 0; k < i; k++){
        nouvelle_chaine [k] = chaine -> str[k];
        }
        for (int k = i; k < (chaine -> N) - 1; k++){
        nouvelle_chaine [k] = chaine -> str[k + 1];
        }
        chaine -> str = nouvelle_chaine;
    }
}

/**
 * \brief détruire, elle ne pourra plus être utilisée (sauf à être de nouveau initialisée)
 * \param[in] chaine chaine à détruire
*/
void destroy(string *chaine){
    // ****** TODO *******
    free(chaine -> str);
    chaine -> str = NULL;
}



void test_create(){
    string ch, ch1, ch2;
    create(&ch, "UN");
    assert(ch.N == 3);
    assert(ch.str[0] == 'U');
    create(&ch1, "DEUX");
    assert(ch1.N == 5);
    assert(ch1.str[4] == '\0');
    create(&ch2, "");
    assert(ch2.N == 1);
    assert(ch2.str[0] == '\0');
    
    destroy(&ch);
    destroy(&ch1);
    destroy(&ch2);
}


void test_length(){
    string ch, ch1;
    create(&ch, "UN");
    assert(strlen("UN")==length(ch));
    create(&ch1, "");
    assert(length(ch1)==strlen(""));
    destroy(&ch);
    destroy(&ch1);
}

void test_add(){
    string ch1;
    create(&ch1, "TROI");
    add(&ch1, 'S');
    assert(length(ch1) == 5);
    assert(ch1.str[4] == 'S');
    add(&ch1, '+');
    assert(length(ch1) == 6);
    assert(ch1.str[5] == '+');
    destroy(&ch1);
}


void test_delete(){
    string ch1;
    create(&ch1, "TROIS");
    delete(&ch1, 0); //ROIS
    assert(length(ch1) == 4);
    assert(ch1.str[0] == 'R');
    delete(&ch1, 2); //ROS
    assert(length(ch1) == 3);
    assert(ch1.str[2] == 'S');
    delete(&ch1, 2); //RO
    assert(length(ch1) == 2);
    assert(ch1.str[1] == 'O');
    delete(&ch1, 0); //O
    delete(&ch1, 0); //_
    assert(length(ch1) == 0);
    
    destroy(&ch1);
}


void test_destroy(){
    string ch, ch1;
    create(&ch, "UN");
    destroy(&ch);
    assert(ch.str == NULL);
    
    create(&ch1, "TROI");
    add(&ch1, 'S');
    destroy(&ch1);
    assert(ch1.str == NULL);
}

int main(){
    test_create();
    test_length();
    test_add();
    test_delete();
    test_destroy();
    
    printf("%s", "\n Bravo ! Tous les tests passent.\n");
    return EXIT_SUCCESS;
}
