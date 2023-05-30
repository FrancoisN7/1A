function Beta_chapeau = MCO(x, y) 
    A = [x.*x x.*y y.*y x y ones(length(x),1); 1 0 1 0 0 0];
    % alpha + gamma = 1

    B = [zeros(length(x),1); 1];
    Beta_chapeau = pinv(A)*B;
end