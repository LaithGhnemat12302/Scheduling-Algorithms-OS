// Laith Ghnemat
// 1200610
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SRTF {
    // We will use min heap for SRTF Scheduling Algorithm(the CPU will execute the job with the shortest remaining service time
    // in the heap).
    public static PriorityQueue<Process> heap = new PriorityQueue<>(Comparator.comparingInt(Process::getRemainingServiceTime));
    public static int currentTime = 0; // This variable will be incremented along the algorithm to get the finished time for each job.
    public static int totalTurnaroundTime = 0;  // The total turnaround time for all jobs.
    public static int totalWaitingTime = 0;     // The total waiting time for all jobs.
    public static double averageTurnaroundTime;     // The average turnaround time for all jobs.
    public static double averageWaitingTime;    // The average waiting time for all jobs.
    // __________________________________________________________________________________________________________________________
    // In this method, we pass two ArrayLists as parameters, processes is the list which has the 8 jobs, and we will delete
    // from it and add to the heap. Also, each job will be added to the newList to use the same jobs in the RR algorithm.
    public static void schedulingAlgorithm(ArrayList<Process> processes, ArrayList<Process> newList) {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));   // Sort the jobs by their arrival time in the list.
        newList.clear();    // Clear the newList (initially will be empty).

        for (Process process : processes)       // Initially the remaining service time is the same as the service time.
            process.setRemainingServiceTime(process.getServiceTime());

        applySRTF(processes, newList);   // Apply the SRTF algorithm.

        averageTurnaroundTime = (double)totalTurnaroundTime/newList.size(); // Calculate the ATT for the 8 jobs.
        averageWaitingTime = (double)totalWaitingTime/newList.size();   // Calculate the AWT for the 8 jobs.
    }
    // __________________________________________________________________________________________________________________________
    // This method will delete the arrived jobs from list, and add them to the heap and the newList.
    private static void addToQueue(ArrayList<Process> list, ArrayList<Process> newList) {
        // While there are jobs in the list, and we passed their arrival time.
        while(!list.isEmpty() && list.get(0).getArrivalTime() <= currentTime) {
            newList.add(list.get(0));   // Add this job to the newList.
            heap.add(list.remove(0));   // Remove this job from the list and add it to the heap.
        }
    }
    // __________________________________________________________________________________________________________________________
    // This method is the SRTF Scheduling algorithm.
    private static void applySRTF(ArrayList<Process> list, ArrayList<Process> newList){
        while (!list.isEmpty() || !heap.isEmpty()) {
            addToQueue(list, newList);  // Delete the arrived jobs from list, and add them to the heap and the newList.

            if (!heap.isEmpty()) {
                Process currentProcess = heap.poll();   // Delete the job with the shortest remaining service time from the heap.
                currentTime++;   // Increment the currentTime by 1 to check if another job with shorter remaining service time has
                // been arrived and give it the priority.

                // Update the remaining service time for this job by decreasing 1.
                currentProcess.setRemainingServiceTime(currentProcess.getRemainingServiceTime() - 1);

                if (currentProcess.getRemainingServiceTime() == 0)  // If the job finished execution update the total turnaround time
                    finishedProcess(currentProcess);                // and the total waiting time.
                else    // If the job didn't finish execution add it to the heap again.
                    heap.add(currentProcess);
            }
            else    // If there are jobs, but they aren't reached until yet and the heap is empty(If the CPU is idle).
                currentTime = list.get(0).getArrivalTime();   // Set currentTime to be the arrival time for the first job in the list.
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