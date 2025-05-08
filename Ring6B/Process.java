import java.util.*;

public class Process {
    int id;
    boolean isAlive;
    static List<Process> ring;

    public Process(int id) {
        this.id = id;
        this.isAlive = true;
    }

    public void startElection() {
        System.out.println("\nProcess " + id + " starts an election.");

        List<Integer> electionPath = new ArrayList<>();
        Set<Integer> candidates = new HashSet<>();
        int n = ring.size();
        int current = (this.id + 1) % n;

        electionPath.add(this.id);
        if (this.isAlive) {
            candidates.add(this.id);
        }

        int sender = this.id;

        while (current != this.id) {
            Process receiver = ring.get(current);
            electionPath.add(receiver.id);

            if (receiver.isAlive) {
                System.out.println("Process " + sender + " -> ELECTION -> Process " + receiver.id);
                candidates.add(receiver.id);
            } else {
                System.out.println("Process " + sender + " -> ELECTION -> Process " + receiver.id + " (crashed, no response)");
            }

            System.out.println("Election path: " + electionPath + "\n");

            sender = receiver.id;
            current = (current + 1) % n;
        }

        int newCoordinator = Collections.max(candidates);
        System.out.println("Final Election path: " + electionPath);
        System.out.println("Election complete. Process " + newCoordinator + " is elected as coordinator.");
        announceCoordinator(newCoordinator);
    }

    public void announceCoordinator(int coordinatorId) {
        System.out.println("\nCoordinator Announcement:");

        for (Process p : ring) {
            if (p.id != coordinatorId && p.isAlive) {
                System.out.println("Process " + coordinatorId + " (Coordinator) -> COORDINATOR -> Process " + p.id);
            }
        }

        System.out.println("\nProcess " + coordinatorId + " is now the coordinator.");
    }
}
