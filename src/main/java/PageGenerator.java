import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class PageGenerator
{
	public ArrayList<int[]> getReferenceString() {
		//page frames: 3
		int[] rStr1 = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
		int[] fStr2 = {8,1,0,7,3,0,3,4,5,3,5,2,0,6,8,4,8,1,5,3};
		int[] rStr3 = {4,6,4,8,6,3,6,0,5,9,2,1,0,4,6,3,0,6,8,4};
		ArrayList<int[]> referenceStrings = new ArrayList<>();
		referenceStrings.add(rStr1); referenceStrings.add(fStr2); referenceStrings.add(rStr3);
		referenceStrings.addAll(generateArrays());
		return referenceStrings;
	}

	private ArrayList<int[]> generateArrays() {
		ArrayList<int[]> referenceStrings = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int[] ran1 = IntStream.generate(() -> new Random().nextInt(9)).limit(10).toArray();
			int[] ran2 = IntStream.generate(() -> new Random().nextInt(9)).limit(15).toArray();
			int[] ran3 = IntStream.generate(() -> new Random().nextInt(9)).limit(20).toArray();
			referenceStrings.add(ran1);
			referenceStrings.add(ran2);
			referenceStrings.add(ran3);
		}
		return referenceStrings;
	}


}
