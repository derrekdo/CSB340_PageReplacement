import java.util.*;

public class OPT extends ReplacementAlgorithm {
    private Queue<Integer> currRefString = new LinkedList<>();
    private ArrayList<Integer> pageFrame = new ArrayList<>();
    private int currPageNum = 0;

    /**
     * @param pageFrameList   - the number of physical page frames
     * @param referenceString
     */
    public OPT(int[] pageFrameList, ArrayList<int[]> referenceString) {
        super(pageFrameList, referenceString);
    }

    public void runAlgo() {
        int i = 0;
        //will read all reference strings
        while(i < pageFrameList.length) {

            //empties the page
            if (!pageFrame.isEmpty()) {
                pageFrame.clear();
                pageFaultCount = 0;
                pageHitCount = 0;
            }
            //makes a queue of the reference string
            for (int num : referenceString.get(i)){
                currRefString.add(num);
            }
            pageFrameCount = pageFrameList[i];
            //reads all the numbers
            while (!currRefString.isEmpty()) {
                //current number to add or skip over
                int curr = currRefString.poll();
                //adds to page frame if not in it
                if (!pageFrame.contains(curr)) {
                    //only adds up until all frames have been filled
                    if (pageFrame.size() < pageFrameCount) {
                        pageFrame.add(curr);
                    } else {
                        // once frame is filled, will find which number to replace
                        int toRemove = -1;
                        //if a number in the page frame no longer appear in the replace it
                        for (int num : pageFrame) {
                            if (!currRefString.contains(num)) {
                                toRemove = num;
                            }
                        }
                        //if all the numbers in the frame still appear find the number the appears the least
                        if (toRemove == -1) {
                            //map to  count their occurences
                            HashMap<Integer, Integer> count = new HashMap<>();
                            //represents the lowest appearance, starts at 100 b/c var needs to be initialized
                            int min = 100;
                            //looks at the number in the ref string
                            for (int num : currRefString) {
                                //if it is the page frame add to map
                                if (pageFrame.contains(num)) {
                                    if (!count.containsKey(num)) {
                                        count.put(num, 1);
                                    } else {
                                        //increment for any subsequent found
                                        count.put(num, count.get(num) + 1);
                                    }
                                    min = count.get(num);
                                }
                            }
                            //min is lowest appearance, and is match to the number in the page frame
                            for (int num : count.values()) {
                                min = Math.min(num, min);
                            }
                            for (int num : count.keySet()) {
                                if (count.get(num) == min) {
                                    toRemove = num;
                                    break;
                                }
                            }
                        }
                        //removes the number from page frame
                        currPageNum = pageFrame.indexOf(toRemove);
                        pageFrame.set(currPageNum, curr);
                    }
                    //increments page fault
                    pageFaultCount++;
                } else {
                    //increments page hit
                    pageHitCount++;
                }
            }
            //decimal value of the fault rate
            faultRate = ((double)getPageFaultCount() / referenceString.get(i).length) * 100;
            //average fault rate for all strings
            totFaultRate += faultRate;
            display(i);
            i++;
        }
    }


    private void display(int i) {
        System.out.println("Reference String:\t" + Arrays.toString(referenceString.get(i)));
        System.out.println("Page Frames:\t\t" + pageFrameCount);
        System.out.println("Fault Rate:\t\t\t" + getPageFaultCount() + "/" + referenceString.get(i).length);
        System.out.println("Hit Rate:\t\t\t" + getPageHitCount() + "/" + referenceString.get(i).length);
        System.out.println();
    }
}
