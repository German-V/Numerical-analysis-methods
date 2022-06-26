import java.util.Arrays;

public class MinSqr {
    static double[] xk;
    static double[] fk;
    public static double[] countS(int n){
        double[] S = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < xk.length; j++) {
                S[i] += Math.pow(xk[j], i);
            }
        }
        return S;
    }
    public static double[] countM(int n){
        double[] M = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < xk.length; j++) {
                M[i] += fk[j] * Math.pow(xk[j], i);
            }
        }
        return M;
    }
    public static double[][] createMatr(int n){
        n++;
        double[][] Matr = new double[n][n+1];
        double[] s = countS(2*n);
        double[] m = countM(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Matr[i][j] = s[i+j];
            }
        }
        for (int i = 0; i < n; i++) {
            Matr[i][n] = m[i];
        }
        return Matr;
    }
public static double findQ(int k, double[] c){
        double Q = 0;
        for (int i = 0; i < c.length; i++) {
            Q+= c[i] * Math.pow(xk[k],i);
        }
        return Q;
}
    public static double findDelta(double[] c){
        double delta = 0;
        for (int i = 0; i < xk.length; i++) {
            delta += Math.pow(fk[i] - findQ(i,c),2);
        }
        return delta;
    }
    public static void MatrixPrint(double[][] Matr, int n)
    {
        if(Matr[0].length==n)
        {
            for(int i =0; i<n; i++)
            {
                for(int j=0;j<n;j++)
                {
                    System.out.print(Matr[i][j] + " ");
                }
                System.out.println();
            }
        }
        else
        {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n + 1; j++) {
                    System.out.print(Matr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    public static double[][]Gauss3(double[][]Matr, int n)
    {
        int i1=0;
        double devider = 0;
        for(int BIGFOR = 0; BIGFOR < n; BIGFOR++)
        {
            for(int i = 0;i<n; i++)
            {
                if(Math.abs(Matr[i1][BIGFOR])<Math.abs(Matr[i][BIGFOR]))
                    i1=i;
            }
            devider=Matr[i1][BIGFOR];
            if(i1!=BIGFOR)
            {
                double[] TEMP = new double[n+1];
                for(int i =0;i<n+1;i++)
                {
                    TEMP[i] = Matr[i1][i];
                    Matr[i1][i] = Matr[BIGFOR][i];
                    Matr[BIGFOR][i] = TEMP[i];
                }
                i1=BIGFOR;
            }
            for(int i =0;i<n+1;i++)
            {
                Matr[i1][i]/=devider;
            }
            for(int i=i1+1;i<n;i++)
            {
                devider = Matr[i][i1];
                if(devider!=0)
                {
                    for(int j= 0; j< n+1;j++)
                    {
                        Matr[i][j] = Matr[i][j] - devider*Matr[i1][j];
                    }
                }
            }

            if(i1<n)i1++;
        }
        return Matr;
    }

    public static double[] GaussX(double[][] Matr, int n)
    {
        double[] X = new double[n];
        Arrays.fill(X,1.0);
        for(int i = n-1; i >= 0; i--)
        {
            X[i] = Matr[i][n];
            for(int j = i+1; j < n;j++)
            {
                X[i] = X[i] - X[j]*Matr[i][j];

            }
        }
        return X;
    }
    public static void main(String[] args) {
        xk = new double[41];
        double start = -2;
        for (int i = 0; i < 41; i++) {
            xk[i] = start;
            start += 0.1;
        }
        fk = new double[41];
        for (int i = 0; i < xk.length; i++) {
            fk[i] = Math.exp(Math.sin(xk[i]));
        }
        double[] c;
        System.out.println("n = 2:\n");
        c = GaussX(Gauss3(createMatr(2),3),3);
        System.out.println("C[i] = "+Arrays.toString(c));
        System.out.println("Величина наилучшего среднекв. приближения: " + findDelta(c));
        System.out.println("\nn = 2:\n");
        c = GaussX(Gauss3(createMatr(5),6),6);
        System.out.println("C[i] = "+Arrays.toString(c));
        System.out.println("Величина наилучшего среднекв. приближения: " + findDelta(c));
    }
}
