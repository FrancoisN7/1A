close all;

%% Étude de l’impact du bruit, filtrage adapté, 
%% taux d'erreur binaire, efficacité en puissance

Fe = 24000; % Fréquence d'échantillonnage (HsignalDEMOD)
Te = 1/Fe;  % Période d'échantillonnage (secondes)
Rb = 3000; % Débit binaire (bits/seconde)
n = 100000; % Nombre de bits dans le signal
T = n/Rb; % Durée du signal (secondes)

bits = randi([0 1],1,n);

erreurs = zeros(1,8);
erreurs_theo = zeros(1,8);


%% Première chaine à étudier, implanter et comparer à la chaine de référence

M = 2;
Rs = Rb/log2(M);
Ns = floor(Fe/Rs);

mapping = 2*bits-1;
mapping = kron(mapping, [1 zeros(1, Ns-1)]);

h = ones(1,Ns);

%% Sans bruit 

% Signal
hr = zeros(1,Ns);
hr(1:floor(Ns/2)) = 1;
signalMOD = filter(h,1,mapping); % Modulation
signalDEMOD = filter(hr,1,signalMOD); % Démodulation

% Diagramme de l'oeil
% figure;
% plot(reshape(signalDEMOD, Ns, length(signalDEMOD)/Ns));
% title("Diagramme de l'oeil sans bruit");

% Taux d'erreur binaire
n0=Ns;
signalECH = signalDEMOD(n0:Ns:end);
signalECH(signalECH<=0) = -1;
signalECH(signalECH>0) = 1;
signalECH = (signalECH+1)/2;
err = sum(abs(signalECH-bits))/n; % vaut bien 0


%% Avec bruit 

% Formation du bruit

for i=0:8

signalMOD = filter(h,1,mapping);

P = mean(abs(signalMOD).^2);
SNR = 10^(i/10);
sig = sqrt(P*Ns/(2*SNR));
bruit = sig * randn(1, length(signalMOD));

% Ajout du bruit 
signalMOD = signalMOD + bruit;

signalDEMOD = filter(hr,1,signalMOD);

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
err = sum(abs(signalECH-bits))/n;
erreurs(i+1)= err;

% taux d'erreur binaire théorique
err_theo = qfunc(sqrt(SNR));
erreurs_theo(i+1)= err_theo;

end

figure;
semilogy(erreurs_theo);
hold on;
semilogy(erreurs);
title("TEB en fonction du rapport signal à bruit (n = 100 000)");
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
title("TEB chaine 1 et TEB chaine ref (nb points = 100 000)");
legend('TEB chaine 1','TEB chaine ref');





