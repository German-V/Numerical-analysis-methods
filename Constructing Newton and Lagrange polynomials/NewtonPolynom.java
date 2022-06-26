import java.util.ArrayList;

public class NewtonPolynom {
    static int a = -1;
    static int b = 1;
    public static double f1(double x){
        return 1/Math.pow((1+Math.pow(x,2)),3);
        //return Math.sin(2*x)*Math.log(x+5);
    }

    public static double findXk(int k,double h){
        return a+k*h;
        //return  ((0.)/2 + ((2.)/2)*Math.cos((Math.PI*(2*k+1))/(2*(n+1))));
    }

    public static double tableElement(double f1,double f0,double x1,double x0){
        return (f1-f0)/(x1-x0);
    }
    public static void createTable(int n){
        double h = (double)(b-a)/n;
        ArrayList<Double>[] table = new ArrayList[n+2];
        for (int i = 0; i <= n+1; i++) {
            table[i] = new ArrayList();
        }

            for (int i = 0; i <= n; i++) {
                table[0].add(findXk(i,h));
                table[1].add(f1(table[0].get(i)));
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

            double xi = Math.PI/6;
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

        System.out.println("P(π/6) = "+P);
        System.out.println("|P(π/6)-f(π/6)| = "+Math.abs(P-f1(xi)));

    }

    public static void main(String[] args) {
        double[] a;
        System.out.println("\n1/(1+x^2)^3\n");
        System.out.println("Равноотстоящие узлы ");
        createTable(10);
    }
}
