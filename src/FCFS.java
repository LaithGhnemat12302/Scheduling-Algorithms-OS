// Laith Ghnemat
// 1200610
import java.util.*;

public class FCFS{
    // We will use Queue for FCFS Scheduling Algorithm(the CPU will execute the jobs in the queue as first come, first served).
    public static final Queue<Process> queue = new LinkedList<>();
    public static int currentTime = 0; // This variable will be incremented along the algorithm to get the finished time for each job.
    public static int totalTurnaroundTime = 0;  // The total turnaround time for all jobs.
    public static int totalWaitingTime = 0;     // The total waiting time for all jobs.
    public static double averageTurnaroundTime;     // The average turnaround time for all jobs.
    public static double averageWaitingTime;    // The average waiting time for all jobs.
    // __________________________________________________________________________________________________________________________
    // In this method, we pass two ArrayLists as parameters, processes is the list which has the 8 jobs, and we will delete
    // from it and add to the queue. Also, each job will be added to the newList to use the same jobs in the SRTF algorithm.
    public static void schedulingAlgorithm(ArrayList<Process> processes, ArrayList<Process> newList){
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));   // Sort the jobs by their arrival time in the list.
        newList.clear();    // Clear the newList (initially will be empty).

        for (Process process : processes)       // Initially the remaining service time is the same as the service time.
            process.setRemainingServiceTime(process.getServiceTime());

        while(!processes.isEmpty() || !queue.isEmpty()) {   // While there are existed jobs that aren't finished until yet.
            addToQueue(processes, newList); // If there are jobs arrives delete them from processes list and add them to the queue.
            applyFCFS(processes, newList);  // Apply the FCFS algorithm.
        }

        averageTurnaroundTime = (double)totalTurnaroundTime/newList.size(); // Calculate the ATT for the 8 jobs.
        averageWaitingTime = (double)totalWaitingTime/newList.size();   // Calculate the AWT for the 8 jobs.
    }
    // __________________________________________________________________________________________________________________________
    // This method will delete the arrived jobs from list, and add them to the queue and the newList.
    private static void addToQueue(ArrayList<Process> list, ArrayList<Process> newList){
        // While there are jobs in the list, and we passed their arrival time.
        while(!list.isEmpty() && list.get(0).getArrivalTime() <= currentTime) {
            newList.add(list.get(0));   // Add this job to the newList.
            queue.add(list.remove(0));  // Remove this job from the list and add it to the queue.
        }

        if(!list.isEmpty() && queue.isEmpty())  // If there are jobs that doesn't arrive until yet, and the CPU is idle.
            currentTime = list.get(0).getArrivalTime();     // Set currentTime to be the arrival time for the first job in the list.
    }
    // __________________________________________________________________________________________________________________________
    // This method is the FCFS Scheduling algorithm.
    private static void applyFCFS(ArrayList<Process> list, ArrayList<Process> newList){
        while (!queue.isEmpty()) {
            addToQueue(list, newList);  // Delete the arrived jobs from list, and add them to the queue and the newList.

            Process currentProcess = queue.poll();  // Delete the first job from the queue.
            assert currentProcess != null;  // Make sure it is not equal null.

            //Increment the currentTime by the service time of this job(until the job finished).
            currentTime += currentProcess.getRemainingServiceTime();
            currentProcess.setRemainingServiceTime(0);  // The job has finished.

            finishedProcess(currentProcess);// Update totalTurnaroundTime & totalWaitingTime after this job finish.
        }
    }
    // __________________________________________________________________________________________________________________________
    // This method is to set the turnaround and waiting times for the job that has been finished, and add them to the total turnaround
    // time and the waiting time respectively.
    private static void finishedProcess(Process process){
        process.setProcessTurnaroundTime(currentTime - process.getArrivalTime());   // Calculate the job turnaround time.
        process.setProcessWaitingTime(process.getProcessTurnaroundTime() - process.getServiceTime());//Calculate the job waiting time.
        totalTurnaroundTime += process.getProcessTurnaroundTime();  // Update the total turnaround time.
        totalWaitingTime += process.getProcessWaitingTime();    // Update the total waiting time.
    }
}