clear;
close all;

load eigenfaces_part3;

% Tirage aléatoire d'une image de test :
personne = randi(nb_personnes)
posture = randi(nb_postures)
% si on veut tester/mettre au point, on fixe l'individu
personne = 10
% posture = 6

ficF = strcat('./Data/', liste_personnes{personne}, liste_postures{posture}, '-300x400.gif')
img = imread(ficF);
image_test = double(transpose(img(:)));
 


% Nombre q de composantes principales à prendre en compte 
q = 5;

% dans un second temps, q peut être calculé afin d'atteindre le pourcentage
% d'information avec q valeurs propres (contraste)
% Pourcentage d'information 
% per = 0.75;

%% Calcul de c(x)  (classifieur)

for i = 1:size(X_masque,1)  
    for j = 1:q
        C(i,j) = W_masque(:,j)'*X_masque(i,:)';
    end
end

% Une ligne de C est une image de la base d'aprentissage dans la base des
% eigenfaces masquee



%Pour une image masquee a reconstituer, il faut :
% la mettre dans la base des eigenfaces
% identifier l'image la plus proche dans la base d'apprentissage
% avec le label, on recupere la zone non masquee de l'image que l'on colle
% par dessus

%%

DataA = C; %       les données d'apprentissage (connues)
LabelA = [];
for i = 1:nb_personnes_base
    for j = 1:nb_postures_base
        classe = strcat(liste_personnes_base(i), '_',num2str(j));
        LabelA = [LabelA classe];
    end
end
LabelA; %     : les labels des données d'apprentissage

DataT = image_test;    % les données de test (on veut trouver leur label)
Nt_test = 36; %    : nombre de données tests qu'on veut labelliser

for i = 1:size(DataT,1)  
    for j = 1:q
        DataTbaseEigen(i,j) = W_masque(:,j)'*DataT(i,:)'
    end
end
DataTbaseEigen


K = 1;          % le K de l'algorithme des k-plus-proches-voisins
ListeClass = liste_personnes; %les classes possibles (== les labels possibles)





%
% Résultat :
% Partition : pour les Nt_test données de test, le label calculé
%
%--------------------------------------------------------------------------
% C'est un 1ppv ici
[Partition] = kppv(DataA, LabelA, DataTbaseEigen, Nt_test, K, ListeClass)
newStr = split(Partition,'_')
%liste_personnes_base = {'f01', 'f10', 'm01', 'm08'}
%       personnes          1     10     17     24 

% individu pseudo-résultat pour l'affichage (A CHANGER)
    switch true
        case (strcmpi(newStr(1),liste_personnes_base(1))==1)
            personne_proche = 1;
        case (strcmpi(newStr(1),liste_personnes_base(2))==1)
            personne_proche = 2;
        case (strcmpi(newStr(1),liste_personnes_base(3))==1)
            personne_proche = 3;
        case (strcmpi(newStr(1),liste_personnes_base(4))==1)
            personne_proche = 4;
        otherwise
            personne_proche = 5;
    end

posture_proche = str2double(newStr(2));

figure('Name','Image tiree aleatoirement','Position',[0.2*L,0.2*H,0.8*L,0.5*H]);

subplot(1, 2, 1);
% Affichage de l'image de test :
colormap gray;
imagesc(img);
title({['Individu de test : posture ' num2str(posture) ' de ', liste_personnes{personne}]}, 'FontSize', 20);
axis image;

ficF = strcat('./Data/', liste_personnes_base{personne_proche}, liste_postures{posture_proche}, '-300x400.gif')
img1 = imread(ficF);
        
subplot(1, 2, 2);
imagesc(img1);
title({['Individu la plus proche : posture ' num2str(posture_proche) ' de ', liste_personnes_base{personne_proche}]}, 'FontSize', 20);
axis image;

%%%%%%% Bayésien

% Partition2 : pour les Nt_test données de test, le label calculé
%
%--------------------------------------------------------------------------
[Partition2] = bayesien(LabelA,C, DataTbaseEigen)
newStr2 = split(Partition2,'_')
%liste_personnes_base = {'f01', 'f10', 'm01', 'm08'}
%       personnes          1     10     17     24 

% individu pseudo-résultat pour l'affichage (A CHANGER)
    switch true
        case (strcmpi(newStr2(1),liste_personnes_base(1))==1)
            personne_proche2 = 1;
        case (strcmpi(newStr2(1),liste_personnes_base(2))==1)
            personne_proche2 = 2;
        case (strcmpi(newStr2(1),liste_personnes_base(3))==1)
            personne_proche2 = 3;
        case (strcmpi(newStr2(1),liste_personnes_base(4))==1)
            personne_proche2 = 4;
        otherwise
            personne_proche2 = 5;
    end

posture_proche2 = str2double(newStr2(2));

figure('Name','Image tiree aleatoirement','Position',[0.2*L,0.2*H,0.8*L,0.5*H]);

subplot(1, 2, 1);
% Affichage de l'image de test :
colormap gray;
imagesc(img);
title({['Individu de test : posture ' num2str(posture) ' de ', liste_personnes{personne}]}, 'FontSize', 20);
axis image;

ficF = strcat('./Data/', liste_personnes_base{personne_proche2}, liste_postures{posture_proche2}, '-300x400.gif')
img = imread(ficF);
        
subplot(1, 2, 2);
imagesc(img);
title({['Individu la plus proche : posture ' num2str(posture_proche2) ' de ', liste_personnes_base{personne_proche2}]}, 'FontSize', 20);
axis image;
