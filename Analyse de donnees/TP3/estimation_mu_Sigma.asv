% estimation_mu_Sigma
function [mu, Sigma] = estimation_mu_Sigma(X)
mu = [mean(X(:,1)),mean(X(:,2))]';
Xc = X - mu';
Sigma = 1/length(X)*(Xc')*Xc;
end