[alpha, beta] = MCO_ord(Data,DataMod);
colormap gray;
imagesc(Data);
pause;
I = -(log(ImMod)-beta)/alpha;

imagesc(I);


erreur_RMSE = sqrt(sum((Data(:)-DataMod(:)).^2)/length(Data(:)))


