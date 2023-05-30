close all;

%% Deuxième chaine à étudier, implanter et comparer à la chaine de référence


Fe = 24000; % Fréquence d'échantillonnage (HsignalDEMOD)
Te = 1/Fe;  % Période d'échantillonnage (secondes)
Rb = 3000; % Débit binaire (bits/seconde)
n = 10000; % Nombre de bits dans le signal
T = n/Rb; % Durée du signal (secondes)

bits = randi([0 1],1,n);

erreurs = zeros(1,8);
erreurs_theo = zeros(1,8);


%% Seconde chaine à étudier, implanter et comparer à la chaine de référence

%Signal
mapping_sym = (2*bi2de(reshape(bits, 2, length(bits)/2).' ) - 3).';
mapping = kron(mapping_sym, [1 zeros(1, Ns-1)]);
z = filter(h,1,mapping);
z = filter(h,1,z);

% Diagramme de l'oeil
figure;
plot(reshape(z, Ns, length(z)/Ns));
title("Diagramme de l'oeil (mapping 4-aire)");

% TEB
n0=Ns;

Ts = Ns /Fe;

ech1 = z(n0:Ns:end)/Ns;
ech1(ech1>=0 & ech1<=2) = 1;
ech1(ech1<0 & ech1>=-2) = -1;
ech1(ech1<-2) = -3;
ech1(ech1>2) = 3;
ech1 = reshape(de2bi((ech1 + 3)/2).' , 1, length(bits));
err = sum(abs(ech1-bits))/n;








%% avec bruit

% Formation du bruit

for i=0:8

z = filter(h,1,mapping);

%figure;
%plot(z, length(z));

M=4;
P = mean(abs(z).^2);
SNR = 10^(i/10);
sig = sqrt(P*Ns/(2*log2(M)*SNR));
bruit = sig * randn(1, length(z));

% Ajout du bruit 
z = z + bruit;

z = filter(h,1,z);

% Choix de n0
n0=Ns;

% Échantillonnage
ech = z(n0:Ns:end)/8;

% Diagramme de l'oeil
%figure;
%plot(reshape(z, Ns, length(z)/Ns));
%title("Diagramme de l'oeil avec canal de propagation et perturbations avec i=",i);

% Détecteur à seuil

ech(ech>=0 & ech<=2) = 1;
ech(ech<0 & ech>=-2) = -1;
ech(ech<-2) = -3;
ech(ech>2) = 3;

% taux d'erreur symbole
err_sym = sum(ech ~= mapping_sym) / length(ech);
erreurs_sym(i+1)= err_sym;

% taux d'erreur symbole théorique
err_theo_sym = (3/2)*qfunc(sqrt((4/5)*SNR));
erreurs_theo_sym(i+1)= err_theo_sym;

% Démapping
ech = reshape(de2bi((ech + 3)/2).' , 1, length(bits));

% taux d'erreur binaire
err = sum(abs(ech-bits))/n;
erreurs(i+1)= err;

% taux d'erreur binaire théorique
err_theo = (3/4)*qfunc(sqrt((4/5)*SNR));
erreurs_theo(i+1)= err_theo;

end

figure;
semilogy(erreurs_theo);
hold on;
semilogy(erreurs);
title("TEB en fonction du rapport signal à bruit (mapping 4-aire)");
legend('Erreur théorique','Erreur');


figure;
semilogy(erreurs_theo_sym);
hold on;
semilogy(erreurs_sym);
title("TES en fonction du rapport signal à bruit (mapping 4-aire)");
legend('Erreur théorique','Erreur');










%% Étude de l’impact du bruit, filtrage adapté, 
%% taux d'erreur binaire, efficacité en puissance

Fe = 24000; % Fréquence d'échantillonnage (HsignalDEMOD)
Te = 1/Fe;  % Période d'échantillonnage (secondes)
Rb = 3000; % Débit binaire (bits/seconde)
n = 100000; % Nombre de bits dans le signal
T = n/Rb; % Durée du signal (secondes)

bits = randi([0 1],1,n);

erreurs1 = zeros(1,8);
erreurs_theo1 = zeros(1,8);

%% Chaine de référence

M = 2;
Rs = Rb/log2(M);
Ns = floor(Fe/Rs);

mapping = 2*bits-1;
mapping = kron(mapping, [1 zeros(1, Ns-1)]);

h = ones(1,Ns);

% Formation du bruit

for i=0:8

signalMOD = filter(h,1,mapping); % Modulation

P = mean(abs(signalMOD).^2);
SNR = 10^(i/10);
sig = sqrt(P*Ns/(2*SNR));
bruit = sig * randn(1, length(signalMOD));

% Ajout du bruit 
signalMOD = signalMOD + bruit;

signalDEMOD = filter(h,1,signalMOD); % demodulation

% Choix de n0
n0=Ns;

% Échantillonnage
signalECH = signalDEMOD(n0:Ns:end);

% Diagramme de l'oeil
% figure;
% plot(reshape(signalDEMOD, Ns, length(signalDEMOD)/Ns));
% title("Diagramme de l'oeil avec canal de propagation et perturbations avec i=",i);

% Détecteur à seuil
signalECH(signalECH<=0) = -1;
signalECH(signalECH>0) = 1;

% Démapping 
signalECH = (signalECH+1)/2;

% taux d'erreur binaire
err = sum(abs(signalECH-bits))/n
erreurs1(i+1)= err;

% taux d'erreur binaire théorique
err_theo = qfunc(sqrt(2*SNR));
erreurs_theo1(i+1)= err_theo;

end

figure;
semilogy(erreurs);
hold on;
semilogy(erreurs1);
title("TEB chaine 2 et TEB chaine ref (nb points = 100 000)");
legend('TEB chaine 2','TEB chaine ref');
