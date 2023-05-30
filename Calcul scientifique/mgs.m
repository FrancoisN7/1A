%--------------------------------------------------------------------------
% ENSEEIHT - 1SN - Calcul scientifique
% TP1 - Orthogonalisation de Gram-Schmidt
% mgs.m
%--------------------------------------------------------------------------

function Q = mgs(A)

    % Recuperation du nombre de colonnes de A
    [n, m] = size(A);
    
    % Initialisation de la matrice Q avec la matrice A
    Q = A;
    
    %------------------------------------------------
    % A remplir
    % Algorithme de Gram-Schmidt classique
    Q(:,1) = Q(:,1)/norm(Q(:,1));
    for i=2:m
        y = Q(:,i);
        for j=1:i-1
        y = y - dot(y, Q(:,j))*Q(:,j);
        end
        Q(:,i) = y/norm(y);
    end
    %------------------------------------------------

end