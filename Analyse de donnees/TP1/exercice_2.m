clear;
close all;
taille_ecran = get(0,'ScreenSize');
L = taille_ecran(3);
H = taille_ecran(4);
figure('Name','Separation des canaux RVB','Position',[0,0,0.67*L,0.67*H]);
figure('Name','Nuage de pixels dans le repere RVB','Position',[0.67*L,0,0.33*L,0.45*H]);

% Lecture et affichage d'une image RVB :
I = imread('ishihara-0.png');
figure(1);				% Premiere fenetre d'affichage
subplot(2,2,1);				% La fenetre comporte 2 lignes et 2 colonnes
imagesc(I);
axis off;
axis equal;
title('Image RVB','FontSize',20);

% % Decoupage de l'image en trois canaux et conversion en doubles :
R = double(I(:,:,1));
V = double(I(:,:,2));
B = double(I(:,:,3));

% Affichage du canal R :
colormap gray;				% Pour afficher les images en niveaux de gris
subplot(2,2,2);
imagesc(R);
axis off;
axis equal;
title('Canal R','FontSize',20);

% Affichage du canal V :
subplot(2,2,3);
imagesc(V);
axis off;
axis equal;
title('Canal V','FontSize',20);

% Affichage du canal B :
subplot(2,2,4);
imagesc(B);
axis off;
axis equal;
title('Canal B','FontSize',20);

% Affichage du nuage de pixels dans le repere RVB :
figure(2);				% Deuxieme fenetre d'affichage
plot3(R,V,B,'b.');
axis equal;
xlabel('R');
ylabel('V');
zlabel('B');
rotate3d;

% Matrice des donnees :
X = [R(:) V(:) B(:)];			% Les trois canaux sont vectorises et concatenes

% Matrice de variance/covariance 
taille = size(R);
n = taille(1)*taille(2);
Xc = X - ones(n,1)*mean(X);
sigma = 1/n*(Xc')*Xc;

% Coefficients de correlation lineaire :
coeff_rv = sigma(2,1)/sqrt(sigma(1,1)*sigma(2,2));
coeff_rb = sigma(3,1)/sqrt(sigma(3,3)*sigma(1,1));
coeff_vb = sigma(2,3)/sqrt(sigma(3,3)*sigma(2,2));

% Proportions de contraste :
cr=sigma(1,1)/trace(sigma);

% Calcul des valeurs propres et vecteurs propres de sigma
[W,D] = eig(sigma);
[D,Ind] = sort(diag(D),'descend');
W_triee = [W(:,Ind(1)), W(:,Ind(2)), W(:,Ind(3))];
C = Xc*W_triee;


% Decoupage de l'image en trois canaux et conversion en doubles 
Col1 = Xc*W_triee(:,1);
Col2 = Xc*W_triee(:,2);
Col3 = Xc*W_triee(:,3);
Iprime = zeros(size(I));
Iprime(:,:,1) = reshape(Col1,size(I,1),size(I,2));
Iprime(:,:,2) = reshape(Col2,size(I,1),size(I,2));
Iprime(:,:,3) = reshape(Col3,size(I,1),size(I,2));

% Affichage de Iprime :
figure(1);				% Premiere fenetre d'affichage
subplot(2,2,1);				% La fenetre comporte 2 lignes et 2 colonnes
imagesc(Iprime);
axis off;
axis equal;
title('C','FontSize',20);

% Affichage du canal 1 :
colormap gray;				% Pour afficher les images en niveaux de gris
subplot(2,2,2);
imagesc(Iprime(:,:,1));
axis off;
axis equal;
title('Canal 1','FontSize',20);

% Affichage du canal 2 :
subplot(2,2,3);
imagesc(Iprime(:,:,2));
axis off;
axis equal;
title('Canal 2','FontSize',20);

% Affichage du canal 3 :
subplot(2,2,4);
imagesc(Iprime(:,:,3));
axis off;
axis equal;
title('Canal 3','FontSize',20);

% Matrice de variance/covariance 
taille = size(R);
n = taille(1)*taille(2);
Cc = C - ones(n,1)*mean(C);
sigma = 1/n*(Cc')*Cc;

% Coefficients de correlation lineaire :
coeff_rv = sigma(2,1)/sqrt(sigma(1,1)*sigma(2,2));
coeff_rb = sigma(3,1)/sqrt(sigma(3,3)*sigma(1,1));
coeff_vb = sigma(2,3)/sqrt(sigma(3,3)*sigma(2,2));

% Proportions de contraste :
cr=sigma(1,1)/trace(sigma);


