import java.rmi.*;
import java.util.Scanner;
public class Client {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        try{
            String url="rmi://localhost/Server";
            ServerIntf s=(ServerIntf)Naming.lookup(url);
            System.out.println("Enter num1:");
            int a=sc.nextInt();
            System.out.println("Enter num2:");
            int b=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter str1:");
            String str1=sc.nextLine();
            System.out.println("Enter str2:");
            String str2=sc.nextLine();


            System.out.println("Add is:"+s.addition(a,b));
            System.out.println("subtract is:"+s.subtract(a,b));
            System.out.println("multiplication is:"+s.multiplication(a,b));
            System.out.println("division is:"+s.division(a,b));
            System.out.println("square is:"+s.square(a));
            System.out.println("square root is:"+s.squareroot(b));
            
            System.out.println("Palindrome of string is:"+s.palindrome(str1));
             
            System.out.println("String is equal or not:"+s.isequalstring(str1,str2));
             
        }
        catch(Exception e){
            System.out.println("Exception at client"+e);
        }
        sc.close();

        }
    }
    
