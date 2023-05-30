Fe=10000;
Te=1/Fe;
N=90;
T=[0:Te:(N-1)*Te];
fo=1100;
arg=2*pi*fo;
X=cos(arg*T);

% plot(T,X);
% xlabel("Temps");
% ylabel("Amplitude");

N1=1024;
delta_f=Fe/N1;
F=[0:delta_f:Fe-delta_f];
TfX=abs(fft(X,N1));

%plot(F ,TfX);
%semilogy(TfX);
%xlabel("Fréquence");
%ylabel("Amplitude");

R=1/N*conv(X,conj(fliplr(X))); %fonction d'autocorrélation
TfR=fft(R,N1);
plot(F ,abs(TfR));
semilogy(abs(TfR));
xlabel("Fréquence");
ylabel("Amplitude");

P=1/N*TfX.*TfX; %Périodogramme
plot(F ,P);
semilogy(P);
xlabel("Fréquence");
ylabel("Amplitude");

[P_W,F1]=pwelch(X,hanning(length(X)),N/10,N1);
plot(F1 ,P_W);
semilogy(P_W);
xlabel("Fréquence");
ylabel("Amplitude");