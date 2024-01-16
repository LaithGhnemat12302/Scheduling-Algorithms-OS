// Laith Ghnemat
// 1200610
import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;

public class Main {
    // All values that I want to display in the console.
    public static double attOneIterationFCFS = 0;       // Values for each Scheduling Algorithm with 1 iteration.
    public static double attOneIterationSRTF = 0;
    public static double attOneIterationRR = 0;
    public static double attOneIterationMLFQ = 0;
    public static double awtOneIterationFCFS = 0;
    public static double awtOneIterationSRTF = 0;
    public static double awtOneIterationRR = 0;
    public static double awtOneIterationMLFQ = 0;
    // ____________________________________________________________________________________________________________________________________
    public static double attHundredIterationsFCFS = 0;      // Values for each Scheduling Algorithm with 100 iterations.
    public static double attHundredIterationsSRTF = 0;
    public static double attHundredIterationsRR = 0;
    public static double attHundredIterationsMLFQ = 0;
    public static double awtHundredIterationsFCFS = 0;
    public static double awtHundredIterationsSRTF = 0;
    public static double awtHundredIterationsRR = 0;
    public static double awtHundredIterationsMLFQ = 0;
    // ____________________________________________________________________________________________________________________________________
    public static double attThousandIterationsFCFS = 0; // Values for each Scheduling Algorithm with 1000 iterations.
    public static double attThousandIterationsSRTF = 0;
    public static double attThousandIterationsRR = 0;
    public static double attThousandIterationsMLFQ = 0;
    public static double awtThousandIterationsFCFS = 0;
    public static double awtThousandIterationsSRTF = 0;
    public static double awtThousandIterationsRR = 0;
    public static double awtThousandIterationsMLFQ = 0;
    // ____________________________________________________________________________________________________________________________________
    public static double attTenThousandIterationsFCFS = 0;  // Values for each Scheduling Algorithm with a 10 000 iterations.
    public static double attTenThousandIterationsSRTF = 0;
    public static double attTenThousandIterationsRR = 0;
    public static double attTenThousandIterationsMLFQ = 0;
    public static double awtTenThousandIterationsFCFS = 0;
    public static double awtTenThousandIterationsSRTF = 0;
    public static double awtTenThousandIterationsRR = 0;
    public static double awtTenThousandIterationsMLFQ = 0;
    // ____________________________________________________________________________________________________________________________________
    public static double attHundredThousandIterationsFCFS = 0;   // Values for each Scheduling Algorithm with 100 000 iterations.
    public static double attHundredThousandIterationsSRTF = 0;
    public static double attHundredThousandIterationsRR = 0;
    public static double attHundredThousandIterationsMLFQ = 0;
    public static double awtHundredThousandIterationsFCFS = 0;
    public static double awtHundredThousandIterationsSRTF = 0;
    public static double awtHundredThousandIterationsRR = 0;
    public static double awtHundredThousandIterationsMLFQ = 0;
    // ____________________________________________________________________________________________________________________________________
    public static void main(String[] args) {
        // Array for all needed values within 1 iteration.
        double[] oneIteration = {attOneIterationFCFS, awtOneIterationFCFS, attOneIterationSRTF, awtOneIterationSRTF,
                attOneIterationRR, awtOneIterationRR, attOneIterationMLFQ, awtOneIterationMLFQ};
        iterations(oneIteration, 1);    // Apply the algorithms within 1 iteration.

        // Array for all needed values within 100 iteration.
        double[] hundredIterations = {attHundredIterationsFCFS, awtHundredIterationsFCFS, attHundredIterationsSRTF, awtHundredIterationsSRTF,
                attHundredIterationsRR, awtHundredIterationsRR, attHundredIterationsMLFQ, awtHundredIterationsMLFQ};
        iterations(hundredIterations, 100);     // Apply the algorithms within 100 iterations.

        // Array for all needed values within 1000 iteration.
        double[] thousandIterations = {attThousandIterationsFCFS, awtThousandIterationsFCFS, attThousandIterationsSRTF,
                awtThousandIterationsSRTF, attThousandIterationsRR, awtThousandIterationsRR, attThousandIterationsMLFQ,
                awtThousandIterationsMLFQ};
        iterations(thousandIterations, 1000);   // Apply the algorithms within 1000 iterations.

        // Array for all needed values within 10 000 iteration.
        double[] tenThousandIterations = {attTenThousandIterationsFCFS, awtTenThousandIterationsFCFS, attTenThousandIterationsSRTF,
                awtTenThousandIterationsSRTF, attTenThousandIterationsRR, awtTenThousandIterationsRR, attTenThousandIterationsMLFQ,
                awtTenThousandIterationsMLFQ};
        iterations(tenThousandIterations, 10000);   // Apply the algorithms within 10 000 iterations.

        // Array for all needed values within 100 000 iteration.
        double[] hundredThousandIterations = {attHundredThousandIterationsFCFS, awtHundredThousandIterationsFCFS,
                attHundredThousandIterationsSRTF, awtHundredThousandIterationsSRTF, attHundredThousandIterationsRR,
                awtHundredThousandIterationsRR, attHundredThousandIterationsMLFQ, awtHundredThousandIterationsMLFQ};
        iterations(hundredThousandIterations, 100000);  // Apply the algorithms within 100 000 iterations.

        System.out.println();
        System.out.println("################################################ FCFS #######################################################");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", "Iteration", "1","100", "1000", "10000", "100000");
        printOutputATT(oneIteration[0], hundredIterations[0], thousandIterations[0], tenThousandIterations[0], hundredThousandIterations[0]);
        printOutputAWT(oneIteration[1], hundredIterations[1], thousandIterations[1], tenThousandIterations[1], hundredThousandIterations[1]);

        System.out.println();
        System.out.println("################################################ SRTF #######################################################");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", "Iterations", "1", "100", "1000", "10000", "100000");
        printOutputATT(oneIteration[2], hundredIterations[2], thousandIterations[2], tenThousandIterations[2], hundredThousandIterations[2]);
        printOutputAWT(oneIteration[3], hundredIterations[3], thousandIterations[3], tenThousandIterations[3], hundredThousandIterations[3]);

        System.out.println();
        System.out.println("################################################ RR #######################################################");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", "Iterations", "1", "100", "1000", "10000", "100000");
        printOutputATT(oneIteration[4], hundredIterations[4], thousandIterations[4], tenThousandIterations[4], hundredThousandIterations[4]);
        printOutputAWT(oneIteration[5], hundredIterations[5], thousandIterations[5], tenThousandIterations[5], hundredThousandIterations[5]);

        System.out.println();
        System.out.println("################################################ MLFQ #######################################################");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", "Iterations", "1", "100", "1000", "10000", "100000");
        printOutputATT(oneIteration[6], hundredIterations[6], thousandIterations[6], tenThousandIterations[6], hundredThousandIterations[6]);
        printOutputAWT(oneIteration[7], hundredIterations[7], thousandIterations[7], tenThousandIterations[7], hundredThousandIterations[7]);
    }
    // ____________________________________________________________________________________________________________________________________
    // This method will return the double value within the first two digits after the decimal point.
    private static double roundToTwoDecimalPlaces(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNumber = decimalFormat.format(value);
        return Double.parseDouble(formattedNumber);
    }
    // ____________________________________________________________________________________________________________________________________
    // This method will take an array of double(ATT & AWT values for each algorithm for a given number of iterations(count)).
    public static void iterations(double[] arr, int count) {
        ArrayList<Process> processes = new ArrayList<>();
        ArrayList<Process> firstList = new ArrayList<>();
        ArrayList<Process> secondList = new ArrayList<>();
        ArrayList<Process> thirdList = new ArrayList<>();

        for(int i = 1; i <= count; i++) {
            buildProcesses(processes);
            FCFS.schedulingAlgorithm(processes, firstList); // Apply FCFS Algorithm.
            arr[0] += FCFS.averageTurnaroundTime;   // Total turnaround time for FCFS.
            arr[1] += FCFS.averageWaitingTime;      // Total waiting time for FCFS.

            SRTF.schedulingAlgorithm(firstList, secondList);    // Apply SRTF Algorithm.
            arr[2] += SRTF.averageTurnaroundTime;   // Total turnaround time for SRTF.
            arr[3] += SRTF.averageWaitingTime;      // Total waiting time for SRTF.

            RR.schedulingAlgorithm(secondList, thirdList);  // Apply RR Algorithm.
            arr[4] += RR.averageTurnaroundTime;     // Total turnaround time for RR.
            arr[5] += RR.averageWaitingTime;        // Total waiting time for RR.

            MLFQ.schedulingAlgorithm(thirdList);    // Apply MLFQ Algorithm.
            arr[6] += MLFQ.averageTurnaroundTime;      // Total turnaround time for MLFQ.
            arr[7] += MLFQ.averageWaitingTime;      // Total waiting time for MLFQ.

            reset();        // Reset values to be 0 for the next iteration.
        }

        // Calculate ATT & AWT for the given number of iterations.
        arr[0] = roundToTwoDecimalPlaces(arr[0]/count);     // ATT for FCFS.
        arr[1] = roundToTwoDecimalPlaces(arr[1]/count);     // AWT for FCFS.
        arr[2] = roundToTwoDecimalPlaces(arr[2]/count);     // ATT for SRTF.
        arr[3] = roundToTwoDecimalPlaces(arr[3]/count);     // AWT for SRTF.
        arr[4] = roundToTwoDecimalPlaces(arr[4]/count);     // ATT for RR.
        arr[5] = roundToTwoDecimalPlaces(arr[5]/count);     // AWT for RR.
        arr[6] = roundToTwoDecimalPlaces(arr[6]/count);     // ATT for MLFQ.
        arr[7] = roundToTwoDecimalPlaces(arr[7]/count);     // AWT for MLFQ.
    }
    // ____________________________________________________________________________________________________________________________________
    // Generate processes within a random arrival time and a random service time(5-100), and add them to the list.
    public static void buildProcesses(ArrayList<Process> list){
        Random random = new Random();

        list.add(new Process(1, random.nextInt(96) + 5, random.nextInt(96) + 5));
        list.add(new Process(2, random.nextInt(96) + 5, random.nextInt(96) + 5));
        list.add(new Process(3, random.nextInt(96) + 5, random.nextInt(96) + 5));
        list.add(new Process(4, random.nextInt(96) + 5, random.nextInt(96) + 5));
        list.add(new Process(5, random.nextInt(96) + 5,random.nextInt(96) + 5));
        list.add(new Process(6, random.nextInt(96) + 5, random.nextInt(96) + 5));
        list.add(new Process(7, random.nextInt(96) + 5, random.nextInt(96) + 5));
        list.add(new Process(8, random.nextInt(96) + 5, random.nextInt(96) + 5));
    }
    // ____________________________________________________________________________________________________________________________________
    public static void reset(){     // Reset values to be 0 for the next iteration.
        FCFS.currentTime = 0;
        FCFS.totalTurnaroundTime = 0;
        FCFS.totalWaitingTime = 0;
        FCFS.averageTurnaroundTime = 0;
        FCFS.averageWaitingTime = 0;

        SRTF.currentTime = 0;
        SRTF.totalTurnaroundTime = 0;
        SRTF.totalWaitingTime = 0;
        SRTF.averageTurnaroundTime = 0;
        SRTF.averageWaitingTime = 0;

        RR.currentTime = 0;
        RR.totalTurnaroundTime = 0;
        RR.totalWaitingTime = 0;
        RR.averageTurnaroundTime = 0;
        RR.averageWaitingTime = 0;

        MLFQ.currentTime = 0;
        MLFQ.totalTurnaroundTime = 0;
        MLFQ.totalWaitingTime = 0;
        MLFQ.averageTurnaroundTime = 0;
        MLFQ.averageWaitingTime = 0;
    }
    // ____________________________________________________________________________________________________________________________________
    // Print ATT values.
    public static void printOutputATT(double attOne, double attHundred, double attThousand, double attTenThousand, double attHundredThousand){
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", "ATT", attOne, attHundred, attThousand, attTenThousand, attHundredThousand);
    }
    // ____________________________________________________________________________________________________________________________________
    // Print AWT values.
    public static void printOutputAWT(double awtOne, double awtHundred, double awtThousand, double awtTenThousand, double awtHundredThousand){
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", "AWT", awtOne, awtHundred, awtThousand, awtTenThousand, awtHundredThousand);
    }
}