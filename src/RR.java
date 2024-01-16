// Laith Ghnemat
// 1200610
import java.util.*;

public class RR{
    // We will use Queue for RR Scheduling Algorithm(the CPU will execute the jobs in the queue with a quantum of 20 as
    // first come, first served. So that if the job finished with this quantum, then it's ok. And if not, it will be added
    // to the queue again and so on).
    public static final Queue<Process> queue = new LinkedList<>();
    public static final int quantum = 20;   // The quantum used in RR Scheduling algorithm.
    public static int currentTime = 0; // This variable will be incremented along the algorithm to get the finished time for each job.
    public static int totalTurnaroundTime = 0;  // The total turnaround time for all jobs.
    public static int totalWaitingTime = 0;     // The total waiting time for all jobs.
    public static double averageTurnaroundTime;     // The average turnaround time for all jobs.
    public static double averageWaitingTime;    // The average waiting time for all jobs.
    // __________________________________________________________________________________________________________________________
    // In this method, we pass two ArrayLists as parameters, processes is the list which has the 8 jobs, and we will delete
    // from it and add to the queue. Also, each job will be added to the newList to use the same jobs in the MLFQ algorithm.
    public static void schedulingAlgorithm(ArrayList<Process> processes, ArrayList<Process> newList) {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));   // Sort the jobs by their arrival time in the list.
        newList.clear();    // Clear the newList (initially will be empty).

        for (Process process : processes)       // Initially the remaining service time is the same as the service time.
            process.setRemainingServiceTime(process.getServiceTime());

        while(!processes.isEmpty() || !queue.isEmpty()) {   // While there are existed jobs that aren't finished until now.
            addToQueue(processes, newList);    // If there are jobs arrives delete them from processes list and add them to the queue.
            applyRR(processes, newList);    // Apply the RR algorithm.

            // While there are jobs that doesn't arrive until yet, and the CPU is idle.
            while(!processes.isEmpty() && queue.isEmpty()) {
                currentTime = processes.get(0).getArrivalTime();   // Set currentTime to be the arrival time for the first job in the list.
                addToQueue(processes, newList);//If there are jobs arrives delete them from processes list and add them to the queue.
            }
        }

        averageTurnaroundTime = (double)totalTurnaroundTime/newList.size(); // Calculate the ATT for the 8 jobs.
        averageWaitingTime = (double)totalWaitingTime/newList.size();   // Calculate the AWT for the 8 jobs.
    }
    // __________________________________________________________________________________________________________________________
    // This method will delete the arrived jobs from list, and add them to the queue and the newList.
    private static void addToQueue(ArrayList<Process> list, ArrayList<Process> newList) {
        // While there are jobs in the list, and we passed their arrival time.
        while(!list.isEmpty() && list.get(0).getArrivalTime() <= currentTime) {
            newList.add(list.get(0));   // Add this job to the newList.
            queue.add(list.remove(0));  // Remove this job from the list and add it to the queue.
        }
    }
    // __________________________________________________________________________________________________________________________
    // This method is the RR Scheduling algorithm.
    private static void applyRR(ArrayList<Process> list, ArrayList<Process> newList){
        while (!queue.isEmpty()) {  // While there are jobs in the queue.
            Process currentProcess = queue.poll();  // Delete the first job from the queue.

            // Select the minimum between the quantum and the remaining service time for this job. Then update the remaining service
            // time for this job and update the currentTime.
            int processExecuting = Math.min(quantum, currentProcess.getRemainingServiceTime());
            currentProcess.setRemainingServiceTime(currentProcess.getRemainingServiceTime() - processExecuting);
            currentTime += processExecuting;

            addToQueue(list, newList);//If there are jobs arrives delete them from the list and add them to the queue and the newList.

            if(currentProcess.getRemainingServiceTime() == 0)   // If the job finished execution update the total turnaround time.
                finishedProcess(currentProcess);                // and the total waiting time.
            else {  // If the job didn't finish execution, and if there are jobs arrives delete them from the list and add them to
                addToQueue(list, newList);       // the queue and the newList and add this job after them.
                queue.add(currentProcess);
            }

            while(!list.isEmpty() && queue.isEmpty()) { // While there are jobs, but they aren't reached until yet and
                currentTime = list.get(0).getArrivalTime();//the heap is empty(while the CPU is idle), then update currentTime.
                addToQueue(list, newList);  // If there are jobs arrives delete them from the list and add them to the queue and
            }                                                                                                       // the newList.
        }
    }
    // __________________________________________________________________________________________________________________________
    // This method is to set the turnaround and waiting times for the job that has been finished, and add them to the total turnaround
    // time and the waiting time respectively.
    private static void finishedProcess(Process process) {
        process.setProcessTurnaroundTime(currentTime - process.getArrivalTime());   // Calculate the job turnaround time.
        process.setProcessWaitingTime(process.getProcessTurnaroundTime() - process.getServiceTime());//Calculate the job waiting time.
        totalTurnaroundTime += process.getProcessTurnaroundTime();  // Update the total turnaround time.
        totalWaitingTime += process.getProcessWaitingTime();    // Update the total waiting time.
    }
}