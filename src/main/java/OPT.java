import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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
        //will finish once all reference strings have been read
        while(i < pageFrameList.length) {

            //empties the page
            if (!pageFrame.isEmpty()) {
                pageFrame.clear();
                pageFaultCount = 0;
                pageHitCount = 0;
            }
            //current reference string a page frames
//            int length = referenceString.get(i).length;
            for (int num : referenceString.get(i)){
                currRefString.add(num);
            }
            pageFrameCount = pageFrameList[i];
            while (!currRefString.isEmpty()) {
                int curr = currRefString.poll();
                System.out.println(currRefString);
                System.out.println(pageFrame);
                System.out.println();
                if (!pageFrame.contains(curr)) {
                    if (pageFrame.size() < pageFrameCount) {
                        pageFrame.add(curr);
                    } else {
                        int toRemove = -1;
                        for (int num : pageFrame) {
                            if (!currRefString.contains(num)) {
                                toRemove = num;
                                System.out.println("Queue no lnger contains: " + toRemove);
                            }
                        }
                        if (toRemove == -1) {
                            HashMap<Integer, Integer> count = new HashMap<>();
                            int min = 10000;
                            for (int num : currRefString) {

                                if (pageFrame.contains(num)) {
                                    if (!count.containsKey(num)) {
                                        count.put(num, 1);
                                    } else {
                                        count.put(num, count.get(num) + 1);
                                    }
                                    min = count.get(num);
                                }
                            }
//                            System.out.println("here3");
                            for (int num : count.values()) {
                                min = Math.min(num, min);
                            }
                            for (int num : count.keySet()) {
                                if (count.get(num) == min) {
                                    toRemove = num;
                                    System.out.println(count);
                                    System.out.println("Appears the least: " + toRemove );
                                    break;
                                }
                            }
                        }
                        currPageNum = pageFrame.indexOf(toRemove);
                        pageFrame.set(currPageNum, curr);
                    }
                    pageFaultCount++;
                } else {
                    pageHitCount++;
                }
            }
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
