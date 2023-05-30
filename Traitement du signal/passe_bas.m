Fe=10000;
Te=1/Fe;
N=100;
T=[0:Te:(N-1)*Te];
f1=1000;
f2=3000;
arg1=2*pi*f1;
arg2=2*pi*f2;
X=cos(arg1*T)+cos(arg2*T);

% plot(T,X);
% xlabel("Temps");
% ylabel("Amplitude");

N1=1024;
delta_f=Fe/N1;
F=linspace(0,Fe,N1);
TfX=abs(fft(X,N1));

%constitution du filtre1 (ordre 11)
N2=5;
fc=1500/Fe;
k=[-N2:1:N2];
arg3=2*fc;
a=2*fc*sinc(arg3*k);
p=(0.54+0.46*cos(2*pi*k/(2*N2+1)));
h=a.*p;
signal_filtre=filter(h,1,X);


TfX_h=abs(fft(h,N1));
% semilogy(F,TfX_a);
% xlabel("Fréquence");
% ylabel("Amplitude");





%constitution du filtre2 (ordre 61)
N3=30;
fc=1500/Fe;
k2=[-N3:1:N3];
a1=2*fc*sinc(arg3*k2);
p1=(0.54+0.46*cos(2*pi*k2/(2*N3+1)));
h1=a1.*p1;
signal_filtre_2=filter(h,1,X);

TfX_h1=abs(fft(a1,N1));

figure();
plot(linspace(-Fe/2,Fe/2,61),a1);
xlabel("Temps");
ylabel("Amplitude");
figure();

%tracé des filtres et du signal de départ
semilogy(F,TfX);
xlabel("Fréquence");
ylabel("Amplitude");
hold on;

semilogy(F,TfX_h);
xlabel("Fréquence");
ylabel("Amplitude");

semilogy(F,TfX_h1);
xlabel("Fréquence");
ylabel("Amplitude");

legend('signal a filtrer','filtre ordre 11','filtre ordre 61');


%tracé des courbes filtrés et du signal de départ
% semilogy(F,TfX);
% xlabel("Fréquence");
% ylabel("Amplitude");
% hold on;
% 
% TfX_filtre=abs(fft(signal_filtree,N1));
% semilogy(F,TfX_filtre);
% xlabel("Fréquence");
% ylabel("Amplitude");
% legend('filtrage ordre 11');
% 
% TfX_filtre_2=abs(fft(signal_filtree_2,N1));
% semilogy(F,TfX_filtre_2);
% xlabel("Fréquence");
% ylabel("Amplitude");
% legend('signal a filtrer','filtrage ordre 11','filtrage ordre 61');





