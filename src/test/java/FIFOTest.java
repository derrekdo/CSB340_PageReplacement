import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FIFOTest {

    @Test
    void fifoTest() {
        int[] test1 = {6,6,5,2,8,5,9,3,7,9,1};
        int[] test2 = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
        int[] test3 = {8,3,2,3,7,8,2,8,3,5,7,5,2};
        int[] frames1 = {3};
        int[] frames2 = {4};

        ArrayList<int[]> refStrin = new ArrayList<>();
        refStrin.add(test2);
        FIFO fifo = new FIFO(frames1, refStrin);
        fifo.runAlgo();

        assertEquals(15, fifo.getPageFaultCount());
        assertEquals(5, fifo.getPageHitCount());

        refStrin.remove(0);
        refStrin.add(test1);
        fifo = new FIFO(frames1, refStrin);
        fifo.runAlgo();

        assertEquals(8, fifo.getPageFaultCount());
        assertEquals(3, fifo.getPageHitCount());

        refStrin.remove(0);
        refStrin.add(test3);
        fifo = new FIFO(frames2, refStrin);
        fifo.runAlgo();

        assertEquals(5, fifo.getPageFaultCount());
        assertEquals(8, fifo.getPageHitCount());

    }
}
