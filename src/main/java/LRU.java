import java.util.ArrayList;
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
                //make sure that the total frames dont increase
//                if (currPageNum == pageFrameCount) {
//                    currPageNum = 0;
//                }
                //add  the current page request to the frame if not already there
                if(!pageFrame.contains(currRefString[j])) {
                    if (pageFrame.size() < pageFrameCount) {
                        pageFrame.add(currRefString[j]);
                    } else {
                        //finds the frame to replace
                        int toRemove = 0;
                        for (int num : pageFrame) {
                            if (!prev.contains(num)){
                                toRemove = num;
                            }
                        }
                        currPageNum = pageFrame.indexOf(toRemove);
                        pageFrame.set(currPageNum, currRefString[j]);
                    }
                    pageFaultCount++;
                    currPageNum++;
                } else {
                    pageHitCount++;
                }
                //keeps track of the previous numbers
                if(prev.size() == pageFrameCount-1) {
                    prev.poll();
                }
                prev.add(currRefString[j]);
//                System.out.println("page " +pageFrame);
//                System.out.println("prev num "+prev);
//                System.out.println();

            }
            totFaultRate += ((double)getPageFaultCount() / currRefString.length) * 100;
            display();

            i++;
        }
    }

    @Override
    public void insert(int pageNumber) {

    }

    @Override
    public void display() {

    }
}
