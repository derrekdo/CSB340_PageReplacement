import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LRU extends  ReplacementAlgorithm {
    private int[] currRefString;
    private ArrayList<Integer> pageFrame = new ArrayList<>();
    private int currPageNum = 0;
    private Queue<Integer> prev = new LinkedList<>();
    /**
     * @param pageFrameList   - the number of physical page frames
     * @param referenceString
     */
    public LRU(int[] pageFrameList, ArrayList<int[]> referenceString) {
        super(pageFrameList, referenceString);
    }

    public void runAlgo(){
        int i = 0;
        //will finish once all reference strings have been read
        while(i < pageFrameList.length) {

            //empties the page
            if (!pageFrame.isEmpty()) {
                pageFrame.clear();
                prev.clear();
                pageFaultCount = 0;
                pageHitCount = 0;
            }

            //current reference string a page frames
            currRefString = referenceString.get(i);
            pageFrameCount = pageFrameList[i];

            //runs the LRU algo on the current refrence string
            for (int j = 0; j < currRefString.length; j++) {
                //add  the current page request to the frame if not already there
                if(!pageFrame.contains(currRefString[j])) {
                    //add until all the frames are filled
                    if (pageFrame.size() < pageFrameCount) {
                        pageFrame.add(currRefString[j]);
                    } else {
                        //removes the number from the page frame if did not appear for
                        //a long time
                        int toRemove = 0;
                        for (int num : pageFrame) {
                            if (!prev.contains(num)){
                                toRemove = num;
                            }
                        }
                        //the number to replace
                        currPageNum = pageFrame.indexOf(toRemove);
                        pageFrame.set(currPageNum, currRefString[j]);
                    }
                    //increment page fault
                    pageFaultCount++;
                } else {
                    //incremtn page hit
                    pageHitCount++;
                }
                //keeps track of the previous numbers
                if(prev.size() == pageFrameCount-1) {
                    prev.poll();
                }
                //adds the the most recent page request to list queue of numbers that recently appeared
                prev.add(currRefString[j]);
            }
            faultRate =  ((double)getPageFaultCount() / currRefString.length) * 100;
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
