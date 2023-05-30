% Auteur : J. Gergaud
% décembre 2017
% -----------------------------
% 



function Jac= diff_finies_centree(fun, x, option)
%
% Cette fonction calcule les différences finies centrées sur un schéma
% Paramètres en entrées
% fun : fonction dont on cherche à calculer la matrice jacobienne
%       fonction de IR^n à valeurs dans IR^m
% x   : point où l'on veut calculer la matrice jacobienne
% option : précision du calcul de fun (ndigits)
%
% Paramètre en sortie
% Jac : Matrice jacobienne approximé par les différences finies
%        real(m,n)
% ------------------------------------
n=size(x,1);
m=size(fun,1);
Jac=zeros(n,m);
w=max(eps,10^(-option));
for i=(1:m)
    h=w*(1/3)*max(abs(x(i)),1)*sgn(x(i));
    e=zeros(n,1);
    e(i)=1;
    Jac(:,i)=(fun(x+h*e)-fun(x-h*e))/(2*h);
end
end


function s = sgn(x)
% fonction signe qui renvoie 1 si x = 0
if x==0
  s = 1;
else 
  s = sign(x);
end
end





