import PalindromeModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.Scanner;

public class PalindromeClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            // Obtain a reference to the naming service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            // Resolve the reference to the remote object
            Palindrome palindromeRef = PalindromeHelper.narrow(ncRef.resolve_str("Palindrome"));

            // Take input from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string to check for palindrome: ");
            String input = scanner.nextLine();
            scanner.close();

            // Call the remote method
            boolean result = palindromeRef.isPalindrome(input);

            // Print the result
            System.out.println("Is \"" + input + "\" a palindrome? " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
