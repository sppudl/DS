import java.util.*;

public class BullyAlgorithmSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Number of processes
        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            processes.add(new Process(i));
        }

        for (Process p : processes) {
            p.setProcesses(processes);
        }

        // Step 2: Crashed process
        int crashedId = -1;
        boolean validCrashId = false;
        while (!validCrashId) {
            System.out.print("Enter the ID of the crashed process: ");
            crashedId = scanner.nextInt();
            if (crashedId < 1 || crashedId > n) {
                System.out.println("Invalid process ID. Please try again.");
            } else {
                validCrashId = true;
            }
        }
        
        processes.get(crashedId - 1).isAlive = false;
        System.out.println("Process " + crashedId + " has crashed.\n");

        // Step 3: Election initiator
        int starterId = -1;
        boolean validStarterId = false;
        while (!validStarterId) {
            System.out.print("Enter the ID of the process to start the election: ");
            starterId = scanner.nextInt();
            if (starterId < 1 || starterId > n) {
                System.out.println("Invalid process ID. Please try again.");
            } else if (!processes.get(starterId - 1).isAlive) {
                System.out.println("Process " + starterId + " is crashed or not alive. Choose a different process.");
            } else {
                validStarterId = true;
            }
        }

        // Start the election from the chosen process
        processes.get(starterId - 1).startElection();
    }
}
