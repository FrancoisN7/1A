clear all;
close all
format long;

%%%%%%%%%%%%
% PARAMÈTRES
%%%%%%%%%%%%

% taille de la matrice symétrique
n = 200;

% type de la matrice (voir matgen_csad)
% imat == 1 valeurs propres D(i) = i
% imat == 2 valeurs propres D(i) = random(1/cond, 1) avec leur logarithmes
%                                  uniformément répartie, cond = 1e10
% imat == 3 valeurs propres D(i) = cond**(-(i-1)/(n-1)) avec cond = 1e5
% imat == 4 valeurs propres D(i) = 1 - ((i-1)/(n-1))*(1 - 1/cond) avec cond = 1e2
imat = 1;

% on génère la matrice (1) ou on lit dans un fichier (0)
% si vous avez déjà généré la matrice d'une certaine taille et d'un type donné
% vous pouvez mettre cette valeur à 0
genere = 1;

% méthode de calcul
v = 10; % eig / norm(y,2)
% [W, V, flag] = eigen_2022(imat, n, v, [], [], 1, [], [], genere);

% nombre maximum de couples propres calculés
m = 100;
percentage = 0.4;

% on génère la matrice (1) ou on lit dans un fichier (0)
genere = 0;

% méthode de calcul
v = 11; % power

% tolérance
eps = 1e-8;
% nombre d'itérations max pour atteindre la convergence
maxit = 10000;

% [W, V, flag] = eigen_2022(imat, n, v, m, eps, maxit, percentage, [], genere);
[W0, V0, flag0] = eigen_2022(imat, n, 0, m, eps, maxit, percentage, 5, genere);
[W1, V1, flag1] = eigen_2022(imat, n, 1, m, eps, maxit, percentage, 5, genere);
[W2, V2, flag2] = eigen_2022(imat, n, 2, m, eps, maxit, percentage, 5, genere);
[W3, V3, flag3] = eigen_2022(imat, n, 3, m, eps, maxit, percentage, 5, genere);

subplot(2,2,1);
histogram(W0(1:46),20);
title('iter\_v0');
hold on;
subplot(2,2,2);
histogram(W1,20);
title('iter\_v1');
hold on;
subplot(2,2,3);
histogram(W2,20);
title('iter\_v2');
hold on;
subplot(2,2,4);
histogram(W3,20);
title('iter\_v3');
hold on;

