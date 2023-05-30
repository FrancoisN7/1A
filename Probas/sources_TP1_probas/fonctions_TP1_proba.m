
% TP1 de Probabilites : fonctions a completer et rendre sur Moodle
% Nom :
% Prénom : 
% Groupe : 1SN-

function varargout = fonctions_TP1_proba(varargin)

    switch varargin{1}     
        case 'ecriture_RVB'
            varargout{1} = feval(varargin{1},varargin{2:end});
        case {'vectorisation_par_colonne','decorrelation_colonnes'}
            [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});
        case 'calcul_parametres_correlation'
            [varargout{1},varargout{2},varargout{3}] = feval(varargin{1},varargin{2:end}); 
    end

end

% Fonction ecriture_RVB (exercice_0.m) ------------------------------------
% (Copiez le corps de la fonction ecriture_RVB du fichier du meme nom)
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
end

% Fonction vectorisation_par_colonne (exercice_1.m) -----------------------
function [Vd,Vg] = vectorisation_par_colonne(I)
Vd = I(:,2:end);
Vd = Vd(:);
Vg = I(:,1:end-1);
Vg = Vg(:);
end

% Fonction calcul_parametres_correlation (exercice_1.m) -------------------
function [r,a,b] = calcul_parametres_correlation(Vd,Vg)
    Moy_g=sum(Vg)/size(Vg,1);
    Moy_d=sum(Vd)/size(Vd,1);
    
    Var_g=sum(Vg.^2)/size(Vg,1)-Moy_g^2;
    Var_d=sum(Vd.^2)/size(Vd,1)-Moy_d^2;
 
    E_type_g = Var_g^(1/2);
    E_type_d = Var_g^(1/2);   
    
    Cov=sum(Vg.*Vd)/size(Vg,1)-Moy_g*Moy_d;
    
            
    
    Coeff = Cov/(E_type_g*E_type_d);

    r=Coeff;
    a=Cov/(E_type_d)^2;
    b=Moy_g-Cov/(E_type_d^2)*Moy_d;
end

% Fonction decorrelation_colonnes (exercice_2.m) --------------------------
function [I_decorrelee,I_min] = decorrelation_colonnes(I,I_max)
I_decorrelee=I;
I_decorrelee(:,2:end)=I_decorrelee(:,2:end)-I_decorrelee(:,1:end-1);
I_min=-I_max;
end



