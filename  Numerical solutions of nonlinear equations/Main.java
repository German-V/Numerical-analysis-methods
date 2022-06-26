public class Main {
    public final static double EPS = 0.0000001;
    public static void MPI(double x)
    {
        double xk = x;
        double xk1 = 0;
        int i = 0;
        double cons;
        System.out.println("Метод простой итерации");
        System.out.println("k" +
                "\txk" +
                "\txk+1");
        do{
            xk1 = Math.log(0.1+Math.sqrt(1-xk*xk));
            cons = Math.abs(xk-xk1);
            i++;
            System.out.println(i+"\t"+xk+ "\t"+xk1+"\t"+cons);
            //System.out.println(cons);
            xk=xk1;
        }
        while(cons>EPS);
        System.out.println();
    }
    public static void Newton(double x){
        double xk = x;
        double xk1 = 0;
        int i = 0;
        double cons;
        System.out.println("Метод Ньютона");
        System.out.println("k" +
                "\txk" +
                "\txk+1");
        do{
            xk1 = xk - ((Math.sqrt(1-xk*xk)-Math.exp(xk)+0.1)
                        / (-(xk+Math.sqrt(1-xk*xk)*Math.exp(xk))/(Math.sqrt(1-xk*xk)))) ;
            cons = Math.abs(xk-xk1);
            i++;
            System.out.println(i+"\t"+xk+ "\t"+xk1+"\t"+cons);
            xk=xk1;
        }
        while(cons>EPS);
        System.out.println();
    }
    public static void NewtonConst(double x){
        double xk = x;
        double xk1 = 0;
        int i = 0;
        double cons;
        System.out.println("Метод Ньютона с постоянной производной");
        System.out.println("k" +
                "\txk" +
                "\txk+1");
        do{

            xk1 = xk - ((Math.sqrt(1-xk*xk)-Math.exp(xk)+0.1)
                        / (-(x+Math.sqrt(1-x*x)*Math.exp(x))/(Math.sqrt(1-x*x)))) ;
            cons = Math.abs(xk-xk1);
            i++;
            System.out.println(i+"\t"+xk+ "\t"+xk1+"\t"+cons);
            xk=xk1;
        }
        while(cons>EPS);
        System.out.println();
    }



    public static void main(String[] args) {
        double a = .01;
        double b = .1075;
        double x = (a+b)/2;
        MPI(x);
        Newton(x);
        NewtonConst(x);
    }
}
