import java.rmi.*;
import java.rmi.server.*;
public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
    public ServerImpl()throws RemoteException{

    }
    public int addition(int a,int b)throws RemoteException{
        return a+b;
    }
    public int subtract(int a,int b)throws RemoteException{
        return a-b;
    }
    public int multiplication(int a,int b)throws RemoteException{
        return a*b;
    }
    public int division(int a,int b)throws RemoteException{
        return a/b;
    }
    public int square(int a)throws RemoteException{
        return a*a;
    }
    public float squareroot(int a)throws RemoteException{
        return (float) (Math.sqrt(a));

    }

    public String palindrome(String str) throws RemoteException{
        StringBuilder sb=new StringBuilder(str);   
        sb.reverse();
        if(str.equals(sb.toString())){
             return "it is palindrome";
        } 
        else{
             return "not palindrome";
        }
    }

    public String isequalstring(String str1,String str2) throws RemoteException{
        if(str1.equals(str2)){
            return "string are equal";
        }
        else{
            return "string are not equal";
        }
    }


    
}
