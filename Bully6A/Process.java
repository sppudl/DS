import java.util.*;

public class Process {
    int id;  // Process ID
    boolean isAlive;  // Process state (alive or crashed)
    boolean isCoordinator;  // Indicates if this process is the coordinator
    boolean electionStarted = false;  // To prevent multiple elections for the same process
    List<Process> processes;  // List of all processes

    // Constructor to initialize the process
    public Process(int id) {
        this.id = id;
        this.isAlive = true;  // By default, the process is alive
        this.isCoordinator = false;  // Initially not the coordinator
    }

    // Method to set the list of all processes (used for election communication)
    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    // Method to start the election
    public void startElection() {
        // If the election has already started or the process is not alive, skip
        if (electionStarted || !isAlive) return;

        electionStarted = true;  // Mark that this process has started the election
        System.out.println("\nProcess " + id + " starts an election.");

        List<Process> higherProcesses = new ArrayList<>();  // Higher priority processes (with higher ID)
        List<Process> aliveResponders = new ArrayList<>();  // Alive processes that respond with OK

        // Step 1: Send ELECTION message to all higher processes
        for (Process p : processes) {
            if (p.id > this.id) {
                System.out.println("Process " + id + " -> ELECTION -> Process " + p.id);
                higherProcesses.add(p);
            }
        }

        // Step 2: Wait for OK responses from higher processes
        for (Process p : higherProcesses) {
            if (p.isAlive) {
                System.out.println("Process " + p.id + " -> OK -> Process " + id);
                aliveResponders.add(p);
            } else {
                System.out.println("Process " + p.id + " is crashed. No OK sent.");
            }
        }

        // Step 3: Recursively start elections for processes that respond with OK
        for (Process p : aliveResponders) {
            p.startElection();
        }

        // Step 4: If no higher process is alive, become coordinator
        if (aliveResponders.isEmpty()) {
            becomeCoordinator();
        }
    }

    // Method to mark this process as the coordinator
    public void becomeCoordinator() {
        // Only become coordinator once and ensure the process is alive
        if (!isAlive || isCoordinator) return;

        isCoordinator = true;  // Mark the process as the coordinator
        System.out.println("\nProcess " + id + " becomes the coordinator.");

        // Inform all other alive processes about the new coordinator
        for (Process p : processes) {
            if (p.id != this.id && p.isAlive) {
                System.out.println("Process " + id + " -> COORDINATOR -> Process " + p.id);
            }
        }
    }
}
