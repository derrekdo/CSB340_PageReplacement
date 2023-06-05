import java.util.ArrayList;
import java.util.Arrays;


public class FIFO extends ReplacementAlgorithm {

    private int[] currRefString;
    private ArrayList<Integer> pageFrame = new ArrayList<>();
    private int currPageNum = 0;

    /**
     * @param pageFrameList   - the number of physical page frames
     * @param referenceString
     */
    public FIFO(int[] pageFrameList, ArrayList<int[]> referenceString) {
        super(pageFrameList, referenceString);
    }

    public void runAlgo() {
        int i = 0;
        //will finish once all reference strings have been read
        while(i < pageFrameList.length) {

            //empties the page
            if (!pageFrame.isEmpty()) {
                pageFrame.clear();
                pageFaultCount = 0;
                pageHitCount = 0;
            }
            //current reference string a page frames
            currRefString = referenceString.get(i);
            pageFrameCount = pageFrameList[i];

            //runs the FIFO algo on the current refrence string
            for (int j = 0; j < currRefString.length; j++) {
                //make sure that the total frames dont increase
                if (currPageNum == pageFrameCount) {
                    currPageNum = 0;
                }
                //add  the current page request to the frame if not already there
                if(!pageFrame.contains(currRefString[j])) {
                    if (pageFrame.size() < pageFrameCount) {
                        pageFrame.add(currRefString[j]);
                    } else {
                        pageFrame.set(currPageNum, currRefString[j]);
                    }
                    pageFaultCount++;
                    currPageNum++;
                } else {
                    pageHitCount++;
                }
            }
            faultRate = ((double)getPageFaultCount() / currRefString.length) * 100;
            totFaultRate += faultRate;
            display();
            i++;
        }
    }

    public void display() {
        System.out.println("Reference String:\t" + Arrays.toString(currRefString));
        System.out.println("Page Frames:\t\t" + pageFrameCount);
        System.out.println("Fault Rate:\t\t\t" + getPageFaultCount() + "/" + currRefString.length);
        System.out.println("Hit Rate:\t\t\t" + getPageHitCount() + "/" + currRefString.length);
        System.out.println();
    }
}
