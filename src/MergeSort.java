import java.util.ArrayList;

public class MergeSort {

	private ScrabbleNode[] array;
	private ScrabbleNode[] tempArray;
	private int length;

	public ScrabbleNode[] sort(ArrayList<ScrabbleNode> words) {
		array = new ScrabbleNode[words.size()];
		for (int i = 0; i < words.size(); i++) array[i] = words.get(i);
		this.length = words.size();
		this.tempArray = new ScrabbleNode[length];
		doMergeSort(0, length - 1);
		return array;
	}

	private void doMergeSort(int lowerIndex, int higherIndex) {
		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			// sort the left side of the array
			doMergeSort(lowerIndex, middle);
			//  sort the right side of the array
			doMergeSort(middle + 1, higherIndex);
			// merge both sides
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {
		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempArray[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
			if (tempArray[i].getValue() <= tempArray[j].getValue()) {
				array[k] = tempArray[i];
				i++;
			} 
			else {
				array[k] = tempArray[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			array[k] = tempArray[i];
			k++;
			i++;
		}
	}
}
