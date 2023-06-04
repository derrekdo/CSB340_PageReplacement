import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FIFOTest {

    @Test
    void fifoTest() {
        int[] test = {6,6,5,2,8,5,9,3,7,9,1};
//        int[] test = {7,0,1,2,0,3,0,4,2,3,0,3,0,3,2,1,2,0,1,7,0,1};
        int[] frames = {3};
        ArrayList<int[]> refStrin = new ArrayList<>();
        refStrin.add(test);
        FIFO fifo = new FIFO(frames, refStrin);
        fifo.runAlgo();
//        System.out.println(fifo.findAvgFault());
        assertEquals(8, fifo.getPageFaultCount());
        assertEquals(3, fifo.getPageHitCount());

    }
}
