compresser [-b / --bavard] fichier_texte
compresse fichier_texte avec la méthode de Huffman et engendre fichier_texte.hff
decompresser [-b / --bavard] fichier.hff
décompresse fichier avec la méthode de Huffman et engendre fichier.hff.dec

Fichier confog.txt : contient 2 paramètres :
- offset du caractère de fin de fichier : 
* 0 : l'indice du 1er octet effectif est  0
* 1 : l'indice du 1er octet effectif est  1

- decalage :
* 0 : le code de l'arbre et les codes des octets sont accolés (pas de bis perdus)
* 1 : le code de l'arbre et les codes des octets sont ont éventuellements séparés
par quelques bits à 0. Le dernier octet du code de l'arbre est complété avec des 0
et le code du premier octet du fichier démarre dans un octet viierge.

Fichier fic_huffman_equilibre.txt : tous les caractères ont la même fréquence, 
ce qui produit un arbre équilibré

Fichiers fic_huffman_profond16.txt (et 24) produisent un arbre profond à une seule branche
descendante.
