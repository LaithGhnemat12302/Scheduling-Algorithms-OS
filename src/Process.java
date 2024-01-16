// Laith Ghnemat
// 1200610
public class Process {
    private final int processID;
    private final int arrivalTime;
    private final int serviceTime;      // This variable Will not change along the code.
    private int remainingServiceTime;   // Will be decremented along the code until reaches zero(until the job finished).
    private int processTurnaroundTime;  // Turnaround time for the process.
    private int processWaitingTime;     // Waiting time for the process.
    // __________________________________________________________________________________________________________________________
    // When we make an object of type Process, we must assign the id, the arrival time and the service time respectively
    // for this process.
    public Process(int processID, int arrivalTime, int serviceTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
    // _________________________________________________  Getters & Setters  ____________________________________________________
    public int getProcessID() {
        return this.processID;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int getRemainingServiceTime() {
        return remainingServiceTime;
    }

    public void setRemainingServiceTime(int remainingServiceTime) {
        this.remainingServiceTime = remainingServiceTime;
    }

    public int getProcessTurnaroundTime() {
        return processTurnaroundTime;
    }

    public void setProcessTurnaroundTime(int processTurnaroundTime) {
        this.processTurnaroundTime = processTurnaroundTime;
    }

    public int getProcessWaitingTime() {
        return processWaitingTime;
    }

    public void setProcessWaitingTime(int processWaitingTime) {
        this.processWaitingTime = processWaitingTime;
    }
}