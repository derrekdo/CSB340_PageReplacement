import java.util.ArrayList;

public abstract class ReplacementAlgorithm
{
	// the number of frames, faults, and hits
	protected int pageFaultCount;
	protected int pageHitCount;
	protected int pageFrameCount;

	protected double totFaultRate;
	protected double faultRate = 0;
	// the number of physical page frames
	protected int[] pageFrameList;

	//list of all reference strings
	protected ArrayList<int[]> referenceString = new ArrayList<>();
	/**
	 * @param pageFrameList - the number of physical page frames
	 */
	public ReplacementAlgorithm(int[] pageFrameList, ArrayList<int[]> referenceString) {
		if (pageFrameList.length == 0)
			throw new IllegalArgumentException();

		for (int i : pageFrameList) {
			if (i < 0) {
				throw new IllegalArgumentException();
			}
		}

		this.referenceString = referenceString;
		this.pageFrameList = pageFrameList;
		pageFaultCount = 0;
		pageHitCount = 0;
	}

	/**
	 * @return - the number of page faults that occurred.
	 */
	public int getPageFaultCount() {
		return pageFaultCount;
	}

	/**
	 * @return the hit count
	 */
	public int getPageHitCount() {
		return pageHitCount;
	}

	/**
	 * @return average fault rate of all reference strings
	 */
	public double findAvgFault(){
		return totFaultRate / referenceString.size();
	}


}
