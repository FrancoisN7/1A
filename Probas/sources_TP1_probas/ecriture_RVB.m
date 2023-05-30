function image_RVB = ecriture_RVB(image_originale)
[nb_lignes,nb_colonnes] = size(image_originale);
image_RVB = zeros(nb_lignes/2,nb_colonnes/2,3);

Rouge=image_originale(1:2:end,2:2:end);
Bleu=image_originale(2:2:end,1:2:end);
Vert1=image_originale(1:2:end,1:2:end);
Vert2=image_originale(2:2:end,2:2:end);
Vert_moy=(Vert1+Vert2)/2;

image_RVB(:,:,1) = Rouge;
image_RVB(:,:,2) = Vert_moy;
image_RVB(:,:,3) = Bleu;
