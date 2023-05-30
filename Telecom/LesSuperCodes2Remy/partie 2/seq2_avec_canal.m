close all;

    %% Étude des interférences entre symbole et du critère de Nyquist

Fe = 24000; % Fréquence d'échantillonnage (HsignalDEMOD)
Te = 1/Fe;  % Période d'échantillonnage (secondes)
Rb = 3000; % Débit binaire (bits/seconde)
n = 10000; % Nombre de bits dans le signal
T = n/Rb; % Durée du signal (secondes)

bits = randi([0 1],1,n);

%% Étude avec canal de propagation sans bruit

M = 2;
Rs = Rb/log2(M);
Ns = floor(Fe/Rs);

mapping = 2*bits-1;
mapping = kron(mapping, [1 zeros(1, Ns-1)]);

h = ones(1,Ns);

% Réponse impulsionnelle globale de la chaine de transmission

fc=1000; % fc=BW dans le sujet 
ordre = 30;
hc = (2*fc/Fe)*sinc(2*(fc/Fe)*[-(ordre-1)/2 : (ordre-1)/2]);

g = conv(h,h);
gc = conv(g,hc);
figure;
plot(1:length(gc),gc);
title("Réponse impultionnelle globale de la chaine de transmission");

% Signal en sortie du filtre de réception

mapping = [mapping zeros(1, 4)];
signalDEMOD = filter(h,1,mapping);
signalDEMOD = signalDEMOD(5: end);

signalDEMOD = [signalDEMOD zeros(1, 15)];
signalDEMOD = filter(hc,1,signalDEMOD);
signalDEMOD = signalDEMOD(16: end);

signalDEMOD = [signalDEMOD zeros(1, 4)];
signalDEMOD = filter(hc,1,signalDEMOD);
signalDEMOD = signalDEMOD(5: end);

figure; plot(signalDEMOD);
title("Signal en sortie du filtre de réception");

% Diagramme de l'oeil
figure;
plot(reshape(signalDEMOD, Ns, length(signalDEMOD)/Ns));
title("Diagramme de l'oeil avec canal de propagation");

%Réponse en fréquence 
H_Hr = fft(g,1024);
Hc = fft(hc,1024);
figure;
plot(1: length(H_Hr),abs(H_Hr));
hold on;
plot(1: length(Hc),abs(Hc));
legend('|H(f)Hr(f)|','|Hc(f)|');

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



