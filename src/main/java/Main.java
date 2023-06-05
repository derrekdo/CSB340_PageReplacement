import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PageGenerator pages = new PageGenerator();
        ArrayList<int[]> referenceStrings = pages.getReferenceString();
        int[] pageFrames = {3,3,3,3,3,3,5,5,5,7,7,7};

        System.out.println("FIFO Algorithm: \n---------------------------\n");
        FIFO fifo = new FIFO(pageFrames, referenceStrings);
        fifo.runAlgo();


        System.out.println("LRU Algorithm: \n---------------------------\n");
        LRU lru = new LRU(pageFrames, referenceStrings);
        lru.runAlgo();


        System.out.println("OPT Algorithm: \n---------------------------\n");
        OPT opt = new OPT(pageFrames, referenceStrings);
        opt.runAlgo();


        System.out.println("Average Fault Rates:  \n---------------------------\n");
        System.out.printf("FIFO Avg: %.2f\n", fifo.findAvgFault());
        System.out.printf("LRU  Avg: %.2f\n", lru.findAvgFault());
        System.out.printf("OPT  Avg: %.2f\n", opt.findAvgFault());

    }
}
