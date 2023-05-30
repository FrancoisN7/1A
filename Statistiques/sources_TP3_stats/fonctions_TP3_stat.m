
% TP3 de Statistiques : fonctions a completer et rendre sur Moodle
% Nom :
% Prenom : 
% Groupe : 1SN-

function varargout = fonctions_TP3_stat(varargin)

    [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});

end

% Fonction estimation_F (exercice_1.m) ------------------------------------
function [rho_F,theta_F,ecart_moyen] = estimation_F(rho,theta)
A=[cos(theta),sin(theta)];
B=rho;
X=inv(A'*A)*A'*B;
x_f=X(1);
y_f=X(2);
theta_F=atan2(y_f,x_f);
rho_F=x_f/cos(theta_F);
m=length(rho);

    % A modifier lors de l'utilisation de l'algorithme RANSAC (exercice 2)
    ecart_moyen = 1/m*sum(abs(rho-rho_F*cos(theta-theta_F)));

end

% Fonction RANSAC_2 (exercice_2.m) ----------------------------------------
function [rho_F_estime,theta_F_estime] = RANSAC_2(rho,theta,parametres)
S1=parametres(1);
S2=parametres(2);
k_max=parametres(3);
ecart_moyen_min=Inf;
n=length(rho);
for i=(1:k_max)
    K=randperm(n,2);
    rho_tirage=[rho(K(1)),rho(K(2))]';
    theta_tirage=[theta(K(1)),theta(K(2))]';
    [rho_I,theta_I,ecart_moyen] = estimation_F(rho_tirage,theta_tirage);
    rho_c=rho(abs(rho-rho_I*cos(theta-theta_I))<=S1);
    theta_c=theta(abs(rho-rho_I*cos(theta-theta_I))<=S1);
    m=length(rho_c);
    if m/n>S2 
        [rho_F,theta_F,ecart_moyen] = estimation_F(rho_c,theta_c);
        if ecart_moyen<ecart_moyen_min
            rho_F_estime=rho_F;
            theta_F_estime=theta_F;
            ecart_moyen_min=ecart_moyen;
        end
    end

end
end

% Fonction G_et_R_moyen (exercice_3.m, bonus, fonction du TP1) ------------
function [G, R_moyen, distances] = ...
         G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)
G=[sum(x_donnees_bruitees),sum(y_donnees_bruitees)]/length(x_donnees_bruitees);

ecart_x=x_donnees_bruitees-G(1)*ones(1,length(x_donnees_bruitees));
ecart_y=y_donnees_bruitees-G(2)*ones(1,length(y_donnees_bruitees));
distances=sqrt(ecart_x.*ecart_x+ecart_y.*ecart_y);
R_moyen=sum(distances)/length(x_donnees_bruitees);   


end

% Fonction estimation_C_et_R (exercice_3.m, bonus, fonction du TP1) -------
function [C_estime,R_estime,critere] = ...
         estimation_C_et_R(x_donnees_bruitees,y_donnees_bruitees,n_tests,C_tests,R_tests)


R_uni=R_tests;

C_uni_x=C_tests(1,:);
C_uni_y=C_tests(2,:);
X_c=repmat(C_uni_x,1,length(x_donnees_bruitees));
X_db=(repmat(x_donnees_bruitees,n_tests,1));
Y_c=repmat(C_uni_y,1, length(x_donnees_bruitees));
Y_db=(repmat(y_donnees_bruitees,n_tests,1));

ecart_X=X_c-X_db;
ecart_Y=Y_c-Y_db;

distances_p_c=sqrt(ecart_X.*ecart_X+ecart_Y.*ecart_Y);
arg_sum=distances_p_c-R_uni;
[critere,I_estime]=min(sum(arg_sum.*arg_sum,2));
C_estime=[C_uni_x(I_estime),C_uni_y(I_estime)];
R_estime=R_uni(I_estime);
    % Attention : par rapport au TP1, le tirage des C_tests et R_tests est 
    %             considere comme etant deje effectue 
    %             (il doit etre fait au debut de la fonction RANSAC_3)

end

% Fonction RANSAC_3 (exercice_3, bonus) -----------------------------------
function [C_estime,R_estime] = ...
         RANSAC_3(x_donnees_bruitees,y_donnees_bruitees,parametres)

G=[sum(x_donnees_bruitees),sum(y_donnees_bruitees)]/length(x_donnees_bruitees);
ecart_x=x_donnees_bruitees-G(1)*ones(1,length(x_donnees_bruitees));
ecart_y=y_donnees_bruitees-G(2)*ones(1,length(y_donnees_bruitees));
distances=sqrt(ecart_x.*ecart_x+ecart_y.*ecart_y);    

R_moyen=sum(distances)/length(x_donnees_bruitees);
R_tests=1/2*R_moyen+R_moyen*rand(n_tests,1);
C_tests_x=G(1)-R_moyen+2*R_moyen*(rand(n_tests,1));
C_tests_y=G(2)-R_moyen+2*R_moyen*(rand(n_tests,1));
C_tests=[C_tests_x;C_tests_y];

    % Attention : il faut faire les tirages de C_tests et R_tests ici

S1=parametres(1);
S2=parametres(2);
k_max=parametres(3);
ecart_moyen_min=Inf;
n=length(rho);
for i=(1:k_max)
    K=randperm(n,3);
    x_tirage=[x_donnees_bruitees(K(1)),x_donnees_bruitees(K(2)),x_donnees_bruitees(K(3))]';
    y_tirage=[y_donnees_bruitees(K(1)),y_donnees_bruitees(K(2)),y_donnees_bruitees(K(3))]';
    [R_tirage,C_tirage] = estimation_cercle_3points(x_tirage,y_tirage);


    %LA FIN EST A ADAPTER POUR QUE CELA FONCTIONNE
    [rho_I,theta_I,ecart_moyen] = estimation_F(rho_tirage,theta_tirage);
    rho_c=rho(abs(rho-rho_I*cos(theta-theta_I))<=S1);
    theta_c=theta(abs(rho-rho_I*cos(theta-theta_I))<=S1);
    m=length(rho_c);
    if m/n>S2 
        [rho_F,theta_F,ecart_moyen] = estimation_F(rho_c,theta_c);
        if ecart_moyen<ecart_moyen_min
            rho_F_estime=rho_F;
            theta_F_estime=theta_F;
            ecart_moyen_min=ecart_moyen;
        end
    end

end

end
