import java.util.Scanner;

public class Rechner
{
    /**
     * @param args
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("shalom ich bin ein geknechteter Taschenrechner gib eine zahl ein!");

        float wert1 = scanner.nextFloat();
        System.out.print("neuezahl!:");
        float wert2 = scanner.nextFloat();

        System.out.print("was willste mit den zahlen machen?\n1-Addieren\n2-Subtrahieren\n3-Multiplizieren\n4-Dividieren\n");

        int operation = scanner.nextInt();
        switch(operation)
        {
        case 1: System.out.println(wert1+wert2); break; 
        case 2: System.out.println(wert1-wert2); break;
        case 3: System.out.println(wert1*wert2); break;
        case 4: System.out.println(wert1/wert2); break;
        default: System.out.println("ne!");
        }    
        scanner.close();
    }
}

