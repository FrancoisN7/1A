function [Partition] = bayesien(LabelA, C, x)

proba = zeros(size(C,1),1);


for i = 1:size(C,1)

[mu, sigma] = estimation_mu_Sigma(C(i, :)');
proba(i,1) = gaussienne(x, mu, sigma);

end

[maximum, indice_max] = max(proba);  
Partition = LabelA(indice_max);   

end




