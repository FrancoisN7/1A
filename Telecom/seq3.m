clear all;
close all;
%Paramètres fixés par l'énoncé
Fe = 24000;
Rb = 3000;
Tb = 1 / Rb;
Te = 1 / Fe;

%Paramètres modifiables
N = 1000;
Ns = Fe / Rb;
Ns1 = Ns;
Nfft = 1024;
Ts = Ns * Te;

%Génération de BITS
signal_bin = round(rand(N,1));
plot(signal_bin,'*-');
xlabel("Bits");
ylabel("Amplitude du signal binaire");
legend('Génération des bits');
figure()

%Mapping
signal_map = (signal_bin-1/2) * 2;
plot(signal_map, '*');
xlabel("Symboles");
ylabel("Amplitude du signal mappé");
legend('Signal mappé');
figure()

%% Chaine de référence
% Définition des filtres
h1 = zeros(1,Ns1);
h1(1,1:Ns) = 1;

%Génération des impulsions
T = [0 : Te : 8 * N * Te - Te];
pic = zeros(1,Ns);
pic(1) = 1;
s_imp = kron(signal_map, pic');
plot(T, s_imp, '*-');
xlabel("Temps");
ylabel("Amplitude des impulsions");
legend('Impulsions');
figure()

%Filtrage
s_filtre = filter(h1,1,s_imp);
plot(T, s_filtre);
xlabel("Temps");
ylabel("Amplitude");
legend('résultat du filtrage')
figure();

%Réponse impulsionnelle globale g
g = conv(h1,h1);
g = Ts * g / max(g);
plot(g);
xlabel("Temps");
ylabel("Amplitude");
legend('Réponse impulsionnelle globale');
figure();

%Tracé de g convolué avec les impulsions
s_glob = filter(h1,1,s_filtre);
s_glob = s_glob / max(s_glob);
plot(s_glob,'*');
xlabel("Symboles");
ylabel("Amplitude");
legend('tracé de g convolué avec les impulsions')
figure();

%Diagramme de l'oeil sans bruit
plot(reshape(s_glob,Ns,length(s_glob)/Ns));
legend('Diagramme de l oeil sans bruit');
figure();

% Génération du bruit
TEB_exp_ref = zeros(1,8);
TEB_theo_ref = zeros(1,8);
RSB = [1 : 1 :8];   

for i = 1 : length(RSB)
Px = mean(abs(signal_map).^2);
SNR = 10 ^ (RSB(i) / 10);
sigma = Px*Ns/(2*SNR);
bruit = sigma * randn(length(s_filtre),1);
signal_bruite = bruit + s_filtre;
sig_bruit_filtre = filter(h1,1,signal_bruite);

%Diagramme de l'oeil avec bruit
%plot(reshape(sig_bruit_filtre,Ns,length(sig_bruit_filtre)/Ns));
%legend('Diagramme de l oeil avec bruit');
%figure();

%Paramètres modifiables pour le d émapping (optimisation après diagramme de l'oeil)
n0 = Ns;

%échantillonnage pour le démapping
s_sortie_ech = sig_bruit_filtre(n0 : Ns1 : end);
decision = sign(s_sortie_ech);
%Taux d'erreur binaire
signal_diff = abs(signal_map - decision) / 2;
TEB_exp_ref(i) = sum(signal_diff) / length(signal_diff);
TEB_theo_ref(i) = 1-cdf('Normal',sqrt(2*SNR),0,1);
end

semilogy([1:1:8], TEB_exp_ref, 'g', [1:1:8], TEB_theo_ref, 'r');
xlabel("SNR");
ylabel("Amplitude");
legend('TEB exp ref','TEB theo ref');
figure();




%% Première chaine à étudier
%% Implantation de la chaine sans bruit
% Définition des filtres
h1 = zeros(1,Ns1);
h2 = h1;
h1(1,1:Ns) = 1;
h2(1,1:Ns/2) = 1;

%Génération des impulsions
T = [0 : Te : 8 * N * Te - Te];
pic = zeros(1,Ns);
pic(1) = 1;
s_imp = kron(signal_map, pic');
plot(T, s_imp, '*-');
xlabel("Temps");
ylabel("Amplitude des impulsions");
legend('Impulsions');
figure();

%Filtrage
s_filtre = filter(h1,1,s_imp);
plot(T, s_filtre);
xlabel("Temps");
ylabel("Amplitude");
legend('résultat du filtrage')
figure();

%Réponse impulsionnelle globale g
g = conv(h1,h2);
g = Ts * g / max(g);
plot(g);
xlabel("Temps");
ylabel("Amplitude");
legend('Réponse impulsionnelle globale');
figure();

%Tracé de g convolué avec les impulsions
s_glob = filter(h2,1,s_filtre);
s_glob = s_glob / max(s_glob);
plot(s_glob,'*');
xlabel("Symboles");
ylabel("Amplitude");
legend('tracé de g convolué avec les impulsions')
figure();

%Diagramme de l'oeil sans bruit
plot(reshape(s_glob,Ns,length(s_glob)/Ns));
legend('Diagramme de l oeil sans bruit');
figure();

%Paramètres modifiables pour le démapping (optimisation après diagramme de l'oeil)
n0 = Ns;
m = 5;

%échantillonnage pour le démapping
s_sortie_ech = s_glob(n0 : Ns1 : end);

%Taux d'erreur binaire sans bruit
signal_diff = abs(signal_map - s_sortie_ech) / 2;
taux_erreur_bin = sum(signal_diff) / N;


%% Implantation de la chaine sans bruit
% Génération du bruit
TEB_exp = zeros(1,8);
TEB_theo = zeros(1,8);
RSB = [1 : 1 : 8];

for i = 1 : length(RSB)
Px = mean(abs(signal_map).^2);
SNR = 10 ^ (RSB(i) / 10);
sigma = Px*Ns/(2*SNR);
bruit = sigma * randn(length(s_filtre),1);
signal_bruite = bruit + s_filtre;
sig_bruit_filtre = filter(h2,1,signal_bruite);

%Diagramme de l'oeil avec bruit  
%plot(reshape(sig_bruit_filtre,Ns,length(sig_bruit_filtre)/Ns));
%legend('Diagramme de l oeil avec bruit');
%figure();

%Paramètres modifiables pour le démapping (optimisation après diagramme de l'oeil)
n0 = Ns;
m = 5;

%échantillonnage pour le démapping
s_sortie_ech = sig_bruit_filtre(n0 : Ns1 : end);
decision = sign(s_sortie_ech);

%Taux d'erreur binaire
signal_diff = abs(signal_map - decision) / 2;
TEB_exp(i) = sum(signal_diff) / N;
TEB_theo(i) = 1-cdf('Normal',sqrt(i),0,sigma);
end

semilogy([1:1:8], TEB_exp, 'g', [1:1:8], TEB_theo, 'r');
xlabel("SNR");
ylabel("Amplitude");
legend('TEB exp','TEB theo');




%% Deuxième chaine à étudier
%Génération de BITS
signal_bin = round(rand(N,3));
plot(signal_bin,'*-');
xlabel("Bits");
ylabel("Amplitude du signal binaire");
legend('Génération des bits');
figure()

%Mapping
signal_map = (signal_bin - 3/2) * 2;
plot(signal_map, '*');
xlabel("Symboles");
ylabel("Amplitude du signal mappé");
legend('Signal mappé');
figure()
