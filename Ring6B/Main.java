import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes in ring: ");
        int n = sc.nextInt();
        sc.nextLine();  // Consume newline

        List<Process> ring = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ring.add(new Process(i));
        }

        // Set static ring reference
        Process.ring = ring;

        System.out.print("Enter IDs of crashed processes (space separated, press Enter to finish): ");
        String[] crashedIds = sc.nextLine().split("\\s+");

        for (String id : crashedIds) {
            if (!id.isEmpty()) {
                int crashedId = Integer.parseInt(id);
                if (crashedId >= 0 && crashedId < n) {
                    ring.get(crashedId).isAlive = false;
                    System.out.println("Process " + crashedId + " is marked as crashed.");
                }
            }
        }

        System.out.print("\nEnter ID of process to start election: ");
        int starter = sc.nextInt();

        if (starter < 0 || starter >= n) {
            System.out.println("Invalid process ID.");
        } else if (!ring.get(starter).isAlive) {
            System.out.println("Cannot start election from a crashed process.");
        } else {
            ring.get(starter).startElection();
        }

        sc.close();
    }
}
