function [alpha, beta] = MCO_ord(niv_gris_init,niv_gris_transf) 
    A = [-niv_gris_init(:) ones(length(niv_gris_init(:)),1)];

    B = log(niv_gris_transf(:));
    size(A)
    size(B)
    K = pinv(A)*B;
    alpha = K(1);
    beta = K(2);
end