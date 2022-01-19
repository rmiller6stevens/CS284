package sorting;

public class QuickSort {
	
	public static <T extends Comparable <T>>
		void sort(T[] table) {
		// Sort the whole table. 
		quickSort(table, 0, table.length - 1);
	}
	
	private static <T extends Comparable <T>> void quickSort(T[] table, int first, int last) {
		if (first < last) { // There is data to be sorted. 
			int pivIndex = partition(table, first, last); //returns the index of the pivot in its final position
			//TODO: add the recursive calls 
		} 
	}
	
	private static <T extends Comparable <T>> int partition(T[] table, int first, int last) {
		T pivot = table[first];  //set the pivot 
		//TODO: write the code for partition 
		int up = first;
		int down = last;
		while(up < down) {
		while(up < table.length -1) { // goes until end of array
			if(pivot.compareTo(table[up]) > 0) {
				break; // stops when its greater than the pivot
			} else {
				up++;
			}
		}
		while(down > 0) { //goes until the beginning of the array
			if(pivot.compareTo(table[down]) < 0) {
				break; // stops when its less than the pivot
			} else {
				down--;
			}
		}
		T temp = table[up];
		table[up] = table[down];
		table[down] = temp;
	}
		return down;
	}
	
}


