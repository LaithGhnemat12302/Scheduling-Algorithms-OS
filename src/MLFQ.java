// Laith Ghnemat
// 1200610
import java.util.*;

public class MLFQ {
    // We will use queue1 as RR Scheduling Algorithm(the CPU will execute the jobs in queue1 with a quantum of 10) and if the job is
    //finished then it's ok. But, if not finished, it will go to queue2(The CPU will execute the jobs in queue2 with a quantum of
    //50(if there are jobs in queue1, then they have more priority than jobs in queue2)). After that, if the job is finished then it's
    //ok, and if not, it will go to queue3 which will work as FCFS(if there are jobs in queue1 and queue2 then they will have more
    //priority than jobs in queue3) --> each job will assign in each queue once at most.
    public static final Queue<Process> queue1 = new LinkedList<>(); // First queue that will work as RR Scheduling with q = 10.
    public static final Queue<Process> queue2 = new LinkedList<>(); // Second queue that will work as RR Scheduling with q = 50.
    public static final Queue<Process> queue3 = new LinkedList<>(); // Third queue that will work as FCFS Scheduling.
    private static final int quantum1 = 10; // Quantum in queue1
    private static final int quantum2 = 50; // Quantum in queue2
    public static int currentTime = 0;  //This variable will be incremented along the algorithm to get the finished time for each job.
    public static int totalTurnaroundTime = 0;  // The total turnaround time for all jobs.
    public static int totalWaitingTime = 0; // The total waiting time for all jobs.
    public static double averageTurnaroundTime; // The average turnaround time for all jobs.
    public static double averageWaitingTime;    // The average waiting time for all jobs.
    // __________________________________________________________________________________________________________________________
    // In this method, we pass one ArrayList as parameter, processes is the list which has the 8 jobs, and we will delete
    // from it and add to queue1, queue2(if needed) and queue3(if needed) respectively.
    public static void schedulingAlgorithm(ArrayList<Process> processes) {
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));   // Sort the jobs by their arrival time in the list.
        int size = processes.size();    // The number of jobs.

        for (Process process : processes)       // Initially the remaining service time is the same as the service time.
            process.setRemainingServiceTime(process.getServiceTime());

        // While there are existed jobs that aren't finished until now, apply the MLFQ Algorithm by adding the arrived jobs into
        // queue1 first, then into queue2 if needed and into queue3 if needed.
        while(!processes.isEmpty() || !queue1.isEmpty() || !queue2.isEmpty() || !queue3.isEmpty()) {
            addToQueueOne(processes);   // If there are jobs arrives delete them from processes list and add them to the queue1.
            applyQ1(processes); // Let the CPU execute the jobs in queue1.
            applyQ2(processes); // Let the CPU execute the jobs in queue2(after ensuring that there is no jobs in queue1).
            applyQ3(processes); // Let the CPU execute the jobs in queue3(after ensuring that there is no jobs in queue1 & queue2).
        }

        averageTurnaroundTime = (double) totalTurnaroundTime/size;  // Calculate the ATT for the 8 jobs.
        averageWaitingTime = (double) totalWaitingTime/size;    // Calculate the AWT for the 8 jobs.
    }
    // __________________________________________________________________________________________________________________________
    // This method will delete the arrived jobs from list, and add them to queue1.
    private static void addToQueueOne(ArrayList<Process> list) {
        // While there are jobs in the list, and we passed their arrival time.
        while(!list.isEmpty() && list.get(0).getArrivalTime() <= currentTime)
            queue1.add(list.remove(0)); // Remove this job from the list and add it to queue1.

        // While there are jobs that doesn't arrive until yet, and the CPU is idle.
        if(!list.isEmpty() && queue1.isEmpty() && queue2.isEmpty() && queue3.isEmpty())
            currentTime = list.get(0).getArrivalTime();   // Set currentTime to be the arrival time for the first job in the list.
    }
    // __________________________________________________________________________________________________________________________
    public static void applyQ1(ArrayList<Process> processes) {  // This method is the RR Scheduling algorithm in queue1 with q = 10.
        addToQueueOne(processes);   // Delete the arrived jobs from processes, and add them to queue1.

        while(!queue1.isEmpty()) {  // While there are jobs in queue1.
            Process currentProcess = queue1.poll(); // Delete the first job from queue1.

            // Select the minimum between quantum1 and the service time for this job. Then update the remaining service
            // time for this job and update the currentTime.
            int processQuantumOneExecuting = Math.min(quantum1, currentProcess.getRemainingServiceTime());
            currentProcess.setRemainingServiceTime(currentProcess.getServiceTime() - processQuantumOneExecuting);
            currentTime += processQuantumOneExecuting;

            if(currentProcess.getRemainingServiceTime() > 0)    // If the job didn't finish execution, add it to queue2.
                queue2.add(currentProcess);
            else    // If the job finished execution update the total turnaround time and the total waiting time.
                finishedProcess(currentProcess);

            addToQueueOne(processes);   // Delete the arrived jobs from processes, and add them to queue1.
        }
    }
    // __________________________________________________________________________________________________________________________
    public static void applyQ2(ArrayList<Process> processes) {  // This method is the RR Scheduling algorithm in queue2 with q = 50.
        while (!queue2.isEmpty()) { // While queue2 has jobs.
            applyQ1(processes); // Jobs in queue1 have more priority than jobs in queue2.

            Process currentProcess = queue2.poll(); // Delete the first job from queue2.
            assert currentProcess != null;  // Ensure that isn't null.

            // Select the minimum between quantum2 and the remaining service time for this job. Then update the remaining service
            // time for this job and update the currentTime.
            int processQuantumTwoExecuting = Math.min(quantum2, currentProcess.getRemainingServiceTime());
            currentProcess.setRemainingServiceTime(currentProcess.getRemainingServiceTime() - processQuantumTwoExecuting);
            currentTime += processQuantumTwoExecuting;

            if(currentProcess.getRemainingServiceTime() > 0)    // If the job didn't finish execution, add it to queue3.
                queue3.add(currentProcess);
            else    // If the job finished execution update the total turnaround time and the total waiting time.
                finishedProcess(currentProcess);
        }
    }
    // __________________________________________________________________________________________________________________________
    public static void applyQ3(ArrayList<Process> processes){   // This method is the FCFS Scheduling algorithm in queue3.
        while (!queue3.isEmpty()) { // While queue3 has jobs.
            applyQ1(processes); // Jobs in queue1 have more priority than jobs in queue3.
            applyQ2(processes); // Jobs in queue2 have more priority than jobs in queue3.

            Process currentProcess = queue3.poll(); // Delete the first job from queue3.
            assert currentProcess != null;  // Ensure that isn't null.
            currentTime += currentProcess.getRemainingServiceTime();    // Update the currentTime.
            currentProcess.setRemainingServiceTime(0);  // The job has finished.

            finishedProcess(currentProcess);    // Update the total turnaround time and the total waiting time.
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