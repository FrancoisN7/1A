
% TP1 de Statistiques : fonctions a completer et rendre sur Moodle
% Nom :
% Prénom : 
% Groupe : 1SN-

function varargout = fonctions_TP1_stat(varargin)

    [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});

end

% Fonction G_et_R_moyen (exercice_1.m) ----------------------------------
function [G, R_moyen, distances] = G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)
G=[sum(x_donnees_bruitees),sum(y_donnees_bruitees)]/length(x_donnees_bruitees);

ecart_x=x_donnees_bruitees-G(1)*ones(1,length(x_donnees_bruitees));
ecart_y=y_donnees_bruitees-G(2)*ones(1,length(y_donnees_bruitees));
distances=sqrt(ecart_x.*ecart_x+ecart_y.*ecart_y);
R_moyen=sum(distances)/length(x_donnees_bruitees);        
end

% Fonction estimation_C_uniforme (exercice_1.m) ---------------------------
function [C_estime, R_moyen] = estimation_C_uniforme(x_donnees_bruitees,y_donnees_bruitees,n_tests)
[G, R_moyen, ~]=G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees);

C_uni_x=G(1)-R_moyen+2*R_moyen*(rand(n_tests,1));
C_uni_y=G(2)-R_moyen+2*R_moyen*(rand(n_tests,1));
X_c=repmat(C_uni_x,1,length(x_donnees_bruitees));
X_db=(repmat(x_donnees_bruitees,n_tests,1));
Y_c=repmat(C_uni_y,1, length(x_donnees_bruitees));
Y_db=(repmat(y_donnees_bruitees,n_tests,1));

ecart_X=X_c-X_db;
ecart_Y=Y_c-Y_db;

distances_p_c=sqrt(ecart_X.*ecart_X+ecart_Y.*ecart_Y);
arg_sum=distances_p_c-R_moyen;
[~,I_estime]=min(sum(arg_sum.*arg_sum,2));
C_estime=[C_uni_x(I_estime),C_uni_y(I_estime)];
     

end

% Fonction estimation_C_et_R_uniforme (exercice_2.m) ----------------------
function [C_estime, R_estime] = estimation_C_et_R_uniforme(x_donnees_bruitees,y_donnees_bruitees,n_tests)
G=[sum(x_donnees_bruitees),sum(y_donnees_bruitees)]/length(x_donnees_bruitees);

ecart_x=x_donnees_bruitees-G(1)*ones(1,length(x_donnees_bruitees));
ecart_y=y_donnees_bruitees-G(2)*ones(1,length(y_donnees_bruitees));
distances=sqrt(ecart_x.*ecart_x+ecart_y.*ecart_y);    


R_moyen=sum(distances)/length(x_donnees_bruitees);
R_uni=1/2*R_moyen+R_moyen*rand(n_tests,1);

C_uni_x=G(1)-R_moyen+2*R_moyen*(rand(n_tests,1));
C_uni_y=G(2)-R_moyen+2*R_moyen*(rand(n_tests,1));
X_c=repmat(C_uni_x,1,length(x_donnees_bruitees));
X_db=(repmat(x_donnees_bruitees,n_tests,1));
Y_c=repmat(C_uni_y,1, length(x_donnees_bruitees));
Y_db=(repmat(y_donnees_bruitees,n_tests,1));

ecart_X=X_c-X_db;
ecart_Y=Y_c-Y_db;

distances_p_c=sqrt(ecart_X.*ecart_X+ecart_Y.*ecart_Y);
arg_sum=distances_p_c-R_uni;
[~,I_estime]=min(sum(arg_sum.*arg_sum,2));
C_estime=[C_uni_x(I_estime),C_uni_y(I_estime)];
R_estime=R_uni(I_estime);
end

% Fonction occultation_donnees (donnees_occultees.m) ----------------------
function [x_donnees_bruitees, y_donnees_bruitees] = occultation_donnees(x_donnees_bruitees, y_donnees_bruitees, theta_donnees_bruitees)

theta_1=pi*rand(1,1);
theta_2=pi*rand(1,1);
if theta_1>theta_2 
    x_donnees_bruitees=x_donnees_bruitees(0<=theta_donnees_bruitees & theta_donnees_bruitees<=theta_2 | theta_1<=theta_donnees_bruitees | theta_donnees_bruitees<=2*pi);
    y_donnees_bruitees=y_donnees_bruitees(0<=theta_donnees_bruitees & theta_donnees_bruitees<=theta_2 | theta_1<=theta_donnees_bruitees | theta_donnees_bruitees<=2*pi);
else
    x_donnees_bruitees=x_donnees_bruitees(theta_1<=theta_donnees_bruitees & theta_donnees_bruitees<=theta_2);
    y_donnees_bruitees=y_donnees_bruitees(theta_1<=theta_donnees_bruitees & theta_donnees_bruitees<=theta_2);
end
end
% Fonction estimation_C_et_R_normale (exercice_4.m, bonus) ----------------
function [C_estime, R_estime] = ...
         estimation_C_et_R_normale(x_donnees_bruitees,y_donnees_bruitees,n_tests)



end
