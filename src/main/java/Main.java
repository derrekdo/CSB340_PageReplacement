import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PageGenerator pages = new PageGenerator();
        ArrayList<int[]> referenceStrings = pages.getReferenceString();
        int[] pageFrames = {3,3,3,3,3,3,5,5,5,7,7,7};

        FIFO fifo = new FIFO(pageFrames, referenceStrings);
        fifo.runAlgo();
        System.out.printf("%.2f", fifo.findAvgFault());

    }
}
