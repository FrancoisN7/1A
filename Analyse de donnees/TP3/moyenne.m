% Définition de la fonction moyenne
function [moyenne_pix] = moyenne(im)
im = single(im);
norme = im(:,:,1) + im(:,:,2) + im(:,:,3);
r = im(:,:,1)./max(1,norme);
v = im(:,:,2)./max(1,norme);
moyenne_pix(1) = mean(r(:));
moyenne_pix(2) = mean(v(:));
end