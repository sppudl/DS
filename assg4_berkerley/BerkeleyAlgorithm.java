import java.util.ArrayList;
import java.util.List; 
import java.util.Scanner; 
public class BerkeleyAlgorithm { 
public static void main(String[] args) { 
Scanner scanner = new Scanner(System.in); 
List<BerkeleyClock> clocks = new ArrayList<>(); 
int numClocks = 3; 
System.out.println("Enter the time for " + numClocks + " clocks (in HH:MM format):"); 
for (int i = 0; i < numClocks; i++) { 
int hours, minutes; 
while (true) { 
System.out.print("Enter hours for Clock " + (i + 1) + " (0-23): "); 
hours = scanner.nextInt(); 
System.out.print("Enter minutes for Clock " + (i + 1) + " (0-59): "); 
minutes = scanner.nextInt(); 
if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) { 
break; 
} 
                System.out.println("Invalid time! Please enter hours between 0-23 and minutes between 0-59."); 
            } 
            clocks.add(new BerkeleyClock(hours, minutes)); 
        } 
 
        System.out.println("\nClocks in the distributed system with their clock timings:"); 
        System.out.println("Clock 1 -> " + clocks.get(0).getTime() + " (master clock)"); 
        System.out.println("Clock 2 -> " + clocks.get(1).getTime()); 
        System.out.println("Clock 3 -> " + clocks.get(2).getTime()); 
 
        // Step 2: Master clock requests time from all clocks 
        System.out.println("\nStep 1 − The Leader is elected, Clock 1 is the master in the system."); 
        System.out.println("Step 2 − Master requests time from all clocks."); 
        for (int i = 0; i < numClocks; i++) { 
            System.out.println("Clock " + (i + 1) + " -> time : " + clocks.get(i).getTime()); 
        } 
 
        // Step 3: Calculate average time 
        int totalMinutes = 0; 
        for (BerkeleyClock clock : clocks) { 
            totalMinutes += clock.getTotalMinutes(); 
        } 
        int averageMinutes = totalMinutes / numClocks; 
        int avgHours = averageMinutes / 60; 
        int avgMinutes = averageMinutes % 60; 
 
        System.out.println("\nStep 3 − The master calculates the time difference for each clock:"); 
 
        // Step 4: Display differences from Clock 1 
        int masterTime = clocks.get(0).getTotalMinutes(); 
        for (int i = 1; i < numClocks; i++) { 
            int difference = clocks.get(i).getTotalMinutes() - masterTime; 
            System.out.printf("Clock %d -> Difference from Clock 1: %+d minutes\n", (i + 1), 
difference); 
        } 
 
        System.out.println("\nStep 4 − The master averages the times and sends the correction time back to the clocks."); 
 
        // Step 5: Apply adjustments and display results 
        for (int i = 0; i < numClocks; i++) { 
            int currentMinutes = clocks.get(i).getTotalMinutes(); 
            int adjustment = averageMinutes - currentMinutes; 
            clocks.get(i).updateTime(adjustment); 
            System.out.printf("Clock %d -> Corrected Time %s (%+d minutes)\n", (i + 1), 
clocks.get(i).getTime(), adjustment); 
        } 
 
        scanner.close(); 
    } 
}