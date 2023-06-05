import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OPTTest {
    @Test
    void optTest() {
//        int[] test = {6,6,5,2,8,5,9,3,7,9,1};
        int[] test = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
        int[] frames = {3};
        ArrayList<int[]> refStrin = new ArrayList<>();
        refStrin.add(test);
        OPT opt = new OPT(frames, refStrin);
        opt.runAlgo();
//        System.out.println(fifo.findAvgFault());
        assertEquals(9, opt.getPageFaultCount());
        assertEquals(11, opt.getPageHitCount());



    }
}
