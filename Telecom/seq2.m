clear all;
close all;
%Paramètres fixés par l'énoncé
Fe = 24000;
Rb = 3000;
Tb = 1 / Rb;
Te = 1 / Fe;

%Paramètres modifiables
N = 300;
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

% Définition du filtre
T1 = [0 : Te : (Ns1-1) * Te];
h1 = zeros(1,Ns1);
h1(1,1:Ns) = 1;

%Tracé du filtre
plot(T1,h1);
xlabel("Temps");
ylabel("Amplitude");
legend('Filtre')
figure()

%Filtrage
s1 = filter(h1,1,s_imp);
plot(T, s1);
xlabel("Temps");
ylabel("Amplitude");
legend('résultat du filtrage')
figure();

%Calcul de la DSP
N1=1024;
delta_f=Fe/N1;
F = [0 : delta_f : Fe-delta_f];
TfX = abs(fft(s1,Nfft));
P1 = 1 / Nfft * TfX .* TfX; %Périodogramme

%Tracé des DSP
semilogy(F,P1,'g')
xlabel("Fréquence");
ylabel("Amplitude");
legend('DSP1');
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
s_glob = filter(h1,1,s1);
s_glob = s_glob / max(s_glob);
plot(s_glob,'*');
xlabel("Symboles");
ylabel("Amplitude");
legend('tracé de g convolué avec les impulsions')
figure();

%Diagramme de l'oeil
plot(reshape(s_glob,Ns,length(s_glob)/Ns));
legend('Diagramme de l oeil');
figure();




%Paramètres modifiables pour le démapping (optimisation après diagramme de l'oeil)
n0 = Ns;
m = 5;

%échantillonnage pour le démapping
s_sortie_ech = s_glob(n0 : Ns1 : end);
plot(s_sortie_ech,'*');
xlabel("Symboles échantillonnés");
ylabel("Amplitude");
legend('échantillonnage pour le démapping')
figure();

%Taux d'erreur binaire
signal_diff = abs(signal_map - s_sortie_ech) / 2;
taux_erreur_bin = sum(signal_diff) / N;




%Etude avec canal de propagation
Fc = 8000;
ordre = 31;
hc = (2 * Fc/Fe) * sinc(2 * (Fc/Fe) * [-(ordre - 1)/2 : (ordre - 1)/2]);
T = [-(ordre-1)/2 * Te : Te : (ordre-1)/2 * Te];
plot(T,hc);
xlabel("Temps");
ylabel("Amplitude");
legend('Filtre passe-bas');
figure();

%Filtrage passe_bas
s_sortie_pb = filter(hc,1,s_glob);
plot(s_sortie_pb);
xlabel("Echantillons");
ylabel("Amplitude");
legend('Réponse impulsionnelle à la sortie du filtre de réception');
figure();

%Diagramme de l'oeil
plot(reshape(s_sortie_pb,Ns,length(s_sortie_pb)/Ns));
legend('Diagramme de l oeil');
figure();

%Tracé de différentes réponses en fréquences de filtres
nfft = 1024;
F1 = [-Fe/2:Fe/nfft:Fe/2-1];
F2 = [-Fe/2:Fe/nfft:Fe/2-1];
H_g = fftshift(abs(fft(g,nfft)));
H_c = fftshift(abs(fft(hc,nfft)));
semilogy(F1, H_g,'g',F2, H_c,'r');
xlabel("Fréquence");
ylabel("Amplitude");
legend('|G(f)| : réponse en freq de g','|Hc(f)| : réponse en freq de hc');
figure();

%%Détermiation du TEB

%échantillonnage pour le démapping
s_sortie_ech = s_sortie_pb(n0 : Ns1 : end);
plot(s_sortie_ech,'*');
xlabel("Symboles échantillonnés");
ylabel("Amplitude");
figure();

%Taux d'erreur binaire
signal_diff = abs(signal_map - s_sortie_ech) / 2;
TEB = sum(signal_diff) / N;

