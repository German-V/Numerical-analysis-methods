import java.util.Arrays;

import static java.lang.Math.pow;

public class GZ {

    public static double[][] MatrixMultiplication(double[][]matr1 , double[][]Matr, int n)
    {
        double[][] Res = new double[n][1];
        for(int i = 0;i<n;i++)
            for(int j = 0; j<1;j++)
                for(int k = 0; k<n;k++)
                {
                    Res[i][j] = Res[i][j]+matr1[i][k]*Matr[k][j];
                }
        return Res;
    }
    public static void Newton(){
        double eps = Math.pow(10,-6);
        double[] xk = {-1,-3};
        double[][] delta;
        double[] x = new double[2];
        double max;
        int k=0;
        do {
            k++;
            x[0] = xk[0];
            x[1] = xk[1];
            delta = deltaX(x);
            xk = minus(x, delta);
            max =Math.max(Math.abs(delta[0][0]),Math.abs(delta[1][0]));
            System.out.println(k+"\t"+xk[0] + "\t" + xk[1]+"\t"+max);
        }
        while (max>=eps);
        System.out.println("||f(x)|| =\t"+Math.max(Math.abs(f1(x)),Math.abs(f2(x))));
    }
    public static double f1(double[] x){
        return Math.cos(x[0]-x[1]) - x[0]*x[1]+2;
    }
    public static double f2(double[] x){
        return Math.pow(x[0],2)+x[0]*x[1]-Math.pow(x[1],2)+1.25;
    }
    public static double df1dx1(double[] x){
        return -Math.sin(x[0]-x[1])-x[1];
    }
    public static double df2dx1(double[] x){
        return 2*x[0]+x[1];
    }
    public static double df1dx2(double[] x){
        return Math.sin(x[0]-x[1])-x[0];
    }
    public static double df2dx2(double[] x){
        return x[0]-2*x[1];
    }
    public static double[][] J(double[] x){
        double[][] J = new double[2][2];
        J[0][0] = df2dx2(x);
        J[0][1] = -df1dx2(x);
        J[1][0] = -df2dx1(x);
        J[1][1] = df1dx1(x);
        return J;
    }
    public static double[][] F(double[] x){
        double[][] F = new double[2][1];
        F[0][0] = f1(x);
        F[1][0] = f2(x);
        return F;
    }
    public static double[][] deltaX(double[] x){
        double[][] res =  MatrixMultiplication(J(x),F(x),2);
        res[0][0] /= df1dx1(x)*df2dx2(x)-df1dx2(x)*df2dx1(x);
        res[1][0] /= df1dx1(x)*df2dx2(x)-df1dx2(x)*df2dx1(x);
        return res;
    }
    public static double[] minus(double[] x, double[][] deltaX){
        double[] X = new double[2];
        X[0]=x[0] - deltaX[0][0];
        X[1]=x[1] - deltaX[1][0];
        return X;
    }
    public static void Secant(){
        double eps = 0.000001;
        double[] x = {-1.5,-2};
        double[][] delta = new double[2][1];
        double[] xk = {-1.6,-1.9};
        int k=0;
        double max;
        do {
            k++;
            double a11 = (f1(x) - f1(xk[0],x[1]))/(x[0]-xk[0]); double a12 = (f1(x) - f1(x[0],xk[1]))/(x[1]-xk[1]);
            double a21 = (f2(x) - f2(xk[0],x[1]))/(x[0]-xk[0]); double a22 = (f2(x) - f2(x[0],xk[1]))/(x[1]-xk[1]);
            delta[0][0] = ((-f1(x))*a22+a12*f2(x))/(a11*a22-a12*a21);
            delta[1][0] = ((-a11)*f2(x)+f1(x)*a21)/(a11*a22-a12*a21);
            xk[0] = x[0];
            xk[1] = x[1];
            x[0] = xk[0]+delta[0][0];
            x[1] = xk[1]+delta[1][0];
            max =Math.max(Math.abs(delta[0][0]),Math.abs(delta[1][0]));
            System.out.println(k+"\t"+x[0] + "\t" + x[1]+"\t"+max);
        }
        while (max>=eps);
        System.out.println("||f(x)|| =\t"+Math.max(Math.abs(f1(x)),Math.abs(f2(x))));

    }
    public static double f1(double x1, double x2) {
        return Math.cos(x1-x2) - x1*x2+2;
    }
    public static double f2(double x1, double x2) {
        return Math.pow(x1,2)+x1*x2-Math.pow(x2,2)+1.25;

    }
    public static double df1dx1(double x1, double x2){
        return -Math.sin(x1-x2)-x2;
    }

    public static double df2dx2(double x1,double x2){
        return x1-2*x2;
    }
    public static void GaussZeydel(){

    }
    public static void main(String[] args) {
        System.out.println("Метод Ньютона\n");
        System.out.println("k\tx1\t\t\t\t\tx2\t\t\t\t\t||x(k) - x(k-1)||");
        Newton();
        System.out.println("\nМетод Секущих\n");
        System.out.println("k\tx1\t\t\t\t\tx2\t\t\t\t\t||x(k) - x(k-1)||");
        Secant();
        //GaussZeydel();
    }
}
