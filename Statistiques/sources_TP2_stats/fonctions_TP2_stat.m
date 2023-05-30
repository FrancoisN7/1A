
% TP2 de Statistiques : fonctions a completer et rendre sur Moodle
% Nom :
% Prénom : 
% Groupe : 1SN-

function varargout = fonctions_TP2_stat(varargin)

    [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});

end

% Fonction centrage_des_donnees (exercice_1.m) ----------------------------
function [x_G, y_G, x_donnees_bruitees_centrees, y_donnees_bruitees_centrees] = ...
                centrage_des_donnees(x_donnees_bruitees,y_donnees_bruitees)

G=[sum(x_donnees_bruitees),sum(y_donnees_bruitees)]/length(x_donnees_bruitees);
x_donnees_bruitees_centrees=x_donnees_bruitees-G(1)*ones(1,length(x_donnees_bruitees));
y_donnees_bruitees_centrees=y_donnees_bruitees-G(2)*ones(1,length(y_donnees_bruitees));
x_G=G(1);
y_G=G(2);
   
     
end

% Fonction estimation_Dyx_MV (exercice_1.m) -------------------------------
function [a_Dyx,b_Dyx] = ...
           estimation_Dyx_MV(x_donnees_bruitees,y_donnees_bruitees,n_tests)
[x_G, y_G, x_donnees_bruitees_centrees, y_donnees_bruitees_centrees] = ...
                centrage_des_donnees(x_donnees_bruitees,y_donnees_bruitees);

Phi_uni=-pi/2+pi*(rand(n_tests,1));

Phi_c=repmat(Phi_uni,1,length(x_donnees_bruitees_centrees));
Phi_db=(repmat(x_donnees_bruitees_centrees,n_tests,1));

%arg_sum=y_donnees_bruitees_centrees-tan(Phi_uni).*x_donnees_bruitees_centrees;
arg_sum=y_donnees_bruitees_centrees-tan(Phi_c).*Phi_db;

[~,I_estime]=min(sum(arg_sum.*arg_sum,2));
a_Dyx=tan(Phi_uni(I_estime));
b_Dyx=y_G-a_Dyx*x_G;

    
end

% Fonction estimation_Dyx_MC (exercice_2.m) -------------------------------
function [a_Dyx,b_Dyx] = ...
                   estimation_Dyx_MC(x_donnees_bruitees,y_donnees_bruitees)
A=ones(length(x_donnees_bruitees),2);
A(:,1)=x_donnees_bruitees';

B=y_donnees_bruitees';
%X=inv(A'*A)*A'*B;
X = eye(2)/(A'*A) * A' * B;
a_Dyx=X(1);
b_Dyx=X(2);

end

% Fonction estimation_Dorth_MV (exercice_3.m) -----------------------------
function [theta_Dorth,rho_Dorth] = ...
         estimation_Dorth_MV(x_donnees_bruitees,y_donnees_bruitees,n_tests)

[x_G, y_G, x_donnees_bruitees_centrees, y_donnees_bruitees_centrees] = ...
                centrage_des_donnees(x_donnees_bruitees,y_donnees_bruitees);

theta_uni=-pi/2+pi*(rand(n_tests,1));
theta_c=repmat(theta_uni,1,length(x_donnees_bruitees_centrees));
X_db=(repmat(x_donnees_bruitees_centrees,n_tests,1));
Y_db=(repmat(y_donnees_bruitees_centrees,n_tests,1));
disp("h");
arg_sum=cos(theta_c).*X_db+sin(theta_c).*Y_db;
disp("h");

[~,I_estime]=min(sum(arg_sum.*arg_sum,2));
disp("h");
theta_Dorth=theta_uni(I_estime);
disp("h");
rho_Dorth = x_G * cos(theta_Dorth) + y_G * sin(theta_Dorth);

end

% Fonction estimation_Dorth_MC (exercice_4.m) -----------------------------
function [theta_Dorth,rho_Dorth] = ...
                 estimation_Dorth_MC(x_donnees_bruitees,y_donnees_bruitees)
[x_G, y_G, x_donnees_bruitees_centrees, y_donnees_bruitees_centrees] = ...
                centrage_des_donnees(x_donnees_bruitees,y_donnees_bruitees);

C=[x_donnees_bruitees_centrees',y_donnees_bruitees_centrees'];
[V,D]=eig(C'*C);
[lambda_min,indice_min]=min(diag(D));
vmin=V(:,indice_min);
theta_Dorth=atan(vmin(2)/vmin(1));
rho_Dorth= x_G * cos(theta_Dorth) + y_G * sin(theta_Dorth);


end
