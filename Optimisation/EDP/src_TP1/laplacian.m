function L = laplacian(nu,dx1,dx2,N1,N2)
%
%  Cette fonction construit la matrice de l'opérateur Laplacien 2D anisotrope
%
%  Inputs
%  ------
%
%  nu : nu=[nu1;nu2], coefficients de diffusivité dans les dierctions x1 et x2. 
%
%  dx1 : pas d'espace dans la direction x1.
%
%  dx2 : pas d'espace dans la direction x2.
%
%  N1 : nombre de points de grille dans la direction x1.
%
%  N2 : nombre de points de grilles dans la direction x2.
%
%  Outputs:
%  -------
%
%  L      : Matrice de l'opérateur Laplacien (dimension N1N2 x N1N2)
%
% 

% Initialisation
nu1=nu(1);
nu2=nu(2);
e1=ones(N1*N2,1);
e2=-2*e1;
e1(N2:N2:end)=0;
S1=spdiags([e1 e2 e1],[-1 0 1],N1*N2,N1*N2);

S2=spdiags([e1 e2 e1],[-N2 0 N2],N1*N2,N1*N2);

L=-(nu1/(dx1*dx1))*S1-(nu2/(dx2*dx2))*S2;
end    
