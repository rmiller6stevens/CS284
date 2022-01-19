package sorting;

import java.util.ArrayList;

public class MergeSort {
	
	private static <T extends Comparable<T>> void merge(T[]outputSeq, T[] leftSeq, T[] rightSeq) {
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		System.out.println(leftSeq[0]);
		while(i1 != leftSeq.length - 1 && i2 != rightSeq.length -1 ) {
			if(leftSeq[i1].compareTo(rightSeq[i1]) < 0 && i1 < leftSeq.length) {
				outputSeq[i3] = leftSeq[i1];
				i1++;
				i3++;
			} else if(i2 < rightSeq.length) {
				outputSeq[i3] = rightSeq[i2];
				i2++;
				i3++;
			} else {
				break;
			}
		}
	}
	
	public static <T extends Comparable <T>> void sort(T[] table) {
		// A table with one element is sorted already.
		if (table.length > 1) {
		// Split table into halves.
			int halfSize = table.length / 2;
			T[] leftTable = (T[])new Comparable[halfSize];
			T[] rightTable = (T[])new Comparable[table.length-halfSize];
			System.arraycopy(table, 0, leftTable, 0, halfSize);
			System.arraycopy(table, halfSize, rightTable, 0,
					table.length - halfSize);
			//Sort the halves.
			sort(leftTable);
			sort(rightTable);
			// Merge the halves.
			merge(table, leftTable, rightTable);
		}
	}
	
	public static void main(String[] args) {
		Integer[] a = {5,4,7,1,8,3,2,9,0,6};
		sort(a);
		for (Integer i: a) {
			System.out.println(i);
		}
		
	}
}
