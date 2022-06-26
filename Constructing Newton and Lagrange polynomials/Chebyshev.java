import java.util.ArrayList;

public class Chebyshev {
    static int a = -1;
    static int b = 1;
    public static double f1(double x){
        return 1/Math.pow((1+Math.pow(x,2)),3);
        //return Math.sin(2*x)*Math.log(x+5);
    }

    public static double findXk(int k,int n){
        return  Math.cos((Math.PI*(2*k+1))/(2*(n+1)));
        //return  Math.cos((2*k+1)*Math.PI/(2*n));
    }

    public static double tableElement(double f1,double f0,double x1,double x0){
        return (f1-f0)/(x1-x0);
    }
    public static void LagrangeInPoint(int n, double point){
        double[] X = new double[n+1];
        double[] F = new double[n+1];
        for (int i = 0; i <= n ; i++) {
            X[i] = findXk(i,n);
            F[i] = f1(X[i]);
        }
        double L=0;
        double x;
        for (int i = 0; i <= n; i++) {
            x=1;
            for (int j = 0; j <= n; j++) {
                if(j!=i)
                x*=(point-X[j])/(X[i]-X[j]);
            }
            L+= F[i]*x;
        }
        System.out.println("L(π/6) = "+L);
        System.out.println("|P(π/6)-f(π/6)| = "+Math.abs(L-f1(point)));

    }
    public static void Lagrange(int n){
        double[] X = new double[n+1];
        double[] F = new double[n+1];
        for (int i = 0; i <= n ; i++) {
            X[i] = findXk(i,n);
            F[i] = f1(X[i]);
        }
        StringBuilder L = new StringBuilder();
        double x;
        for (int i = 0; i <= n; i++) {
            x=1;
            L.append("+");
            for (int j = 0; j <= n; j++) {
                double xMinusX = X[i]-X[j];
                if(j!=i)
                    L.append("((x-"+X[j]+")/"+xMinusX+")*");
            }
            L.append("("+F[i]+")");
        }
        System.out.println(L);


    }

    public static void main(String[] args) {
        double[] a;
        System.out.println("\n1/(1+x^2)^3\n");
        System.out.println("Многочлен Лагранжа ");
        Lagrange(10);
        LagrangeInPoint(10,Math.PI/6);
        //createTableCheb(10);
    }
}
