clear all;
close all;
%Paramètres fixés par l'énoncé
Fe=24000;
Rb=3000;
Tb=1/Rb;
Te=1/Fe;
Nfft=1024;

%Paramètres modifiables
N=100;
Ns=Fe/Rb;
Ns1=Ns;
Ns2=Ns*2;
Ns3=Ns;
alpha=0.6;
Ts1=Ns1*Te;
Ts2=Ns2*Te;
Ts3=Ns3*Te;
sigma = 1;

%Génération de BITS
signal_bin=round(rand(N,1));
plot(signal_bin,'*-');
xlabel("Temps");
ylabel("Amplitude du signal binaire");
figure()

%Mapping
signal_map=(signal_bin-1/2)*2;
plot(signal_map,'*');
xlabel("Temps");
ylabel("Amplitude du signal mappé");
figure()

%Génération des impulsions
pic=zeros(1,Ns);
pic(1)=1;
s_imp=kron(signal_map,pic');
plot(s_imp,'*-');
xlabel("Temps");
ylabel("Amplitude des impulsions");
figure()

% Définition du filtre 1
T1=[0:Te:(Ns1-1)*Te];
h1=zeros(1,Ns1);
h1(1,1:Ns)=1;

%Tracé du filtre 1
plot(T1,h1);
xlabel("Temps");
ylabel("Amplitude");
figure()

% Définition du filtre 2
T2=[0:Te:(Ns2-1)*Te];
h2=zeros(1,Ns2);
h2(1,1:Ns2)=1;

%Tracé du filtre 2
plot(T2,h2);
xlabel("Temps");
ylabel("Amplitude");
figure()

% Définition du filtre 3
L=15;
h3=rcosdesign(alpha,L,Ns3);
T3=[0:Te:(length(h3)-1)*Te];

%Tracé du filtre 3
plot(T3,h3);
xlabel("Temps");
ylabel("Amplitude");
figure();

%Filtrage 1
s1=filter(h1,1,s_imp);
plot(s1);
xlabel("Temps");
ylabel("Amplitude");
figure();

%Filtrage 2
s2=filter(h2,1,s_imp);
plot(s2);
xlabel("Temps");
ylabel("Amplitude");
figure();

%Filtrage 3
s3=filter(h3,1,s_imp);
plot(s3);
xlabel("Temps");
ylabel("Amplitude");
figure();

%Calcul de la DSP1
N1=1024;
delta_f=Fe/N1;
F=[0:delta_f:Fe-delta_f];
TfX=abs(fft(s1,Nfft));
P1=1/Nfft*TfX.*TfX; %Périodogramme

%Calcul de la DSP2
TfX=abs(fft(s2,Nfft));
P2=1/Nfft*TfX.*TfX; %Périodogramme

%Calcul de la DSP3
TfX=abs(fft(s3,Nfft));
P3=1/Nfft*TfX.*TfX; %Périodogramme

%Tracé des DSP
semilogy(F,P1,'g',F,P2,'r',F,P3,'b');
xlabel("Fréquence");
ylabel("Amplitude");
legend('DSP1','DSP2','DSP3');
figure();

%Tracé de la DSP1 théo et exp
F_centre=linspace(-Fe/2,Fe/2,Nfft);
sx1=fftshift(Ts1*sinc(F_centre*Ts1).*sinc(F_centre*Ts1));
sx1 = sx1 / max(sx1);
P1 = P1 / max(P1); %On normalise les 2 expressions
semilogy(F,P1,'g',F,sx1,'k');
xlabel("Fréquence");
ylabel("Amplitude");
legend('DSP1 expéritementale','DSP1 théorique');
title("Comparaison des DSP")
figure();

%Tracé de la DSP2 théo et exp
sx2=fftshift(Ts2*sinc(F_centre*Ts2).*sinc(F_centre*Ts2));
sx2 = sx2 / max(sx2);
P2 = P2 / max(P2); %On normalise les 2 expressions
semilogy(F,P2,'g',F,sx2,'k');
xlabel("Fréquence");
ylabel("Amplitude");
legend('DSP2 expéritementale','DSP2 théorique');
title("Comparaison des DSP")
figure();

%Tracé de la DSP3 théo et exp
sx3 = zeros(1,1024);
plage1 = find(F<=(1-alpha)/(2*Ts3));
plage2 = find((1-alpha)/(2*Ts3)<=F);
plage3 = find(F<=(1+alpha)/(2*Ts3));
plage = intersect(plage2,plage3);
sx3(plage1) = sigma^2;
sx3(plage) = 1/2*sigma^2*(1+cos(pi*Ts3/alpha*(F(plage)-(1-alpha)/2*Ts3)));
semilogy(F,P3);
xlabel("Fréquence");
ylabel("Amplitude");
title("DSP")
legend('DSP3 expéritementale');


