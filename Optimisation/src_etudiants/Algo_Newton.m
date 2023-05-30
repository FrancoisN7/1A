function [beta_k, norm_grad_f_beta, f_beta_k, norm_delta, nb_it, exitflag] ...
          = Algo_Newton(Hess_f_C14, beta0, option)
%************************************************************
% Fichier  ~gergaud/ENS/Optim1a/TP-optim-20-21/Newton_ref.m *
% Novembre 2020                                             *
% Université de Toulouse, INP-ENSEEIHT                      *
%************************************************************
%
% Newton r�sout par l'algorithme de Newton les problemes aux moindres carres
% Min 0.5||r(beta)||^2
% beta \in R^p
%
% Parametres en entrees
% --------------------
% Hess_f_C14 : fonction qui code la hessiennne de f
%              Hess_f_C14 : R^p --> matrice (p,p)
%              (la fonction retourne aussi le residu et la jacobienne)
% beta0  : point de départ
%          real(p)
% option(1) : Tol_abs, tolérance absolue
%             real
% option(2) : Tol_rel, tolérance relative
%             real
% option(3) : nitimax, nombre d'itérations maximum
%             integer
%
% Parametres en sortie
% --------------------
% beta      : beta
%             real(p)
% norm_gradf_beta : ||gradient f(beta)||
%                   real
% f_beta    : f(beta)
%             real
% res       : r(beta)
%             real(n)
% norm_delta : ||delta||
%              real
% nbit       : nombre d'itérations
%              integer
% exitflag   : indicateur de sortie
%              integer entre 1 et 4
% exitflag = 1 : ||gradient f(beta)|| < max(Tol_rel||gradient f(beta0)||,Tol_abs)
% exitflag = 2 : |f(beta^{k+1})-f(beta^k)| < max(Tol_rel|f(beta^k)|,Tol_abs)
% exitflag = 3 : ||delta)|| < max(Tol_rel delta^k),Tol_abs)
% exitflag = 4 : nombre maximum d'itérations atteint
%      
% ---------------------------------------------------------------------------------

% TO DO %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    beta_k = beta0;
    norm_grad_f_beta = 0;
    f_beta_k = 1/2*norm(residu(beta_k))^2;
    norm_delta = 0;
    nb_it = 0;
    exitflag = 0;
    norm_grad_f_beta0=norm(J_residu(beta0)'*residu(beta_k));
    % exitflag = 1 : norm_grad_f_beta < max(option(2)*norm_grad_f_beta0,option(1))
    % exitflag = 2 : norm(f_beta_k1-f_beta_k) < max(option(2)*abs(f_beta_k)),f_beta_k)
    % exitflag = 3 : norm_grad_f_beta < max(option(2)*norm(beta_k),option(1))
    % exitflag = 4 : nb_it=option(3)
    while exitflag == 0
        J_k=J_residu(beta_k);
        Hessienne=Hess_f_C14(beta_k);
        beta_k1=beta_k - Hessienne\J_k'*residu(beta_k);
        delta=beta_k1-beta_k;
        norm_delta=norm(delta);
        f_beta_mem=f_beta_k;
        f_beta_k=1/2*norm(residu(beta_k1))^2;
        
        norm_grad_f_beta=norm(J_k'*residu(beta_k));
        
        
        beta_k=beta_k1;
        nb_it=nb_it+1;
        
        if nb_it==option(3) 
            exitflag = 4;
        end
        if norm_grad_f_beta < max(option(2)*norm_grad_f_beta0,option(1))
            exitflag = 1;
        end
        if norm(f_beta_k-f_beta_mem) < max(option(2)*norm(f_beta_mem),option(1))
            exitflag = 2;
        end
        if norm_grad_f_beta < max(option(2)*norm(beta_k),option(1))
            exitflag = 3;
        end
    end
        
end

