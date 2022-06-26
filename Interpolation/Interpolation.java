import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Interpolation {
    static int a = -2;
    static int b = 2;
    public static double f1(double x){
        return Math.exp(Math.sin(x));
        //return Math.sin(2*x)*Math.log(x+5);
    }
    public static double f2(double x){
        return Math.abs(2 * Math.sin(2 * x) - 1);
    }

    public static double findXk(int k,int n){
        return  ((0.)/2 + ((4.)/2)*Math.cos((Math.PI*(2*k+1))/(2*(n+1))));
    }
    public static double findXk2(int k,double h){
        return a+k*h;
    }
    public static double tableElement(double f1,double f0,double x1,double x0){
        return (f1-f0)/(x1-x0);
    }
    public static void createTableCheb(int n, int func){
        //n=3;
        ArrayList<Double>[] table = new ArrayList[n+2];
        for (int i = 0; i <= n+1; i++) {
            table[i] = new ArrayList();
        }
        double[] x ={0,2,3,5};
        double[] f={1,4,-3,1};
        if(func == 1){
            for (int i = 0; i <= n; i++) {
//                table[0].add(x[i]);
//                table[1].add(f[i]);
                table[0].add(findXk(i,n));
                table[1].add(f1(table[0].get(i)));
            }}
        else {
            for (int i = 0; i <= n; i++) {
                table[0].add(findXk(i,n));
                table[1].add(f2(table[0].get(i)));
            }
        }
        int p = 1;
        int l = 0;
        for (int i = 2; i <= n+1; i++) {
            l = i-1;
            for (int j = 0; j < n+1-p; j++) {
                table[i].add(tableElement(table[i-1].get(j+1),
                        table[i-1].get(j),
                        table[0].get(l),
                        table[0].get(l-i+1)));
                l++;
            }
            p++;
        }
        System.out.println("\nn = "+n);
        StringBuilder answer = new StringBuilder();
        answer.append(table[1].get(0));

        for (int i = 2; i <= n+1; i++) {
            if(Math.signum(table[i].get(0))>=0)
                answer.append(" + "+ table[i].get(0));
                //answer.append(" + "+ new BigDecimal(table[i].get(0)).toPlainString());
            else answer.append(table[i].get(0));
            //else answer.append(new BigDecimal(table[i].get(0)).toPlainString());
            if(Math.signum(table[0].get(0))>=0)
                answer.append("*(x-" + table[0].get(0)+")");
                //answer.append("*(x-" + new BigDecimal(table[0].get(0)).toPlainString()+")");
            else answer.append("*(x+" + Math.abs(table[0].get(0))+")");
            //else answer.append("*(x" + new BigDecimal(table[0].get(0)).toPlainString()+")");
            for (int j = 1; j < i-1; j++) {
                if(Math.signum(table[0].get(j))>=0)
                    answer.append("*(x-" + table[0].get(j)+")");
                    //answer.append("*(x-" + new BigDecimal(table[0].get(j)).toPlainString()+")");
                else
                    answer.append("*(x+" + Math.abs(table[0].get(j))+")");
                //answer.append("*(x" + new BigDecimal(table[0].get(j)).toPlainString()+")");

            }
        }
        System.out.println(answer.toString());
        double max=0;

        for (int i = 0; i < 100; i++) {
            double xi = a+(double)i*(b-a)/100;
            double P = table[1].get(0);
            double add = 0;
            for (int j = 2; j <= n+1; j++) {
                add=0;
                add+=table[j].get(0);
                for (int k = 0; k < j-1; k++) {
                    add*=(xi-table[0].get(k));
                }
                P+=add;
            }
            if(func==1){
                if(max<Math.abs(P-f1(xi)))
                max=Math.abs(P-f1(xi));
            }
            else{
                if(max<Math.abs(P-f2(xi)))
                max=Math.abs(P-f2(xi));
            }
        }
        System.out.println("max|P(x)-f(x)| = "+max);
    }
    public static void createTable(int n, int func){
        double h = (double)(b-a)/n;
        ArrayList<Double>[] table = new ArrayList[n+2];
        for (int i = 0; i <= n+1; i++) {
            table[i] = new ArrayList();
        }
        if(func == 1){
        for (int i = 0; i <= n; i++) {
            table[0].add(findXk2(i,h));
            table[1].add(f1(table[0].get(i)));
        }}
        else {
            for (int i = 0; i <= n; i++) {
                table[0].add(findXk2(i,h));
                table[1].add(f2(table[0].get(i)));
            }
        }
        int p = 1;
        int l = 0;
        for (int i = 2; i <= n+1; i++) {
            l = i-1;
            for (int j = 0; j < n+1-p; j++) {
                table[i].add(tableElement(table[i-1].get(j+1),
                        table[i-1].get(j),
                        table[0].get(l),
                        table[0].get(l-i+1)));
                l++;
            }
            p++;
        }
        System.out.println("\nn = "+n);
        StringBuilder answer = new StringBuilder();
        answer.append(table[1].get(0));

        for (int i = 2; i <= n+1; i++) {
            if(Math.signum(table[i].get(0))>=0)
                answer.append(" + "+ table[i].get(0));
                //answer.append(" + "+ new BigDecimal(table[i].get(0)).toPlainString());
            else answer.append(table[i].get(0));
            //else answer.append(new BigDecimal(table[i].get(0)).toPlainString());
            if(Math.signum(table[0].get(0))>=0)
                answer.append("*(x-" + table[0].get(0)+")");
                //answer.append("*(x-" + new BigDecimal(table[0].get(0)).toPlainString()+")");
            else answer.append("*(x+" + Math.abs(table[0].get(0))+")");
            //else answer.append("*(x" + new BigDecimal(table[0].get(0)).toPlainString()+")");
            for (int j = 1; j < i-1; j++) {
                if(Math.signum(table[0].get(j))>=0)
                    answer.append("*(x-" + table[0].get(j)+")");
                    //answer.append("*(x-" + new BigDecimal(table[0].get(j)).toPlainString()+")");
                else
                    answer.append("*(x+" + Math.abs(table[0].get(j))+")");
                //answer.append("*(x" + new BigDecimal(table[0].get(j)).toPlainString()+")");

            }
        }
        System.out.println(answer.toString());
        double max =0;
        for (int i = 0; i < 100; i++) {
            double xi = a+(double)i*(b-a)/100;
            double P = table[1].get(0);
            double add = 0;
            for (int j = 2; j <= n+1; j++) {
                add=0;
                add+=table[j].get(0);
                for (int k = 0; k < j-1; k++) {
                    add*=(xi-table[0].get(k));
                }
                P+=add;
            }
            if(func==1){
                if(max<Math.abs(P-f1(xi)))
                max=Math.abs(P-f1(xi));
            }
            else{
                if(max<Math.abs(P-f2(xi)))
                max=Math.abs(P-f2(xi));
            }
        }
        System.out.println("max|P(x)-f(x)| = "+max);
    }
    public static void main(String[] args) {
        int[] n = {5,10,15,20};
        double[] max = new double[n.length];
        double[] a;
        System.out.println("\ne^sin(x)\n");
        System.out.println("Узлы Чебышева ");
        for (int i = 0; i < n.length; i++) {
            createTableCheb(n[i],1);
        }
        System.out.println("Равноотстоящие узлы ");
        for (int i = 0; i < n.length; i++) {
            createTable(n[i],1);
        }
        System.out.println("\n|2sin(2x)-1|\n");
        System.out.println("\nУзлы Чебышева ");
        for (int i = 0; i < n.length; i++) {
            createTableCheb(n[i],2);
        }
        System.out.println("\nРавноотстоящие узлы ");
        for (int i = 0; i < n.length; i++) {
            createTable(n[i],2);
        }
    }
}
