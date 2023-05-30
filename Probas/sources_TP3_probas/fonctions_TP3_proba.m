
% TP3 de Probabilites : fonctions a completer et rendre sur Moodle
% Nom :
% Prénom : 
% Groupe : 1SN-

function varargout = fonctions_TP3_proba(varargin)

    switch varargin{1}
        
        case 'matrice_inertie'
            
            [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});
            
        case {'ensemble_E_recursif','calcul_proba'}
            
            [varargout{1},varargout{2},varargout{3}] = feval(varargin{1},varargin{2:end});
    
    end
end

% Fonction ensemble_E_recursif (exercie_1.m) ------------------------------
function [E,contour,G_somme] = ...
    ensemble_E_recursif(E,contour,G_somme,i,j,voisins,G_x,G_y,card_max,cos_alpha)

if size(E,1)<card_max
for k=(1:8)

    i_voisin=i+voisins(k,1);
    j_voisin=j+voisins(k,2);
    G_x_voisin=G_x(i_voisin,j_voisin);
    G_y_voisin=G_y(i_voisin,j_voisin);
    G_ij=[G_x_voisin G_y_voisin];
    G_ij=G_ij/norm(G_ij);
    G_somme=G_somme/norm(G_somme);
    contour(i,j)=0;

if G_ij*G_somme'>=cos_alpha && contour(i_voisin,j_voisin)==1
    E =[E; i_voisin j_voisin];
    G_somme=G_somme+G_ij;
    [E,contour,G_somme]=ensemble_E_recursif(E,contour,G_somme,i_voisin,j_voisin,voisins,G_x,G_y,card_max,cos_alpha);


end

end
end


    
end

% Fonction matrice_inertie (exercice_2.m) ---------------------------------
function [M_inertie,C] = matrice_inertie(E,G_norme_E)
PI=G_norme_E;
sum_PI=sum(PI);
x=E(:,2);
y=E(:,1);
x_moy=(1/sum_PI)*sum(PI.*x);
y_moy=(1/sum_PI)*sum(PI.*y);
delta_x=x-x_moy;
delta_y=y-y_moy;
A=(1/sum_PI)*sum(PI'*(delta_x.*delta_x));
B=(1/sum_PI)*sum(PI'*(delta_x.*delta_y));
D=(1/sum_PI)*sum(PI'*(delta_y.*delta_y));

C=[x_moy y_moy];
M_inertie=[A B ; B D];
end

% Fonction calcul_proba (exercice_2.m) ------------------------------------
function [x_min,x_max,probabilite] = calcul_proba(E_nouveau_repere,p)
n=size(E_nouveau_repere(:,1));
x_min=min(E_nouveau_repere(:,1));
x_max=max(E_nouveau_repere(:,1));
y_min=min(E_nouveau_repere(:,2));
y_max=max(E_nouveau_repere(:,2));
N_arrondi=(y_max-y_min)*(x_max-x_min);
N=round(N_arrondi,0);
probabilite=binocdf(N,n,p,"upper");


    
end
