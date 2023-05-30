function [mu, sigma] = estimation_mu_Sigma(X)

mu = 1/length(X) * X' * ones(length(X),1);

sigma = 1/length(X) * (X - mu')' *(X - mu');


end