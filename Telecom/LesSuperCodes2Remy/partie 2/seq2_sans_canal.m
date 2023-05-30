close all;

    %% Étude des interférences entre symbole et du critère de Nyquist

Fe = 24000; % Fréquence d'échantillonnage (HsignalDEMOD)
Te = 1/Fe;  % Période d'échantillonnage (secondes)
Rb = 3000; % Débit binaire (bits/seconde)
n = 100; % Nombre de bits dans le signal
T = n/Rb; % Durée du signal (secondes)

bits = randi([0 1],1,n);

%% Étude sans canal de propagation 

M = 2;
Rs = Rb/log2(M);
Ns = floor(Fe/Rs);

mapping = 2*bits-1;
mapping = kron(mapping, [1 zeros(1, Ns-1)]);

h = ones(1,Ns);

signalMOD = filter(h,1,mapping); % Modulation

signalDEMOD = filter(h,1,signalMOD); % Démodulation

% Tracé du signal 
figure;
plot([1:length(signalDEMOD)],signalDEMOD);
title("Tracé temporel du signal en sortie du filtre de réception");

% Diagramme de l'oeil
figure;
plot(reshape(signalDEMOD, Ns, length(signalDEMOD)/Ns));
title("Diagramme de l'oeil sans canal de propagation");

% Réponse impultionnelle globale de la chaine de transmission
g = conv(h,h);
figure;
plot(1:length(g), g);
title("Réponse impultionnelle globale de la chaine de transmission");

%Choix de n0
n0 = Ns;

% Échantillonnage
signalECH = signalDEMOD(n0:Ns:end);

% Détecteur à seuil
signalECH(signalECH<=0) = -1;
signalECH(signalECH>0) = 1;

% Démapping 
signalECH = (signalECH+1)/2;

% taux d'erreur binaire
TEB = sum(abs(signalECH-bits))/n

