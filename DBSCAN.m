A = Column1;
B = Column2;
C = Column3;

x = A(1);
y = B(1);

plot(x,y,'x','Color','Black','linewidth',2);

hold on

axis([-5 5 -5 5]);
pbaspect([1 1 1]);

for i = 1:100
   
     if C(i) == 2
        plot(A(i),B(i),'x','Color','Red','linewidth',2);
    elseif C(i) == 3
        plot(A(i),B(i),'x','Color','Blue','linewidth',2);
    elseif C(i) == 4
        plot(A(i),B(i),'x','Color','Green','linewidth',2);
    elseif C(i) == 5 %brown
        plot(A(i),B(i),'x','Color','[0.586,0.293,0]','linewidth',2);
    elseif C(i) == 6 %grey
        plot(A(i),B(i),'x','Color','[0.75,0.75,0.75]','linewidth',2);
    elseif C(i) == 10
        plot(A(i),B(i),'x','Color','Magenta','linewidth',2);
    elseif C(i) == 17 %orange
        plot(A(i),B(i),'x','Color','[1, 0.644, 0]','linewidth',2);
    else 
        plot(A(i),B(i),'x','Color','k','linewidth',2);
    end
        
end