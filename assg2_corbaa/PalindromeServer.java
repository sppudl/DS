import PalindromeModule.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

class Palindromelmpl extends PalindromePOA {
    public boolean isPalindrome(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return input.equalsIgnoreCase(reversed);
    }
}

public class PalindromeServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            Palindromelmpl palindromelmpl = new Palindromelmpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(palindromelmpl);
            Palindrome palindromeRef = PalindromeHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] path = ncRef.to_name("Palindrome");
            ncRef.rebind(path, palindromeRef);

            System.out.println("Palindrome Server Ready...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
